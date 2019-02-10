package ru.egoncharovsky.wordstart.ui.cards;

import ru.egoncharovsky.wordstart.domain.card.LearningCardOld;
import ru.egoncharovsky.wordstart.repository.LearningCardRepository;
import ru.egoncharovsky.wordstart.ui.ModelView;

public class CardsDictionaryControllerImpl implements CardsDictionaryController {

    private LearningCardRepository cardsRepo = LearningCardRepository.INSTANCE;

    private ModelView<CardsList> view;
    private CardsList model;

    public CardsDictionaryControllerImpl(ModelView<CardsList> view) {
        this.view = view;

        model = new CardsList(LearningCardOld.from(cardsRepo.getAll()));

        view.init(model);
    }

    @Override
    public void onDeleteCards() {
        for (CardsList.Item item : model.getSelected()) {
            cardsRepo.delete(item.getCardId());
        }

        model = new CardsList(LearningCardOld.from(cardsRepo.getAll()));
        view.update(model);
    }

    @Override
    public void onBackToNormalMode() {
        model.clearSelected();
        view.update(model);
    }

    @Override
    public CardsListActionsHandler.CardsListActions multiSelect() {
        return new CardsListActionsHandler.CardsListActions() {
            @Override
            public void onItemClick(CardsList.Item item) {
                model.toggleSelect(item);
                view.update(model);
            }

            @Override
            public void onItemLongClick(CardsList.Item item) {
                model.toggleSelect(item);
                view.update(model);
            }
        };
    }

    @Override
    public CardsListActionsHandler.CardsListActions normalSelect() {
        return new CardsListActionsHandler.CardsListActions() {
            @Override
            public void onItemClick(CardsList.Item item) {

            }

            @Override
            public void onItemLongClick(CardsList.Item item) {
                model.toggleSelect(item);
                view.update(model);
            }
        };
    }
}
