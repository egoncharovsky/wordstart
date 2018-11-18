package ru.egoncharovsky.wordstart.ui.cards;

public interface CardsDictionaryController {
    void onDeleteCards();

    void onBackToNormalMode();

    ItemActions multiSelect();

    ItemActions normalSelect();

    interface ItemActions {
        void onItemClick(CardsDictionaryModel.Item item);

        void onItemLongClick(CardsDictionaryModel.Item item);
    }
}
