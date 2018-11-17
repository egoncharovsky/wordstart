package ru.egoncharovsky.wordstart.domain.word;

public interface TranslateService {

    Translation translate(Word word, Language toLanguage);
}
