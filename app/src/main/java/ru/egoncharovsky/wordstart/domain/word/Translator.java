package ru.egoncharovsky.wordstart.domain.word;

public interface Translator {

    Translation translate(Word word, Language toLanguage);
}
