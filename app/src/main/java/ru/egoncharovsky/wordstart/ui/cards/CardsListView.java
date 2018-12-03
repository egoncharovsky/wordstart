package ru.egoncharovsky.wordstart.ui.cards;

import android.content.Context;
import android.support.annotation.NonNull;
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

public class CardsListView {

    private CardsListAdapter adapter;
    private RecyclerView listView;

    public CardsListView(Context context, RecyclerView listView) {
        this.adapter = new CardsListView.CardsListAdapter(context);
        this.listView = listView;

        this.listView.setLayoutManager(new LinearLayoutManager(context));
        this.listView.setItemAnimator(new DefaultItemAnimator());

        this.listView.setAdapter(adapter);
    }

    public CardsListView(Context context, RecyclerView listView, final CardsListActionsHandler actionsHandler) {
        this(context, listView);

        this.listView.addOnItemTouchListener(new RecyclerItemClickListener(context, this.listView) {
            @Override
            public void onItemClick(View view, int position) {
                actionsHandler.itemActions().onItemClick(adapter.get(position));
            }

            @Override
            public void onItemLongClick(View view, int position) {
                actionsHandler.itemActions().onItemLongClick(adapter.get(position));
            }
        });
    }

    public void update(List<CardsList.Item> items) {
        adapter.update(items);
    }

    private class CardsListAdapter extends RecyclerView.Adapter<CardsListAdapter.ItemView> {

        private Context context;
        private List<CardsList.Item> items = Collections.emptyList();

        public CardsListAdapter(Context context) {
            this.context = context;
        }

        public class ItemView extends RecyclerView.ViewHolder {
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


        public void update(List<CardsList.Item> cards) {
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
            CardsList.Item item = items.get(position);

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

        public CardsList.Item get(int position) {
            return items.get(position);
        }
    }
}
