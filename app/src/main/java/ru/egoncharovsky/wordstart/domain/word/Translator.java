package ru.egoncharovsky.wordstart.domain.word;

public interface Translator {

    Translation translate(Phrase phrase, Language toLanguage);
}
