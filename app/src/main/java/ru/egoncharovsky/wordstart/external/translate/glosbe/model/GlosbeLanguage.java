package ru.egoncharovsky.wordstart.external.translate.glosbe.model;

import com.fasterxml.jackson.annotation.JsonValue;
import ru.egoncharovsky.wordstart.domain.word.Language;

public enum GlosbeLanguage {
    EN("en"),
    RU("ru");

    private String code;

    GlosbeLanguage(String code) {
        this.code = code;
    }

    public static GlosbeLanguage from(Language language) {
        switch (language) {
            case EN:
                return EN;
            case RU:
                return RU;
            default:
                throw new IllegalArgumentException("Unsupported language in Glosbe: " + language);
        }
    }

    public Language toLanguage() {
        switch (code) {
            case "en":
                return Language.EN;
            case "ru":
                return Language.RU;
            default:
                throw new IllegalArgumentException("Unknown Glosbe language code: " + code);
        }
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code;
    }
}
