package ru.egoncharovsky.wordstart.domain.base;

public abstract class Entity implements Identifieble {

    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
