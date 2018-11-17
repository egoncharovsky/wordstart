package ru.egoncharovsky.wordstart.ui.cards;

import android.os.Bundle;
import android.support.v7.view.ActionMode;
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

    private ActionMode actionMode;

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

    public void onItemClick(CardsDictionaryModel.CardItem item) {

        if (isMultiSelectMode()) {
            model.toggleSelect(item);

            if (!model.hasSelected()) {
                actionMode.finish();
            }

            view.update(model);
        }
    }

    public void onItemLongClick(CardsDictionaryModel.CardItem item) {
        model.toggleSelect(item);

        activateMultiSelectMode();

        view.update(model);
    }

    public void onDeleteCards() {
        for (CardsDictionaryModel.CardItem item : model.getSelected()) {
            cardsService.delete(item.getCardId());
        }

        model = new CardsDictionaryModel(cardsService.getAll());

        if (!model.hasSelected()) {
            activateNormalMode();
        }

        view.update(model);
    }

    public void onCreateCard() {
        System.out.println("created");
    }

    private void activateMultiSelectMode() {
        if (actionMode == null) {
            actionMode = startSupportActionMode(actionModeCallback);
        }
    }

    private void activateNormalMode() {
        if (actionMode != null) {
            actionMode.finish();
        }
    }

    private boolean isMultiSelectMode() {
        return actionMode != null;
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

    private ActionMode.Callback actionModeCallback = new ActionMode.Callback() {

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                // Inflate a menu resource providing context menu items
                mode.getMenuInflater().inflate(R.menu.menu_multi_select, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false; // Return false if nothing is done
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_delete:
                        onDeleteCards();
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                actionMode = null;
                model.clearSelected();
                view.update(model);
            }
        };

}
