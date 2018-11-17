package ru.egoncharovsky.wordstart.ui.cards;

public interface CardsDictionaryController {
    void onDeleteCards();

    void onBackToNormalMode();

    ItemActions multiSelect();

    ItemActions normalSelect();

    interface ItemActions {
        void onItemClick(CardsDictionaryModel.CardItem item);

        void onItemLongClick(CardsDictionaryModel.CardItem item);
    }
}
