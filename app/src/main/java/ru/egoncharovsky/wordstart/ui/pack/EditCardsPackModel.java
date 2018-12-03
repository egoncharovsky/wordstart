package ru.egoncharovsky.wordstart.ui.pack;

import ru.egoncharovsky.wordstart.domain.card.CardPack;
import ru.egoncharovsky.wordstart.ui.cards.CardsList;

import java.util.ArrayList;
import java.util.List;

public class EditCardsPackModel {

    private String packName;

    private CardsList cardsList;

    public EditCardsPackModel() {
        this.cardsList = new CardsList();
    }

    public EditCardsPackModel(CardPack pack) {
        this.packName = pack.getName();
        this.cardsList = new CardsList(new ArrayList<>(pack.getCards()));
    }

    public String getPackName() {
        return packName;
    }

    public int getCardsTotal() {
        return cardsList.size();
    }

    public List<CardsList.Item> getCards() {
        return cardsList.getCards();
    }
}
