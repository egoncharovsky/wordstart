package ru.egoncharovsky.wordstart.domain.learning;

import ru.egoncharovsky.wordstart.domain.Entity;
import ru.egoncharovsky.wordstart.domain.word.Translation;
import ru.egoncharovsky.wordstart.domain.word.Word;

public class LearningCard extends Entity {

    private final Word originalWord;

    private final Word translationWord;

    private Translation translation;

    public LearningCard(Word originalWord, Word translationWord) {
        this.originalWord = originalWord;
        this.translationWord = translationWord;
    }

    public LearningCard reverse() {
        return new LearningCard(translationWord, originalWord);
    }

    public Word getOriginalWord() {
        return originalWord;
    }

    public Word getTranslationWord() {
        return translationWord;
    }

//    public boolean hasTranslationVariant(Translation.Variant variant) {
//        return translationWord.equals(variant.getWord());
//    }

    public Translation getTranslation() {
        return translation;
    }

    public void setTranslation(Translation translation) {
        this.translation = translation;
    }
}
