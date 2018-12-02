package ru.egoncharovsky.wordstart.ui.shared;

import ru.egoncharovsky.wordstart.R;
import ru.egoncharovsky.wordstart.domain.word.Language;
import ru.egoncharovsky.wordstart.ui.EnumString;

public enum TranslateLanguage implements EnumString<Language> {
    EN(Language.EN, R.string.language_english),
    RU(Language.RU, R.string.language_russian);

    private final Language value;
    private final int name;

    TranslateLanguage(Language language, int name) {
        this.value = language;
        this.name = name;
    }

    public static TranslateLanguage from(Language language) {
        for (TranslateLanguage tl : TranslateLanguage.values()) {
            if (tl.getValue() == language) return tl;
        }
        throw new IllegalArgumentException("Language isn't presented: " + language);
    }

    public boolean possibleTranslateTo(TranslateLanguage language) {
        return !this.equals(language);
    }

    public boolean possibleTranslateFrom(TranslateLanguage language) {
        return !this.equals(language);
    }

    @Override
    public Language getValue() {
        return value;
    }

    @Override
    public int getString() {
        return name;
    }
}
