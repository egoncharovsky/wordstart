package ru.egoncharovsky.wordstart.domain.learning;

import ru.egoncharovsky.wordstart.domain.Entity;
import ru.egoncharovsky.wordstart.domain.word.Translation;
import ru.egoncharovsky.wordstart.domain.word.Word;

public class LearningCard extends Entity {

    private final Word word;

    private final Word translationWord;

    private Translation translation;

    public LearningCard(Word word, Word translationWord) {
        this.word = word;
        this.translationWord = translationWord;
    }

    public LearningCard reverse() {
        return new LearningCard(translationWord, word);
    }

    public Word getWord() {
        return word;
    }

    public Word getTranslationWord() {
        return translationWord;
    }

    public Translation getTranslation() {
        return translation;
    }

    public void setTranslation(Translation translation) {
        this.translation = translation;
    }
}
