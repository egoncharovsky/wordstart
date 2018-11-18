package ru.egoncharovsky.wordstart.ui.cards;

import ru.egoncharovsky.wordstart.domain.card.LearningCard;

import java.util.*;

public class CardsDictionaryModel {
    private final List<Item> cards = new LinkedList<>();
    private final Set<Item> selected = new HashSet<>();

    public CardsDictionaryModel(List<LearningCard> cards) {
        for (LearningCard card : cards) {
            this.cards.add(new Item(card));
        }
    }

    public void toggleSelect(Item item) {
        if (selected.contains(item)) {
            selected.remove(item);
        } else {
            selected.add(item);
        }
    }

    public boolean hasSelected() {
        return !selected.isEmpty();
    }

    public List<Item> getCards() {
        return new ArrayList<>(cards);
    }

    public Set<Item> getSelected() {
        return new HashSet<>(selected);
    }

    public void clearSelected() {
        selected.clear();
    }

    public class Item {
        private final LearningCard card;

        private Item(LearningCard card) {
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

        public Long getCardId() {
            return card.getId();
        }
    }
}
