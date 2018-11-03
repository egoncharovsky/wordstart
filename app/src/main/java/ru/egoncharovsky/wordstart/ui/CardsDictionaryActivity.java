package ru.egoncharovsky.wordstart.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import ru.egoncharovsky.wordstart.R;
import ru.egoncharovsky.wordstart.domain.learning.LearningCard;
import ru.egoncharovsky.wordstart.domain.learning.LearningCardsService;
import ru.egoncharovsky.wordstart.repository.LearningCardRepositoryImpl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class CardsDictionaryActivity extends BaseActivity {

    private LearningCardsService cardsService = new LearningCardsService(new LearningCardRepositoryImpl());

    private CardsDictionaryView cardsDictionaryView;
    private Model model;

    @Override
    public int getActivityViewId() {
        return R.layout.activity_cards_dictionary;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cardsDictionaryView = new CardsDictionaryView();
        model = new Model(cardsService.getAll());

        cardsDictionaryView.update(model);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.cards_dictionary, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class Model {
        private final List<CardItem> cards = new LinkedList<>();

        private Model(List<LearningCard> cards) {
            for (LearningCard card : cards) {
                this.cards.add(new CardItem(card));
            }
        }

        public List<CardItem> getCards() {
            return new ArrayList<>(cards);
        }

        private class CardItem {
            private final LearningCard card;

            private CardItem(LearningCard card) {
                this.card = card;
            }

            public String getText() {
                return card.getOriginalWord().getValue();
            }

            public String getSubText() {
                return card.getTranslationWord().getValue();
            }
        }
    }

    private class CardsDictionaryView {

        private final ListView listCards;

        public CardsDictionaryView() {
            listCards = findViewById(R.id.list_cards);

            /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        }

        public void update(Model model) {
            listCards.setAdapter(cardsAdapter(model.getCards()));
        }
    }

    private ListAdapter cardsAdapter(List<Model.CardItem> cards) {
        return new ArrayAdapter<Model.CardItem>(this, R.layout.list_item_card, cards) {

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.list_item_card, parent, false);
                }
                Model.CardItem item = getItem(position);

                TextView mainText = convertView.findViewById(R.id.list_item_card_main_text);
                mainText.setText(Objects.requireNonNull(item).getText());

                TextView subText = convertView.findViewById(R.id.list_item_card_sub_text);
                subText.setText(Objects.requireNonNull(item).getSubText());

                return convertView;
            }
        };
    }
}
