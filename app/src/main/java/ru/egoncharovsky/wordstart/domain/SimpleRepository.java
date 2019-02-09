package ru.egoncharovsky.wordstart.domain;

import java.util.List;

public interface SimpleRepository<I extends IdentifiableOld> {

    I insert(I item);

    I get(Long id);

    List<I> getAll();

    I update(I item);

    void delete(Long id);
}
