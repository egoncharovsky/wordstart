package ru.egoncharovsky.wordstart.domain.word;

import ru.egoncharovsky.wordstart.domain.Entity;

import java.util.LinkedList;
import java.util.List;

public class Translation extends Entity {

    private final Word originalWord;

    private final List<Variant> translationVariants;

    private final Language translationLanguage;

    public Translation(Word originalWord, Language toLanguage) {
        if (originalWord.getLanguage() == toLanguage) {
            throw new IllegalArgumentException("TranslationItem language must be differ from originalWord");
        }

        this.originalWord = originalWord;
        this.translationLanguage = toLanguage;
        translationVariants = new LinkedList<>();
    }

    public Word getOriginalWord() {
        return originalWord;
    }

    public List<Variant> getVariants() {
        return translationVariants;
    }

    public void addVariant(Variant translation) {
        translationVariants.add(translation);
    }

    public Language getOriginalLanguage() {
        return originalWord.getLanguage();
    }

    public Language getTranslationLanguage() {
        return translationLanguage;
    }

    public boolean variantsContainsWord(Word word) {
        for (Variant variant : translationVariants) {
            if (variant.getWord().equals(word)) return true;
        }
        return false;
    }

    public class Variant {
        private final Word word;

        public Variant(Word word) {
            if (word.getLanguage() != translationLanguage) {
                throw new IllegalArgumentException("Expect originalWord language is " +
                        translationLanguage + " but match " + word.getLanguage());
            }

            this.word = word;
        }

        public Word getWord() {
            return word;
        }

        @Override
        public String toString() {
            return "Variant{" +
                    "value=" + word.getValue() +
                    '}';
        }
    }
}
