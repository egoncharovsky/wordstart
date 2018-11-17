package ru.egoncharovsky.wordstart.external.translate.glosbe.model;

public class GlosbeMeaning {
    private GlosbeLanguage language;
    private String text;

    public GlosbeLanguage getLanguage() {
        return language;
    }

    public void setLanguage(GlosbeLanguage language) {
        this.language = language;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
