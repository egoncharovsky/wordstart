package ru.egoncharovsky.wordstart.repository;

import android.annotation.SuppressLint;
import ru.egoncharovsky.wordstart.domain.card.LearningCard;
import ru.egoncharovsky.wordstart.domain.card.LearningCardRepository;
import ru.egoncharovsky.wordstart.domain.word.Language;
import ru.egoncharovsky.wordstart.domain.word.Translation;
import ru.egoncharovsky.wordstart.domain.word.Word;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LearningCardRepositoryImpl implements LearningCardRepository {

    @SuppressLint("UseSparseArrays")
    private static Map<Long, LearningCard> data = new HashMap<>();
    private static Long counter = 0L;
    static {
        insertStatic(new LearningCard(
                new Word("word 2", Language.EN),
                new Word("слово 2", Language.RU)));
        insertStatic(new LearningCard(
                new Word("word", Language.EN),
                new Word("слово", Language.RU)));
    }

    private static LearningCard insertStatic(LearningCard item) {
        item.persist((counter++));
        data.put(item.getId(), item);
        return item;
    }

    @Override
    public LearningCard insert(LearningCard item) {
        return insertStatic(item);
    }

    @Override
    public LearningCard get(Long id) {
        return data.get(id);
    }

    @Override
    public List<LearningCard> getAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public LearningCard update(LearningCard item) {
        if (item.getId() != null && data.containsKey(item.getId())) {
            data.put(item.getId(), item);
            return item;
        } else {
            throw new RuntimeException("item not persisted");
        }
    }

    @Override
    public void delete(Long id) {
        data.remove(id);
    }

    @Override
    public List<LearningCard> findCardsFor(Translation translation) {
        List<LearningCard> found = new ArrayList<>();
        for (LearningCard card : data.values()) {
            if (card.containedInTranslation(translation)) {
                found.add(card);
            }
        }
        return found;
    }
}
