package ru.egoncharovsky.wordstart.ui.cards;

import ru.egoncharovsky.wordstart.domain.learning.LearningCard;

import java.util.*;

public class CardsDictionaryModel {
    private final List<CardItem> cards = new LinkedList<>();
    private final Set<CardItem> selected = new HashSet<>();

    public CardsDictionaryModel(List<LearningCard> cards) {
        for (LearningCard card : cards) {
            this.cards.add(new CardItem(card));
        }
    }

    public void toggleSelect(CardItem item) {
        if (selected.contains(item)) {
            selected.remove(item);
        } else {
            selected.add(item);
        }
    }

    public List<CardItem> getCards() {
        return new ArrayList<>(cards);
    }

    public class CardItem {
        private final LearningCard card;

        private CardItem(LearningCard card) {
            this.card = card;
        }

        public boolean isSelected() {
            return selected.contains(this);
        }

        public String getText() {
            return card.getOriginalWord().getValue();
        }

        public String getSubText() {
            return card.getTranslationWord().getValue();
        }
    }
}
