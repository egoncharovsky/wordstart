package ru.egoncharovsky.wordstart.ui.translate;

import ru.egoncharovsky.wordstart.domain.card.LearningCard;
import ru.egoncharovsky.wordstart.domain.word.Language;
import ru.egoncharovsky.wordstart.domain.word.Translation;
import ru.egoncharovsky.wordstart.domain.word.Word;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

class TranslateModel {
    private final Language from;
    private final Language to;

    private final Set<TranslationItem> items;

    private Word translatedWord;

    public TranslateModel(Language from, Language to) {
        this.from = from;
        this.to = to;

        items = Collections.emptySet();
    }

    public TranslateModel(Translation translation, Collection<LearningCard> cards) {
        from = translation.getOriginalLanguage();
        to = translation.getTranslationLanguage();

        translatedWord = translation.getOriginalWord();

        items = new LinkedHashSet<>();
        for (Translation.Variant variant : translation.getVariants()) {
            TranslationItem item = new TranslationItem(variant);

            if (cards != null) {
                for (LearningCard card : cards) {
                    item.markIfRepresents(card);
                }
            }

            items.add(item);
        }
    }

    public Language getFrom() {
        return from;
    }

    public Language getTo() {
        return to;
    }

    public String getTranslatedText() {
        return translatedWord != null ? translatedWord.getValue() : "";
    }

    public Set<TranslationItem> getItems() {
        return new LinkedHashSet<>(items);
    }

    public class TranslationItem {
        private final Word word;
        private boolean marked;

        public TranslationItem(Translation.Variant variant) {
            this.word = variant.getWord();
            marked = false;
        }

        public String getWord() {
            return word.getValue();
        }

        public void markIfRepresents(LearningCard card) {
            if (word.equals(card.getTranslationWord())) {
                marked = true;
            }
        }

        public LearningCard toCard() {
            marked = true;
            return new LearningCard(translatedWord, word);
        }

        public boolean isMarked() {
            return marked;
        }

        @Override
        public String toString() {
            return "TranslationItem{" +
                    "word='" + word.getValue() + '\'' +
                    ", marked=" + marked +
                    '}';
        }
    }
}
