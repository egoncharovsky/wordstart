package ru.egoncharovsky.wordstart.domain.card;

import ru.egoncharovsky.wordstart.domain.Entity;
import ru.egoncharovsky.wordstart.domain.word.Phrase;
import ru.egoncharovsky.wordstart.domain.word.Translation;

/**
 * Immutable
 */
public class LearningCard extends Entity {

    private final Phrase originalPhrase;

    private final Phrase translationPhrase;

    public LearningCard(Phrase originalPhrase, Phrase translationPhrase) {
        if (originalPhrase.getLanguage().equals(translationPhrase.getLanguage()))
            throw new IllegalArgumentException("Translation language must differ to original");

        this.originalPhrase = originalPhrase;
        this.translationPhrase = translationPhrase;
    }

    public LearningCard reverse() {
        return new LearningCard(translationPhrase, originalPhrase);
    }

    public Phrase getOriginalPhrase() {
        return originalPhrase;
    }

    public Phrase getTranslationPhrase() {
        return translationPhrase;
    }

    public boolean containedInTranslation(Translation translation) {
        return translation.getOriginalPhrase().equals(originalPhrase)
                && translation.variantsContainsWord(translationPhrase);
    }
}
