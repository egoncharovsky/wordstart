package ru.egoncharovsky.wordstart.ui.translate;

import ru.egoncharovsky.wordstart.ui.shared.TranslateLanguage;

public interface TranslateController {
    void onTranslate(String input);

    void onCreateCard(TranslateModel.TranslationItem item);

    void onSwapLanguage();

    void onFromLanguageSelected(TranslateLanguage selected);

    void onToLanguageSelected(TranslateLanguage selected);
}
