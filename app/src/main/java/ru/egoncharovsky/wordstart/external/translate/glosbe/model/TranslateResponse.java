package ru.egoncharovsky.wordstart.external.translate.glosbe.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class TranslateResponse {

    private String result;

    @JsonProperty("tuc")
    private List<GlosbeTranslation> translations;

    private String phrase;

    private List<GlosbeExample> examples;

    @JsonProperty("phrase")
    private String fromLanguage;

    @JsonProperty("dest")
    private String destinationLanguage;

    /**
     * key - authorId as {@link String}
     */
    private Map<String, GlosbeAuthor> authors;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<GlosbeTranslation> getTranslations() {
        return translations;
    }

    public void setTranslations(List<GlosbeTranslation> translations) {
        this.translations = translations;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public List<GlosbeExample> getExamples() {
        return examples;
    }

    public void setExamples(List<GlosbeExample> examples) {
        this.examples = examples;
    }

    public String getFromLanguage() {
        return fromLanguage;
    }

    public void setFromLanguage(String fromLanguage) {
        this.fromLanguage = fromLanguage;
    }

    public String getDestinationLanguage() {
        return destinationLanguage;
    }

    public void setDestinationLanguage(String destinationLanguage) {
        this.destinationLanguage = destinationLanguage;
    }

    public Map<String, GlosbeAuthor> getAuthors() {
        return authors;
    }

    public void setAuthors(Map<String, GlosbeAuthor> authors) {
        this.authors = authors;
    }
}
