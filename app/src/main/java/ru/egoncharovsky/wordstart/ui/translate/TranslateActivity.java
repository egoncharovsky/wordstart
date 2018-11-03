package ru.egoncharovsky.wordstart.ui.translate;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import ru.egoncharovsky.wordstart.R;
import ru.egoncharovsky.wordstart.domain.learning.LearningCard;
import ru.egoncharovsky.wordstart.domain.learning.LearningCardsService;
import ru.egoncharovsky.wordstart.domain.word.Language;
import ru.egoncharovsky.wordstart.domain.word.Translation;
import ru.egoncharovsky.wordstart.domain.word.TranslationService;
import ru.egoncharovsky.wordstart.domain.word.Word;
import ru.egoncharovsky.wordstart.ui.BaseActivity;

import java.util.*;

public class TranslateActivity extends BaseActivity {

    private TranslationService translationService;

    private LearningCardsService cardsService;

    private TranslateView translateView;
    private TranslateModel model;

    @Override
    public int getActivityViewId() {
        return R.layout.activity_translate;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        translationService = TranslationService.getInstace();
        cardsService = LearningCardsService.getInstance();

        translateView = new TranslateView();
        model = new TranslateModel(Language.RU, Language.EN);
    }

    public void onTranslate(View view) {
        String input = translateView.getTranslateInput();
        if (!input.isEmpty()) {
            Word word = new Word(input, model.getFrom());

            Translation translation = translationService.translate(word, model.getTo());
            Set<LearningCard> cards = new HashSet<>(cardsService.getCardsFor(translation));

            model = new TranslateModel(translation, cards);
            translateView.update(model);
        }
    }

    public void onCreateCard(View view) {
        TranslateModel.TranslationItem item = translateView.getClickedItem(view);

        item.setMarked(true);
        translateView.update(model);
    }

    private class TranslateModel {
        private final Language from;
        private final Language to;

        private final String translatedText;
        private final Set<TranslationItem> items;

        public TranslateModel(Language from, Language to) {
            this.from = from;
            this.to = to;

            translatedText = "";
            items = Collections.emptySet();
        }

        public TranslateModel(Translation translation, Collection<LearningCard> cards) {
            from = translation.getOriginalLanguage();
            to = translation.getTranslationLanguage();

            translatedText = translation.getOriginalWord().getValue();

            items = new LinkedHashSet<>();
            for (Translation.Variant variant : translation.getTranslationVariants()) {
                TranslationItem item = new TranslationItem(variant);

                if (cards != null) {
                    for (LearningCard card : cards) {
                        if (card.hasTranslationVariant(variant)) {
                            item.setMarked(true);
                        }
                    }
                }

                items.add(item);
            }
        }

        public Language getFrom() {
            return from;
        }

        public Language getTo() {
            return to;
        }

        public String getTranslatedText() {
            return translatedText;
        }

        public Set<TranslationItem> getItems() {
            return new LinkedHashSet<>(items);
        }

        private class TranslationItem {
            private final String word;
            private boolean marked;

            public TranslationItem(Translation.Variant variant) {
                this.word = variant.getWord().getValue();
                marked = false;
            }

            public String getWord() {
                return word;
            }

            public boolean isMarked() {
                return marked;
            }

            public void setMarked(boolean marked) {
                this.marked = marked;
            }

            @Override
            public String toString() {
                return "TranslationItem{" +
                        "word='" + word + '\'' +
                        ", marked=" + marked +
                        '}';
            }
        }
    }

    private class TranslateView {
        private EditText inputTranslate;

        private ListView listTranslation;

        private HashMap<ImageButton, TranslateModel.TranslationItem> buttonItemMap;

        public TranslateView() {
            inputTranslate = findViewById(R.id.input_translate);
            listTranslation = findViewById(R.id.list_translation_words);
        }

        public void update(TranslateModel model) {
            buttonItemMap = new HashMap<>();

            inputTranslate.setText(model.getTranslatedText());
            listTranslation.setAdapter(translationWordsAdapter(model.getItems()));
        }

        public String getTranslateInput() {
            Editable text = inputTranslate.getText();
            if (text != null) {
                return text.toString();
            }
            return "";
        }

        public TranslateModel.TranslationItem getClickedItem(View buttonView) {
            ImageButton button = buttonView.findViewById(R.id.button_create_card);
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
                    textView.setText(Objects.requireNonNull(item).getWord());

                    ImageButton button = convertView.findViewById(R.id.button_create_card);
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


}
