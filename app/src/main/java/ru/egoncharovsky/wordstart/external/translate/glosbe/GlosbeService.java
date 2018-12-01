package ru.egoncharovsky.wordstart.external.translate.glosbe;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import ru.egoncharovsky.wordstart.domain.word.Language;
import ru.egoncharovsky.wordstart.domain.word.Phrase;
import ru.egoncharovsky.wordstart.domain.word.Translation;
import ru.egoncharovsky.wordstart.domain.word.Translator;
import ru.egoncharovsky.wordstart.external.translate.glosbe.model.GlosbePhrase;
import ru.egoncharovsky.wordstart.external.translate.glosbe.model.GlosbeTranslation;
import ru.egoncharovsky.wordstart.external.translate.glosbe.model.TranslateRequest;
import ru.egoncharovsky.wordstart.external.translate.glosbe.model.TranslateResponse;

import java.net.URI;

/**
 * Powered by API of <a href="https://glosbe.com/">Glosbe Dictionary</a>
 */
public class GlosbeService implements Translator {

    private static final String ENDPOINT_TRANSLATE = "https://glosbe.com/gapi/translate";
    private RestTemplate rest = new RestTemplate() {{
        getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }};

    @Override
    public Translation translate(Phrase phrase, Language toLanguage) {
        TranslateRequest request = new TranslateRequest()
                .from(phrase.getLanguage())
                .dest(toLanguage)
                .phrase(phrase.getValue());

        TranslateResponse response = send(request);

        Translation translation = new Translation(phrase, toLanguage);
        for (GlosbeTranslation glosbeTranslation : response.getTranslations()) {
            GlosbePhrase glosbePhrase = glosbeTranslation.getPhrase();
            if (glosbePhrase != null) {

                if (request.getDestinationLanguage().equals(glosbePhrase.getLanguage())
                        && glosbePhrase.getText() != null) {

                    translation.addVariant(
                            translation.new Variant(
                                    new Phrase(glosbePhrase.getText(), glosbePhrase.getLanguage().toLanguage())
                            )
                    );
                }
            }

        }

        return translation;
    }

    protected TranslateResponse send(TranslateRequest request) {
        URI uri = request.toGetURI(ENDPOINT_TRANSLATE);
        return rest.getForObject(uri, TranslateResponse.class);
    }
}