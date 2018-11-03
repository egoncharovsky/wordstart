package ru.egoncharovsky.wordstart.domain.word;

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
