package ru.egoncharovsky.wordstart.ui.cards;

import ru.egoncharovsky.wordstart.domain.card.LearningCard;
import ru.egoncharovsky.wordstart.domain.card.LearningCardsService;
import ru.egoncharovsky.wordstart.domain.word.Phrase;
import ru.egoncharovsky.wordstart.repository.LearningCardRepositoryImpl;
import ru.egoncharovsky.wordstart.ui.ModelView;
import ru.egoncharovsky.wordstart.ui.translate.TranslateLanguage;

public class EditCardControllerImpl implements EditCardController {

    private ModelView<Void> view;

    private LearningCardsService cardsService = new LearningCardsService(new LearningCardRepositoryImpl());

    public EditCardControllerImpl(ModelView<Void> view) {
        this.view = view;

        view.init(null);
    }

    @Override
    public void onSave(String original, TranslateLanguage originalLanguage,
                       String translation, TranslateLanguage translationLanguage) {
        LearningCard card = new LearningCard(
                new Phrase(original, originalLanguage.getValue()),
                new Phrase(translation, translationLanguage.getValue())
        );

        cardsService.save(card);
    }
}
