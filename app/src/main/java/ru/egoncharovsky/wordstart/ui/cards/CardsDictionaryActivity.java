package ru.egoncharovsky.wordstart.ui.cards;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import ru.egoncharovsky.wordstart.R;
import ru.egoncharovsky.wordstart.ui.BaseActivityOld;
import ru.egoncharovsky.wordstart.ui.ModelView;

public class CardsDictionaryActivity extends BaseActivityOld implements ModelView<CardsList> {

    private CardsDictionaryController controller;

    private ActionMode actionMode;

    private CardsListView cardsList;

    private FloatingActionButton addButton;

    @Override
    public int getActivityViewId() {
        return R.layout.activity_cards_dictionary;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.controller = new CardsDictionaryControllerImpl(this);
    }

    @Override
    public void init(CardsList model) {
        cardsList = new CardsListView(this,
                (RecyclerView) findViewById(R.id.list_cards),
                new CardsListActionsHandler() {
                    @Override
                    public CardsListActions itemActions() {
                        if (isMultiSelectMode()) {
                            return controller.multiSelect();
                        } else {
                            return controller.normalSelect();
                        }
                    }
                });

        addButton = findViewById(R.id.list_cards_button_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                switchActivityTo(EditCardActivity.class);
            }
        });

        update(model);
    }

    @Override
    public void update(CardsList model) {
        if (model.hasSelected()) {
            activateMultiSelectMode();
        } else {
            activateNormalMode();
        }

        cardsList.update(model.getCards());
    }

    private void activateMultiSelectMode() {
        if (actionMode == null) {
            actionMode = startSupportActionMode(actionModeCallback);
            addButton.setVisibility(View.INVISIBLE);
        }
    }

    private void activateNormalMode() {
        if (actionMode != null) {
            actionMode.finish();
            addButton.setVisibility(View.VISIBLE);
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
                    controller.onDeleteCards();
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            actionMode = null;
            controller.onBackToNormalMode();
        }
    };

}
