package ru.egoncharovsky.wordstart.domain;

import ru.egoncharovsky.wordstart.domain.base.Entity;

import java.util.LinkedList;
import java.util.List;

public class Translation extends Entity {

    private final Word word;

    private List<Word> translationWords;

    private final Language translationLanguage;

    public Translation(Word word, Language toLanguage) {
        if (word.getLanguage() == toLanguage) {
            throw new IllegalArgumentException("Translation language must be differ from word");
        }

        this.word = word;
        this.translationLanguage = toLanguage;
    }

    public Word getWord() {
        return word;
    }

    public List<Word> getTranslationWords() {
        return translationWords;
    }

    public void addTranslationWord(Word translation) {
        if (translation.getLanguage() != translationLanguage) {
            throw new IllegalArgumentException("Expect word language is " +
                    translationLanguage + " but match " + translation.getLanguage());
        }

        if (translationWords == null) {
            translationWords = new LinkedList<>();
        }

        translationWords.add(translation);
    }

    public Language getTranslationLanguage() {
        return translationLanguage;
    }
}
