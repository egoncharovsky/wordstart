package ru.egoncharovsky.wordstart.domain.card;

import ru.egoncharovsky.wordstart.domain.Entity;
import ru.egoncharovsky.wordstart.domain.word.Phrase;
import ru.egoncharovsky.wordstart.domain.word.Translation;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Immutable
 */
@Deprecated
public class LearningCardOld extends Entity {

    public static LearningCardOld from(LearningCard card) {
        return new LearningCardOld(card.component2(), card.component3());
    }

    public static List<LearningCardOld> from(Collection<LearningCard> cards) {
        List<LearningCardOld> list = new LinkedList<>();
        for (LearningCard card : cards) {
            list.add(from(card));
        }
        return list;
    }

    public LearningCard toLearningCard() {
        return new LearningCard(getId(), originalPhrase, translationPhrase);
    }

    private final Phrase originalPhrase;

    private final Phrase translationPhrase;

    public LearningCardOld(Phrase originalPhrase, Phrase translationPhrase) {
        if (originalPhrase.getLanguage().equals(translationPhrase.getLanguage()))
            throw new IllegalArgumentException("Translation language must differ to original");

        this.originalPhrase = originalPhrase;
        this.translationPhrase = translationPhrase;
    }

    public LearningCardOld reverse() {
        return new LearningCardOld(translationPhrase, originalPhrase);
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
