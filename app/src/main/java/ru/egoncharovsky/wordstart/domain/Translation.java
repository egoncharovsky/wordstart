package ru.egoncharovsky.wordstart.domain;

import ru.egoncharovsky.wordstart.domain.base.Entity;

import java.util.List;

public class Translation extends Entity {

    private Word word;

    private List<Word> translationWords;

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public List<Word> getTranslationWords() {
        return translationWords;
    }

    public void setTranslationWords(List<Word> translationWords) {
        this.translationWords = translationWords;
    }
}
