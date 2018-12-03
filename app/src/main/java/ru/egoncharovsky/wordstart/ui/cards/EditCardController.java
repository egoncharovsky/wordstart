package ru.egoncharovsky.wordstart.ui.cards;

import ru.egoncharovsky.wordstart.ui.translate.TranslateLanguage;

public interface EditCardController {
    void onSave(String original, TranslateLanguage originalLanguage,
                String translation, TranslateLanguage translationLanguage);
}
