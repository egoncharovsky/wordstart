package ru.egoncharovsky.wordstart.external.translate.glosbe.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GlosbeTranslation {

    private GlosbePhrase phrase;

    private List<GlosbeMeaning> meanings;

    private Long meaningId;

    @JsonProperty("authors")
    private List<Long> authorIds;

    public GlosbePhrase getPhrase() {
        return phrase;
    }

    public void setPhrase(GlosbePhrase phrase) {
        this.phrase = phrase;
    }

    public List<GlosbeMeaning> getMeanings() {
        return meanings;
    }

    public void setMeanings(List<GlosbeMeaning> meanings) {
        this.meanings = meanings;
    }

    public Long getMeaningId() {
        return meaningId;
    }

    public void setMeaningId(Long meaningId) {
        this.meaningId = meaningId;
    }

    public List<Long> getAuthorIds() {
        return authorIds;
    }

    public void setAuthorIds(List<Long> authorIds) {
        this.authorIds = authorIds;
    }
}
