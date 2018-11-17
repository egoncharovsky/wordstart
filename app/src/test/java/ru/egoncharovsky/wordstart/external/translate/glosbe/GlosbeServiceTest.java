package ru.egoncharovsky.wordstart.external.translate.glosbe;

import org.junit.Assert;
import org.junit.Test;
import ru.egoncharovsky.wordstart.domain.word.Language;
import ru.egoncharovsky.wordstart.domain.word.Translation;
import ru.egoncharovsky.wordstart.domain.word.Word;
import ru.egoncharovsky.wordstart.external.translate.glosbe.model.TranslateRequest;
import ru.egoncharovsky.wordstart.external.translate.glosbe.model.TranslateResponse;

public class GlosbeServiceTest {

    private GlosbeService service = new GlosbeService();

    @Test
    public void send_responseResultIsOk() {
        Word word = new Word("Привет мир!", Language.RU);
        Language dest = Language.EN;

        TranslateRequest request = new TranslateRequest()
                .from(word.getLanguage())
                .dest(dest)
                .phrase(word.getValue());

        TranslateResponse response = service.send(request);

        Assert.assertEquals(response.getResult(), "ok");
    }

    @Test
    public void translate_hasTranslationVariants() {
        Translation result = service.translate(new Word("Hi", Language.EN), Language.RU);

        Assert.assertTrue(result.getVariants().size() > 0);
    }
}