package ru.egoncharovsky.wordstart.domain.word;

import ru.egoncharovsky.wordstart.domain.Entity;

import java.util.LinkedList;
import java.util.List;

public class Translation extends Entity {

    private final Phrase originalPhrase;

    private final List<Variant> translationVariants;

    private final Language translationLanguage;

    public Translation(Phrase originalPhrase, Language toLanguage) {
        if (originalPhrase.getLanguage() == toLanguage) {
            throw new IllegalArgumentException("TranslationItem language must be differ from originalPhrase");
        }

        this.originalPhrase = originalPhrase;
        this.translationLanguage = toLanguage;
        translationVariants = new LinkedList<>();
    }

    public Phrase getOriginalPhrase() {
        return originalPhrase;
    }

    public List<Variant> getVariants() {
        return new LinkedList<>(translationVariants);
    }

    public void addVariant(Variant translation) {
        translationVariants.add(translation);
    }

    public Language getOriginalLanguage() {
        return originalPhrase.getLanguage();
    }

    public Language getTranslationLanguage() {
        return translationLanguage;
    }

    public boolean variantsContainsWord(Phrase phrase) {
        for (Variant variant : translationVariants) {
            if (variant.getPhrase().equals(phrase)) return true;
        }
        return false;
    }

    /**
     * Immutable
     */
    public class Variant {
        private final Phrase phrase;

        public Variant(Phrase phrase) {
            if (phrase.getLanguage() != translationLanguage) {
                throw new IllegalArgumentException("Expect originalPhrase language is " +
                        translationLanguage + " but match " + phrase.getLanguage());
            }

            this.phrase = phrase;
        }

        public Phrase getPhrase() {
            return phrase;
        }

        @Override
        public String toString() {
            return "Variant{" +
                    "value=" + phrase.getValue() +
                    '}';
        }
    }
}
