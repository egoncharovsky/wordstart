package ru.egoncharovsky.wordstart.ui.translate;

public interface TranslateController {
    void onTranslate(String input);

    void onCreateCard(TranslateModel.TranslationItem item);

    void onSwapLanguage();

    void onFromLanguageSelected(TranslateLanguage selected);

    void onToLanguageSelected(TranslateLanguage selected);
}
