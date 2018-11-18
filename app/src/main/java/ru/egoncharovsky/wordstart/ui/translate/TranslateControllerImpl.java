package ru.egoncharovsky.wordstart.ui.translate;

import ru.egoncharovsky.wordstart.domain.card.LearningCard;
import ru.egoncharovsky.wordstart.domain.card.LearningCardsService;
import ru.egoncharovsky.wordstart.domain.word.Language;
import ru.egoncharovsky.wordstart.domain.word.Translation;
import ru.egoncharovsky.wordstart.domain.word.TranslationService;
import ru.egoncharovsky.wordstart.domain.word.Word;
import ru.egoncharovsky.wordstart.external.translate.glosbe.GlosbeService;
import ru.egoncharovsky.wordstart.repository.LearningCardRepositoryImpl;
import ru.egoncharovsky.wordstart.ui.ModelView;

import java.util.HashSet;
import java.util.Set;

public class TranslateControllerImpl implements TranslateController {

    private TranslationService translationService = new TranslationService(new GlosbeService());
    private LearningCardsService cardsService = new LearningCardsService(new LearningCardRepositoryImpl());

    private ModelView<TranslateModel> view;
    private TranslateModel model;

    public TranslateControllerImpl(ModelView<TranslateModel> view) {
        this.view = view;

        // todo add choose language
        model = new TranslateModel(Language.EN, Language.RU);

        view.init(model);
    }

    @Override
    public void onTranslate(String input) {
        Word word = new Word(input, model.getFrom());

        Translation translation = translationService.translate(word, model.getTo());

        Set<LearningCard> cards = new HashSet<>(cardsService.getCardsFor(translation));
        model = new TranslateModel(translation, cards);

        view.update(model);
    }

    @Override
    public void onCreateCard(TranslateModel.TranslationItem item) {
        cardsService.save(item.toCard());
        view.update(model);
    }
}
