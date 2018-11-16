package ru.egoncharovsky.wordstart.domain.learning;

import ru.egoncharovsky.wordstart.domain.word.Translation;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class LearningCardsService {

    private LearningCardRepository repository;

    public LearningCardsService(LearningCardRepository repository) {
        this.repository = repository;
    }

    public List<LearningCard> getAll() {
        return repository.getAll();
    }

    public List<LearningCard> getCardsFor(Translation translation) {
        return repository.findCardsFor(translation);
    }

    public LearningCard save(LearningCard card) {
        return repository.insert(card);
    }

    public void delete(Long id) {
        Objects.requireNonNull(id, "Id is null");

        repository.delete(id);
    }

    public void deleteAll(Collection<Long> ids) {
        for (Long id : ids) {
            delete(id);
        }
    }
}
