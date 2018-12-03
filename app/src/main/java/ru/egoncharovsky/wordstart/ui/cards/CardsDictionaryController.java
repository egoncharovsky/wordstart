package ru.egoncharovsky.wordstart.ui.cards;

public interface CardsDictionaryController {
    void onDeleteCards();

    void onBackToNormalMode();

    CardsListActionsHandler.CardsListActions multiSelect();

    CardsListActionsHandler.CardsListActions normalSelect();

}
