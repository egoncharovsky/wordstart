package ru.egoncharovsky.wordstart.domain;

import ru.egoncharovsky.wordstart.domain.base.Entity;

public class LearningCard extends Entity {

    private Word word;

    private Word translationWord;

    public LearningCard(Word word, Word translationWord) {
        this.word = word;
        this.translationWord = translationWord;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public Word getTranslationWord() {
        return translationWord;
    }

    public void setTranslationWord(Word translationWord) {
        this.translationWord = translationWord;
    }
}
