package ru.egoncharovsky.wordstart.ui.translate;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import ru.egoncharovsky.wordstart.R;
import ru.egoncharovsky.wordstart.ui.BaseActivity;
import ru.egoncharovsky.wordstart.ui.EnumAdapter;
import ru.egoncharovsky.wordstart.ui.ModelView;
import ru.egoncharovsky.wordstart.ui.shared.TranslateLanguage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

public class TranslateActivity extends BaseActivity implements ModelView<TranslateModel> {

    private TranslateController controller;

    private Spinner langFromChooser;
    private Spinner langToChooser;
    private EditText inputTranslate;
    private Button buttonTranslate;
    private ListView listTranslation;

    private HashMap<ImageView, TranslateModel.TranslationItem> buttonItemMap;

    private EnumAdapter<TranslateLanguage> langFromAdapter;
    private EnumAdapter<TranslateLanguage> langToAdapter;


    @Override
    public int getActivityViewId() {
        return R.layout.activity_translate;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        controller = new TranslateControllerImpl(this);
    }

    @Override
    public void init(TranslateModel model) {
        inputTranslate = findViewById(R.id.input_translate);
        listTranslation = findViewById(R.id.list_translation_words);

        langFromChooser = findViewById(R.id.spinner_translate_from);
        langFromChooser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                controller.onFromLanguageSelected(langFromAdapter.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        langFromAdapter = new EnumAdapter<>(
                this, R.layout.list_item_translate_language, TranslateLanguage.values());
        langFromChooser.setAdapter(langFromAdapter);

        langToChooser = findViewById(R.id.spinner_translate_to);
        langToChooser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                controller.onToLanguageSelected(langToAdapter.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        langToAdapter = new EnumAdapter<>(
                this, R.layout.list_item_translate_language, TranslateLanguage.values());
        langToChooser.setAdapter(langToAdapter);

        buttonTranslate = findViewById(R.id.button_translate);

        update(model);
    }

    @Override
    public void update(TranslateModel model) {
        buttonItemMap = new HashMap<>();

        inputTranslate.setText(model.getTranslatedText());
        listTranslation.setAdapter(translationWordsAdapter(model.getItems()));

        if (model.getFrom() != null) {
            langFromChooser.setSelection(langFromAdapter.position(model.getFrom()));
        }
        if (model.getTo() != null) {
            langToChooser.setSelection(langToAdapter.position(model.getTo()));
        }

        buttonTranslate.setEnabled(!Objects.equals(model.getFrom(), model.getTo()));
    }

    public void onTranslate(View view) {
        String input = inputOf(inputTranslate);
        if (!input.isEmpty()) {
            controller.onTranslate(input);
            // todo if no variants - print message
        }
    }

    public void onCreateCard(View view) {
        TranslateModel.TranslationItem item = getClickedItem(view);

        controller.onCreateCard(item);
    }

    public void onSwapLanguage(View view) {
        controller.onSwapLanguage();
    }

    private TranslateModel.TranslationItem getClickedItem(View buttonView) {
        ImageView button = buttonView.findViewById(R.id.button_create_card);
        return buttonItemMap.get(button);
    }

    private ListAdapter translationWordsAdapter(Set<TranslateModel.TranslationItem> items) {
        return new ArrayAdapter<TranslateModel.TranslationItem>(
                TranslateActivity.this,
                R.layout.list_item_translation_word,
                new ArrayList<>(items)) {

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.list_item_translation_word, parent, false);
                }
                TranslateModel.TranslationItem item = getItem(position);

                TextView textView = convertView.findViewById(R.id.list_item_translation_word);
                textView.setText(Objects.requireNonNull(item).getPhrase());

                ImageView button = convertView.findViewById(R.id.button_create_card);
                if (item.isMarked()) {
                    button.setClickable(false);
                    button.setImageResource(R.drawable.baseline_done_black_18dp);
                } else {
                    button.setImageResource(R.drawable.baseline_add_circle_outline_black_18dp);
                }
                buttonItemMap.put(button, item);

                return convertView;
            }
        };
    }
}
