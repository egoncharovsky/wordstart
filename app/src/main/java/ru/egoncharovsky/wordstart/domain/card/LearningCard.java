package ru.egoncharovsky.wordstart.domain.card;

import ru.egoncharovsky.wordstart.domain.Entity;
import ru.egoncharovsky.wordstart.domain.word.Translation;
import ru.egoncharovsky.wordstart.domain.word.Word;

/**
 * Immutable
 */
public class LearningCard extends Entity {

    private final Word originalWord;

    private final Word translationWord;

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

    public boolean containedInTranslation(Translation translation) {
        return translation.getOriginalWord().equals(originalWord)
                && translation.variantsContainsWord(translationWord);
    }
}
