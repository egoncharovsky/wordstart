package ru.egoncharovsky.wordstart.ui.cards;

import ru.egoncharovsky.wordstart.domain.card.LearningCardOld;
import ru.egoncharovsky.wordstart.domain.word.Phrase;
import ru.egoncharovsky.wordstart.repository.LearningCardRepository;
import ru.egoncharovsky.wordstart.ui.ModelView;
import ru.egoncharovsky.wordstart.ui.translate.TranslateLanguage;

public class EditCardControllerImpl implements EditCardController {

    private ModelView<Void> view;

    private LearningCardRepository cardRepo = LearningCardRepository.INSTANCE;

    public EditCardControllerImpl(ModelView<Void> view) {
        this.view = view;

        view.init(null);
    }

    @Override
    public void onSave(String original, TranslateLanguage originalLanguage,
                       String translation, TranslateLanguage translationLanguage) {
        LearningCardOld card = new LearningCardOld(
                new Phrase(original, originalLanguage.getValue()),
                new Phrase(translation, translationLanguage.getValue())
        );

        cardRepo.create(card.toLearningCard());
    }
}
