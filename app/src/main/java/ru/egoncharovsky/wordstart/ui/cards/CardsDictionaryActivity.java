package ru.egoncharovsky.wordstart.ui.cards;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import ru.egoncharovsky.wordstart.R;
import ru.egoncharovsky.wordstart.domain.learning.LearningCardsService;
import ru.egoncharovsky.wordstart.repository.LearningCardRepositoryImpl;
import ru.egoncharovsky.wordstart.ui.BaseActivity;

public class CardsDictionaryActivity extends BaseActivity {

    private LearningCardsService cardsService = new LearningCardsService(new LearningCardRepositoryImpl());

    private CardsDictionaryView view;
    private CardsDictionaryModel model;

    @Override
    public int getActivityViewId() {
        return R.layout.activity_cards_dictionary;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        view = new CardsDictionaryView(this);
        model = new CardsDictionaryModel(cardsService.getAll());

        view.update(model);
    }

    public void onToggleSelect(CardsDictionaryModel.CardItem item) {
        model.toggleSelect(item);

        view.update(model);
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
}
