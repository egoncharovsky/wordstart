package ru.egoncharovsky.wordstart.domain;

import ru.egoncharovsky.wordstart.domain.base.Entity;

public class Word extends Entity {

    private String value;

    private Language language;

    public Word(String value, Language language) {
        this.value = value;
        this.language = language;
    }

    @Override
    public String toString() {
        return "Word{" +
                "value='" + value + '\'' +
                '}';
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
