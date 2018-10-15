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
import ru.egoncharovsky.wordstart.domain.LearningCard;
import ru.egoncharovsky.wordstart.domain.Translation;
import ru.egoncharovsky.wordstart.domain.Word;
import ru.egoncharovsky.wordstart.domain.service.LearningCardsService;
import ru.egoncharovsky.wordstart.domain.service.TranslationService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TranslateActivity extends BaseActivity {

    private TranslationService translationService;

    private LearningCardsService cardsService;

    private EditText inputTranslate;

    private ListView listTranslation;

    private Translation translation;

    private Set<LearningCard> cards;

    @Override
    public int getActivityViewId() {
        return R.layout.activity_translate;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        translationService = TranslationService.getInstace();
        cardsService = LearningCardsService.getInstance();

        inputTranslate = findViewById(R.id.input_translate);
        listTranslation = findViewById(R.id.list_translation_words);
    }

    public void onTranslate(View view) {
        String text = getInputTranslateValue();
        if (!text.isEmpty()) {
            Word word = new Word(text, Language.EN);

            translation = translationService.translate(word, Language.RU);
            cards = new HashSet<>(cardsService.getCardsFor(translation));

            listTranslation.setAdapter(translationWordsAdapter(translation.getTranslationWords()));
        }
    }

    public void onCreateCard(View view) {
        ImageButton button = view.findViewById(R.id.button_create_card);

        button.setImageResource(R.drawable.baseline_turned_in_black_18dp);
    }

    private String getInputTranslateValue() {
        Editable text = inputTranslate.getText();
        if (text != null) {
            return text.toString().trim().toLowerCase();
        }
        return "";
    }

    private boolean cardsContains(Word translationWord) {
        for (LearningCard card : cards) {
            if (card.getTranslationWord().equals(translationWord))
                return true;
        }
        return false;
    }

    private ListAdapter translationWordsAdapter(List<Word> words) {
        return new ArrayAdapter<Word>(this, R.layout.list_item_translation_word, words) {

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.list_item_translation_word, parent, false);
                }
                Word word = getItem(position);

                TextView textView = convertView.findViewById(R.id.list_item_translation_word);
                textView.setText(word.getValue());

                ImageButton button = convertView.findViewById(R.id.button_create_card);
                if (cardsContains(word)) {
                    button.setImageResource(R.drawable.baseline_turned_in_black_18dp);
                } else {
                    button.setImageResource(R.drawable.baseline_turned_in_not_black_18dp);
                }

                return convertView;
            }
        };
    }
}
