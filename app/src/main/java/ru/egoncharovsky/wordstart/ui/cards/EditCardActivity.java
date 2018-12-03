package ru.egoncharovsky.wordstart.ui.cards;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import ru.egoncharovsky.wordstart.R;
import ru.egoncharovsky.wordstart.ui.*;
import ru.egoncharovsky.wordstart.ui.translate.TranslateLanguage;

public class EditCardActivity extends BaseActivity implements ModelView<Void> {

    private EditCardController controller;

    private EnumSpinnerWrapper<TranslateLanguage> originalLanguage;
    private EditText phraseOriginal;
    private EnumSpinnerWrapper<TranslateLanguage> translationLanguage;
    private EditText phraseTranslation;

    private Button save;
    private Button cancel;

    @Override
    public int getActivityViewId() {
        return R.layout.activity_edit_card;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        controller = new EditCardControllerImpl(this);
    }

    @Override
    public void init(Void model) {
        phraseOriginal = findViewById(R.id.input_edit_card_original);
        phraseOriginal.addTextChangedListener(new EditFinishListner() {
            @Override
            public void onEditFinish(Editable s) {
                save.setEnabled(readyToSave());
            }
        });

        originalLanguage = new EnumSpinnerWrapper<>((Spinner) findViewById(R.id.spinner_edit_card_original_language),
                new EnumAdapter<>(this, R.layout.list_item_phrase_language, TranslateLanguage.values()));
        originalLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                save.setEnabled(readyToSave());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        phraseTranslation = findViewById(R.id.input_edit_card_translation);
        phraseTranslation.addTextChangedListener(new EditFinishListner() {
            @Override
            public void onEditFinish(Editable s) {
                save.setEnabled(readyToSave());
            }
        });
        translationLanguage = new EnumSpinnerWrapper<>((Spinner) findViewById(R.id.spinner_edit_card_translation_language),
                new EnumAdapter<>(this, R.layout.list_item_phrase_language, TranslateLanguage.values()));
        translationLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                save.setEnabled(readyToSave());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        save = findViewById(R.id.button_edit_card_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.onSave(inputOf(phraseOriginal), originalLanguage.getSelected(),
                        inputOf(phraseTranslation), translationLanguage.getSelected());
                switchActivityTo(CardsDictionaryActivity.class);
            }
        });
        save.setEnabled(false);

        cancel = findViewById(R.id.button_edit_card_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void update(Void model) {
    }

    private boolean readyToSave() {
        return !inputOf(phraseOriginal).isEmpty()
                && !inputOf(phraseTranslation).isEmpty()
                && !originalLanguage.getSelected().equals(translationLanguage.getSelected());
    }

}
