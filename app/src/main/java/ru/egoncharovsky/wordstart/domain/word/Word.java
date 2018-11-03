package ru.egoncharovsky.wordstart.domain.word;

import ru.egoncharovsky.wordstart.domain.Entity;

/**
 * Immutable
 */
public class Word extends Entity {

    private final String value;

    private final Language language;

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

    public Language getLanguage() {
        return language;
    }
}
