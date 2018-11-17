package ru.egoncharovsky.wordstart.external.translate.glosbe.model;

import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

public class TranslateRequest {

    private String fromLanguage;

    private String destinationLanguage;

    private String format = "json";

    private String phrase;

    private Boolean translateMemory = true;

    private Boolean pretty = true;

    public Map<String, ?> asGetOptions() {
        Assert.notNull(fromLanguage, "From language must be specified");
        Assert.notNull(destinationLanguage, "Destination language must be specified");
        Assert.isTrue(!fromLanguage.equals(destinationLanguage), "From language must differ from destination");
        Assert.notNull(phrase, "Phrase for translation must be specified");
        Assert.notNull(format, "Format must be specified");

        return new HashMap<String, Object>() {{
           put("from", fromLanguage);
           put("dest", destinationLanguage);
           put("format", format);
           put("phrase", phrase);
           put("tm", translateMemory);
           put("pretty", pretty);
        }};
    }

    public TranslateRequest from(String from) {
        setFromLanguage(from);
        return this;
    }

    public TranslateRequest dest(String dest) {
        setDestinationLanguage(dest);
        return this;
    }

    public TranslateRequest phrase(String phrase) {
        setPhrase(phrase);
        return this;
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

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public Boolean getTranslateMemory() {
        return translateMemory;
    }

    public void setTranslateMemory(Boolean translateMemory) {
        this.translateMemory = translateMemory;
    }

    public Boolean getPretty() {
        return pretty;
    }

    public void setPretty(Boolean pretty) {
        this.pretty = pretty;
    }
}
