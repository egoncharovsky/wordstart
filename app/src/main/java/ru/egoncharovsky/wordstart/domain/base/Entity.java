package ru.egoncharovsky.wordstart.domain.base;

public abstract class Entity {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
