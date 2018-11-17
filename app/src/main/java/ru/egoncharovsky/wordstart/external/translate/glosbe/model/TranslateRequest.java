package ru.egoncharovsky.wordstart.external.translate.glosbe.model;

import org.springframework.util.Assert;
import org.springframework.web.util.UriComponentsBuilder;
import ru.egoncharovsky.wordstart.domain.word.Language;

import java.net.URI;

public class TranslateRequest {

    private GlosbeLanguage fromLanguage;

    private GlosbeLanguage destinationLanguage;

    private String format = "json";

    private String phrase;

    private Boolean translateMemory = true;

    private Boolean pretty = true;

    public URI toGetURI(String baseUri) {
        Assert.notNull(fromLanguage, "From language must be specified");
        Assert.notNull(destinationLanguage, "Destination language must be specified");
        Assert.isTrue(!fromLanguage.equals(destinationLanguage), "From language must differ from destination");
        Assert.notNull(phrase, "Phrase for translation must be specified");
        Assert.notNull(format, "Format must be specified");

        UriComponentsBuilder b = UriComponentsBuilder.fromHttpUrl(baseUri)
                .queryParam("from", fromLanguage)
                .queryParam("dest", destinationLanguage)
                .queryParam("phrase", phrase)
                .queryParam("format", format);
        if (translateMemory != null) {
            b.queryParam("tm", translateMemory);
        }
        if (pretty != null) {
            b.queryParam("pretty", pretty);
        }

        return b.build().encode().toUri();
    }

    public TranslateRequest from(Language from) {
        setFromLanguage(GlosbeLanguage.from(from));
        return this;
    }

    public TranslateRequest dest(Language dest) {
        setDestinationLanguage(GlosbeLanguage.from(dest));
        return this;
    }

    public TranslateRequest phrase(String phrase) {
        setPhrase(phrase.toLowerCase().trim());
        return this;
    }

    public GlosbeLanguage getFromLanguage() {
        return fromLanguage;
    }

    public void setFromLanguage(GlosbeLanguage fromLanguage) {
        this.fromLanguage = fromLanguage;
    }

    public GlosbeLanguage getDestinationLanguage() {
        return destinationLanguage;
    }

    public void setDestinationLanguage(GlosbeLanguage destinationLanguage) {
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
