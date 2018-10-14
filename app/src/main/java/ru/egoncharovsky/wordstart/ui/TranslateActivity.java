package ru.egoncharovsky.wordstart.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import ru.egoncharovsky.wordstart.R;
import ru.egoncharovsky.wordstart.domain.Language;
import ru.egoncharovsky.wordstart.domain.Translation;
import ru.egoncharovsky.wordstart.domain.Word;
import ru.egoncharovsky.wordstart.domain.service.TranslationService;

import java.util.List;

public class TranslateActivity extends BaseActivity {

    private TranslationService translationService;

    private EditText inputTranslate;

    private ListView listTranslation;

    @Override
    public int getActivityViewId() {
        return R.layout.activity_translate;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        translationService = TranslationService.getInstace();

        inputTranslate = findViewById(R.id.input_translate);
        listTranslation = findViewById(R.id.list_translation_words);
    }

    public void onTranslate(View view) {
        String text = getInputTranslateValue();
        if (!text.isEmpty()) {
            Word word = new Word(text, Language.EN);

            Translation translation = translationService.translate(word, Language.RU);
            listTranslation.setAdapter(translationWordsAdapter(translation.getTranslationWords()));
        }
    }

    private String getInputTranslateValue() {
        Editable text = inputTranslate.getText();
        if (text != null) {
            return text.toString().trim().toLowerCase();
        }
        return "";
    }

    private ListAdapter translationWordsAdapter(List<Word> words) {
        return new ArrayAdapter<Word>(this, R.layout.list_item_translation_word, words) {

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.list_item_card, parent, false);
                }
                Word word = getItem(position);

                TextView textView = convertView.findViewById(R.id.list_item_card_word);
                textView.setText(word.getValue());

                return convertView;
            }
        };
    }
}
