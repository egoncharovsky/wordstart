package ru.egoncharovsky.wordstart.ui.adapter;

import ru.egoncharovsky.wordstart.domain.base.Identifieble;

import java.util.List;

public abstract class IdentifiebleListAdapter<ItemType extends Identifieble>
        extends BaseTypedAdapter<ItemType> {

    protected List<ItemType> items;

    public IdentifiebleListAdapter(List<ItemType> items) {
        this.items = items;
    }

    @Override
    public ItemType getTypedItem(int position) {
        return items.get(position);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public long getItemId(int position) {
        return getTypedItem(position).getId();
    }
}
