package ru.egoncharovsky.wordstart.domain.service;

import ru.egoncharovsky.wordstart.domain.Language;
import ru.egoncharovsky.wordstart.domain.LearningCard;
import ru.egoncharovsky.wordstart.domain.Translation;
import ru.egoncharovsky.wordstart.domain.Word;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class LearningCardsService {

    private static LearningCardsService instance;

    private LearningCardsService() {

    }

    public static LearningCardsService getInstance() {
        if (instance == null) {
            instance = new LearningCardsService();
        }
        return instance;
    }

    public List<LearningCard> getAll() {
        Word word1 = new Word("слово", Language.RU);
        Word word2 = new Word("word", Language.EN);

        final LearningCard card = new LearningCard(word1, word2);

        List<LearningCard> cards = new LinkedList<LearningCard>() {{
            add(card);
            add(card.reverse());
        }};

        return cards;
    }

    public List<LearningCard> getCardsFor(Translation translation) {
        if (!translation.getTranslationWords().isEmpty()) {
            final Word word = translation.getWord();
            final Word translationWord = translation.getTranslationWords().get(0);

            return new LinkedList<LearningCard>() {{
                add(new LearningCard(word, translationWord));
            }};
        }
        return Collections.emptyList();
    }
}
