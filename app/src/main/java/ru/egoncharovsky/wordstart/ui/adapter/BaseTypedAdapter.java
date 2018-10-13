package ru.egoncharovsky.wordstart.ui.adapter;


public abstract class BaseTypedAdapter<ItemType>
        extends android.widget.BaseAdapter {

    @Override
    public Object getItem(int position) {
        return getTypedItem(position);
    }

    public abstract ItemType getTypedItem(int position);
}
