package ru.egoncharovsky.wordstart.ui.cards;

import ru.egoncharovsky.wordstart.domain.card.LearningCardsService;
import ru.egoncharovsky.wordstart.repository.LearningCardRepositoryImpl;
import ru.egoncharovsky.wordstart.ui.ModelView;

public class CardsDictionaryControllerImpl implements CardsDictionaryController {

    private LearningCardsService cardsService = new LearningCardsService(new LearningCardRepositoryImpl());

    private ModelView<CardsDictionaryModel> view;
    private CardsDictionaryModel model;

    public CardsDictionaryControllerImpl(ModelView<CardsDictionaryModel> view) {
        this.view = view;

        model = new CardsDictionaryModel(cardsService.getAll());

        view.init(model);
    }

    @Override
    public void onDeleteCards() {
        for (CardsDictionaryModel.Item item : model.getSelected()) {
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
            public void onItemClick(CardsDictionaryModel.Item item) {
                model.toggleSelect(item);
                view.update(model);
            }

            @Override
            public void onItemLongClick(CardsDictionaryModel.Item item) {
                model.toggleSelect(item);
                view.update(model);
            }
        };
    }

    @Override
    public ItemActions normalSelect() {
        return new ItemActions() {
            @Override
            public void onItemClick(CardsDictionaryModel.Item item) {

            }

            @Override
            public void onItemLongClick(CardsDictionaryModel.Item item) {
                model.toggleSelect(item);
                view.update(model);
            }
        };
    }
}
