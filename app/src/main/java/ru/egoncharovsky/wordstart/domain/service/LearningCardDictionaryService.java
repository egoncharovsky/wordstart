package ru.egoncharovsky.wordstart.domain.service;

import ru.egoncharovsky.wordstart.domain.Language;
import ru.egoncharovsky.wordstart.domain.LearningCard;
import ru.egoncharovsky.wordstart.domain.Word;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class LearningCardDictionaryService {

    public List<LearningCard> getAll() {
        Word word1 = new Word("слово", Language.RU);
        Word word2 = new Word("word", Language.EN);

        LearningCard card = new LearningCard(word1, word2);
        
        return Collections.singletonList(card);
    }

}
