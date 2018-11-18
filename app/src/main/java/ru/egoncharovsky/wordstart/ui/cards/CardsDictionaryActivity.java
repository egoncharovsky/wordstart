package ru.egoncharovsky.wordstart.ui.cards;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.widget.RelativeLayout;
import android.widget.TextView;
import ru.egoncharovsky.wordstart.R;
import ru.egoncharovsky.wordstart.ui.BaseActivity;
import ru.egoncharovsky.wordstart.ui.ModelView;
import ru.egoncharovsky.wordstart.ui.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardsDictionaryActivity extends BaseActivity implements ModelView<CardsDictionaryModel> {

    private CardsDictionaryController controller;

    private CardsDictionaryController.ItemActions itemActions() {
        if (isMultiSelectMode()) {
            return controller.multiSelect();
        } else {
            return controller.normalSelect();
        }
    }

    private ActionMode actionMode;

    private CardItemsAdapter adapter;

    private RecyclerView listCards;
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
    public void init(CardsDictionaryModel model) {
        this.adapter = new CardItemsAdapter();

        listCards = findViewById(R.id.list_cards);
        listCards.setLayoutManager(new LinearLayoutManager(this));
        listCards.setItemAnimator(new DefaultItemAnimator());
        listCards.addOnItemTouchListener(new RecyclerItemClickListener(this, listCards) {
            @Override
            public void onItemClick(View view, int position) {
                itemActions().onItemClick(adapter.get(position));
            }

            @Override
            public void onItemLongClick(View view, int position) {
                itemActions().onItemLongClick(adapter.get(position));
            }
        });
        listCards.setAdapter(adapter);

        addButton = findViewById(R.id.list_cards_button_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

        update(model);
    }

    @Override
    public void update(CardsDictionaryModel model) {
        if (model.hasSelected()) {
            activateMultiSelectMode();
        } else {
            activateNormalMode();
        }

        adapter.update(model.getCards());
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

    private class CardItemsAdapter extends RecyclerView.Adapter<CardItemsAdapter.ItemView> {

        private List<CardsDictionaryModel.Item> items = Collections.emptyList();

        private class ItemView extends RecyclerView.ViewHolder {
            TextView mainText;
            TextView subText;
            RelativeLayout layout;

            ItemView(View view) {
                super(view);
                mainText = view.findViewById(R.id.list_item_card_main_text);
                subText = view.findViewById(R.id.list_item_card_sub_text);
                layout = view.findViewById(R.id.list_item_card_layout);
            }
        }


        public void update(List<CardsDictionaryModel.Item> cards) {
            this.items = new ArrayList<>(cards);
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public ItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_card, parent, false);

            return new ItemView(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull ItemView holder, int position) {
            CardsDictionaryModel.Item item = items.get(position);

            holder.mainText.setText(item.getText());
            holder.subText.setText(item.getSubText());

            if (item.isSelected())
                holder.layout.setBackgroundColor(ContextCompat.getColor(CardsDictionaryActivity.this, R.color.list_item_selected_state));
            else
                holder.layout.setBackgroundColor(ContextCompat.getColor(CardsDictionaryActivity.this, R.color.list_item_normal_state));

        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        public CardsDictionaryModel.Item get(int position) {
            return items.get(position);
        }
    }
}
