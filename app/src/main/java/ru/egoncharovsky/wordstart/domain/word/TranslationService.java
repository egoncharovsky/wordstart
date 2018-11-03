package ru.egoncharovsky.wordstart.domain.word;

public class TranslationService {

    public TranslationService() {

    }

    public Translation translate(Word word, final Language toLanguage) {
        return new Translation(word, toLanguage) {{
            addVariant(this.new Variant(new Word("переведено 1", toLanguage)));
            addVariant(this.new Variant(new Word("переведено 2", toLanguage)));
            addVariant(this.new Variant(new Word("переведено 3", toLanguage)));
        }};
    }
}
