package ru.egoncharovsky.wordstart.ui.translate;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import ru.egoncharovsky.wordstart.R;
import ru.egoncharovsky.wordstart.ui.BaseActivity;
import ru.egoncharovsky.wordstart.ui.ModelView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

public class TranslateActivity extends BaseActivity implements ModelView<TranslateModel> {

    private TranslateController controller;

    private EditText inputTranslate;
    private ListView listTranslation;
    private HashMap<ImageView, TranslateModel.TranslationItem> buttonItemMap;

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

        update(model);
    }

    @Override
    public void update(TranslateModel model) {
        buttonItemMap = new HashMap<>();

        inputTranslate.setText(model.getTranslatedText());
        listTranslation.setAdapter(translationWordsAdapter(model.getItems()));
    }

    public void onTranslate(View view) {
        String input = getTranslateInput();
        if (!input.isEmpty()) {
            controller.onTranslate(input);
            // todo if no variants - print message
        }
    }

    public void onCreateCard(View view) {
        TranslateModel.TranslationItem item = getClickedItem(view);

        controller.onCreateCard(item);
    }

    private String getTranslateInput() {
        Editable text = inputTranslate.getText();
        if (text != null) {
            return text.toString();
        }
        return "";
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
