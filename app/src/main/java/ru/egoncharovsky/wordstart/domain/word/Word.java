package ru.egoncharovsky.wordstart.domain.word;

import ru.egoncharovsky.wordstart.domain.Entity;

import java.util.Objects;

/**
 * Immutable
 */
public class Word extends Entity {

    private final String value;

    private final Language language;

    public Word(String value, Language language) {
        Objects.requireNonNull(value);
        Objects.requireNonNull(language);

        this.value = value;
        this.language = language;
    }

    @Override
    public String toString() {
        return "Word{" +
                "value='" + value + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Word)) return false;
        if (!super.equals(o)) return false;
        Word word = (Word) o;
        return Objects.equals(getValue(), word.getValue()) &&
                getLanguage() == word.getLanguage();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getValue(), getLanguage());
    }

    public String getValue() {
        return value;
    }

    public Language getLanguage() {
        return language;
    }
}
