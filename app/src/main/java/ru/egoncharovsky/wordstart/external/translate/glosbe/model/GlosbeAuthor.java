package ru.egoncharovsky.wordstart.external.translate.glosbe.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GlosbeAuthor {

    @JsonProperty("U")
    private String sourceUrl;

    private Long id;

    @JsonProperty("N")
    private String name;

    @JsonProperty("url")
    private String glosbeSourceUrl;

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGlosbeSourceUrl() {
        return glosbeSourceUrl;
    }

    public void setGlosbeSourceUrl(String glosbeSourceUrl) {
        this.glosbeSourceUrl = glosbeSourceUrl;
    }
}
