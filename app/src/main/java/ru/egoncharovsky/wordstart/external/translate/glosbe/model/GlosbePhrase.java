package ru.egoncharovsky.wordstart.external.translate.glosbe.model;

public class GlosbePhrase {

    private String text;

    private GlosbeLanguage language;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public GlosbeLanguage getLanguage() {
        return language;
    }

    public void setLanguage(GlosbeLanguage language) {
        this.language = language;
    }
}
