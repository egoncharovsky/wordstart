package ru.egoncharovsky.wordstart.ui.cards;

public interface CardsListActionsHandler {

    CardsListActions itemActions();

    interface CardsListActions {
        void onItemClick(CardsList.Item item);

        void onItemLongClick(CardsList.Item item);
    }
}
