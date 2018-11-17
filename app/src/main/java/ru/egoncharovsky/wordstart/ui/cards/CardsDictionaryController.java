package ru.egoncharovsky.wordstart.ui.cards;

import ru.egoncharovsky.wordstart.domain.learning.LearningCardsService;
import ru.egoncharovsky.wordstart.repository.LearningCardRepositoryImpl;
import ru.egoncharovsky.wordstart.ui.ModelView;

public class CardsDictionaryController implements CardsDictionaryActivity.Controller {

    private LearningCardsService cardsService = new LearningCardsService(new LearningCardRepositoryImpl());

    private ModelView<CardsDictionaryModel> view;
    private CardsDictionaryModel model;

    public CardsDictionaryController(ModelView<CardsDictionaryModel> view) {
        this.view = view;

        model = new CardsDictionaryModel(cardsService.getAll());

        view.update(model);
    }

    @Override
    public void onDeleteCards() {
        for (CardsDictionaryModel.CardItem item : model.getSelected()) {
            cardsService.delete(item.getCardId());
        }

        model = new CardsDictionaryModel(cardsService.getAll());
        view.update(model);
    }

    @Override
    public void onBackToNormalMode() {
        model.clearSelected();
        view.update(model);
    }

    @Override
    public ItemActions multiSelect() {
        return new ItemActions() {
            @Override
            public void onItemClick(CardsDictionaryModel.CardItem item) {
                model.toggleSelect(item);
                view.update(model);
            }

            @Override
            public void onItemLongClick(CardsDictionaryModel.CardItem item) {

            }
        };
    }

    @Override
    public ItemActions normalSelect() {
        return new ItemActions() {
            @Override
            public void onItemClick(CardsDictionaryModel.CardItem item) {

            }

            @Override
            public void onItemLongClick(CardsDictionaryModel.CardItem item) {
                model.toggleSelect(item);
                view.update(model);
            }
        };
    }
}
