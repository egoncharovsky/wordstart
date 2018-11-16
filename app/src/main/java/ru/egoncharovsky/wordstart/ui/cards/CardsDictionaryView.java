package ru.egoncharovsky.wordstart.ui.cards;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import ru.egoncharovsky.wordstart.R;
import ru.egoncharovsky.wordstart.ui.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardsDictionaryView {

    private final CardsDictionaryActivity context;
    private final CardItemsAdapter adapter;

    private final RecyclerView listCards;
    private final FloatingActionButton addButton;

    CardsDictionaryView(final CardsDictionaryActivity activity) {
        this.context = activity;
        this.adapter = new CardItemsAdapter();

        listCards = activity.findViewById(R.id.list_cards);
        listCards.setLayoutManager(new LinearLayoutManager(activity));
        listCards.setItemAnimator(new DefaultItemAnimator());
        listCards.addOnItemTouchListener(new RecyclerItemClickListener(activity, listCards) {
            @Override
            public void onItemClick(View view, int position) {
                activity.onItemClick(adapter.get(position));
            }

            @Override
            public void onItemLongClick(View view, int position) {
                activity.onItemLongClick(adapter.get(position));
            }
        });
        listCards.setAdapter(adapter);

        addButton = activity.findViewById(R.id.list_cards_button_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    void update(CardsDictionaryModel model) {
        adapter.update(model.getCards());
    }

    private class CardItemsAdapter extends RecyclerView.Adapter<CardItemsAdapter.ItemView> {

        private List<CardsDictionaryModel.CardItem> items = Collections.emptyList();

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


        public void update(List<CardsDictionaryModel.CardItem> cards) {
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
            CardsDictionaryModel.CardItem item = items.get(position);

            holder.mainText.setText(item.getText());
            holder.subText.setText(item.getSubText());

            if (item.isSelected())
                holder.layout.setBackgroundColor(ContextCompat.getColor(context, R.color.list_item_selected_state));
            else
                holder.layout.setBackgroundColor(ContextCompat.getColor(context, R.color.list_item_normal_state));

        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        public CardsDictionaryModel.CardItem get(int position) {
            return items.get(position);
        }
    }
}
