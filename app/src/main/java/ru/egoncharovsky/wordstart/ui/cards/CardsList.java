package ru.egoncharovsky.wordstart.ui.cards;

import ru.egoncharovsky.wordstart.domain.card.LearningCardOld;

import java.util.*;

public class CardsList {
    private final List<Item> cards = new LinkedList<>();
    private final Set<Item> selected = new HashSet<>();

    public CardsList() {

    }

    public CardsList(List<LearningCardOld> cards) {
        for (LearningCardOld card : cards) {
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

    public int size() {
        return cards.size();
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
        private final LearningCardOld card;

        private Item(LearningCardOld card) {
            this.card = card;
        }

        public boolean isSelected() {
            return selected.contains(this);
        }

        public String getText() {
            return card.getOriginalPhrase().getValue();
        }

        public String getSubText() {
            return card.getTranslationPhrase().getValue();
        }

        public Long getCardId() {
            return card.getId();
        }
    }
}
