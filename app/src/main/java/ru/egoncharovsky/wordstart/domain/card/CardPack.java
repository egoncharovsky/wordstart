package ru.egoncharovsky.wordstart.domain.card;

import ru.egoncharovsky.wordstart.domain.Entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CardPack extends Entity {

    private String name;

    private final Set<LearningCard> cards;

    public CardPack(String name, Collection<LearningCard> cards) {
        if (name == null) throw new IllegalArgumentException("Card pack name must be not null");
        this.name = name;
        this.cards = new HashSet<>(cards);
    }

    public String getName() {
        return name;
    }

    public void rename(String name) {
        Objects.requireNonNull(name);
        this.name = name;
    }

    public void addCard(LearningCard card) {
        cards.add(card);
    }

    public void removeCard(LearningCard card) {
        cards.remove(card);
    }

    public Set<LearningCard> getCards() {
        return new HashSet<>(cards);
    }
}
