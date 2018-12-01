package ru.egoncharovsky.wordstart.domain.word;

import ru.egoncharovsky.wordstart.domain.Entity;

import java.util.Objects;

/**
 * Immutable
 */
public class Phrase extends Entity {

    private final String value;

    private final Language language;

    public Phrase(String value, Language language) {
        Objects.requireNonNull(value);
        Objects.requireNonNull(language);

        this.value = value;
        this.language = language;
    }

    @Override
    public String toString() {
        return "Phrase{" +
                "value='" + value + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Phrase)) return false;
        if (!super.equals(o)) return false;
        Phrase phrase = (Phrase) o;
        return Objects.equals(getValue(), phrase.getValue()) &&
                getLanguage() == phrase.getLanguage();
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
