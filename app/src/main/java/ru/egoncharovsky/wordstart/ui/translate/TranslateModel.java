package ru.egoncharovsky.wordstart.ui.translate;

import ru.egoncharovsky.wordstart.domain.card.LearningCard;
import ru.egoncharovsky.wordstart.domain.word.Language;
import ru.egoncharovsky.wordstart.domain.word.Phrase;
import ru.egoncharovsky.wordstart.domain.word.Translation;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

class TranslateModel {
    private final Language from;
    private final Language to;

    private final Set<TranslationItem> items;

    private Phrase translatedPhrase;

    public TranslateModel(Language from, Language to) {
        this.from = from;
        this.to = to;

        items = Collections.emptySet();
    }

    public TranslateModel(Translation translation, Collection<LearningCard> cards) {
        from = translation.getOriginalLanguage();
        to = translation.getTranslationLanguage();

        translatedPhrase = translation.getOriginalPhrase();

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
        return translatedPhrase != null ? translatedPhrase.getValue() : "";
    }

    public Set<TranslationItem> getItems() {
        return new LinkedHashSet<>(items);
    }

    public class TranslationItem {
        private final Phrase phrase;
        private boolean marked;

        public TranslationItem(Translation.Variant variant) {
            this.phrase = variant.getPhrase();
            marked = false;
        }

        public String getPhrase() {
            return phrase.getValue();
        }

        public void markIfRepresents(LearningCard card) {
            if (phrase.equals(card.getTranslationPhrase())) {
                marked = true;
            }
        }

        public LearningCard toCard() {
            marked = true;
            return new LearningCard(translatedPhrase, phrase);
        }

        public boolean isMarked() {
            return marked;
        }

        @Override
        public String toString() {
            return "TranslationItem{" +
                    "phrase='" + phrase.getValue() + '\'' +
                    ", marked=" + marked +
                    '}';
        }
    }
}
