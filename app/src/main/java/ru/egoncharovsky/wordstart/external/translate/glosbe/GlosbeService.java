package ru.egoncharovsky.wordstart.external.translate.glosbe;

import org.springframework.web.client.RestTemplate;
import ru.egoncharovsky.wordstart.domain.word.Language;
import ru.egoncharovsky.wordstart.domain.word.Translation;
import ru.egoncharovsky.wordstart.domain.word.Translator;
import ru.egoncharovsky.wordstart.domain.word.Word;
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
    private RestTemplate rest = new RestTemplate();

    @Override
    public Translation translate(Word word, Language toLanguage) {
        TranslateRequest request = new TranslateRequest()
                .from(word.getLanguage())
                .dest(toLanguage)
                .phrase(word.getValue());

        TranslateResponse response = send(request);

        Translation translation = new Translation(word, toLanguage);
        for (GlosbeTranslation glosbeTranslation : response.getTranslations()) {
            GlosbePhrase phrase = glosbeTranslation.getPhrase();
            if (phrase != null) {

                if (request.getDestinationLanguage().equals(phrase.getLanguage())
                        && phrase.getText() != null) {

                    translation.addVariant(
                            translation.new Variant(
                                    new Word(phrase.getText(), phrase.getLanguage().toLanguage())
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