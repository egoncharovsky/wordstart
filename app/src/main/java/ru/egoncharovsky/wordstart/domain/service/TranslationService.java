package ru.egoncharovsky.wordstart.domain.service;

import ru.egoncharovsky.wordstart.domain.Language;
import ru.egoncharovsky.wordstart.domain.Translation;
import ru.egoncharovsky.wordstart.domain.Word;

public class TranslationService {

    private static TranslationService instance;

    private TranslationService() {

    }

    public static TranslationService getInstace() {
        if (instance == null) {
            instance = new TranslationService();
        }
        return instance;
    }

    public Translation translate(Word word, final Language toLanguage) {
        return new Translation(word, toLanguage) {{
            addTranslationWord(new Word("переведено 1", toLanguage));
            addTranslationWord(new Word("переведено 2", toLanguage));
            addTranslationWord(new Word("переведено 3", toLanguage));
        }};
    }
}
