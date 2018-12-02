package ru.egoncharovsky.wordstart.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class EnumAdapter<E extends EnumString<?>> extends ArrayAdapter<String> {

    private List<String> strings;

    private Map<E, Integer> value2position;
    private Map<Integer, E> position2value;

    public EnumAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public EnumAdapter(@NonNull Context context, int resource, E[] values) {
        this(context, resource);
        update(values);
    }

    @SuppressWarnings("ConstantConditions")
    public int position(E value) {
        return value2position.get(value);
    }

    public E get(int position) {
        return position2value.get(position);
    }

    public void update(E[] enumerated) {
        initValues(enumerated);

        clear();
        addAll(strings);
        notifyDataSetChanged();
    }

    @SuppressLint("UseSparseArrays")
    protected void initValues(E[] enumerated) {
        this.strings = new LinkedList<>();
        this.value2position = new HashMap<>();
        this.position2value = new HashMap<>();

        for (E value : enumerated) {
            int pos = addString(getContext().getResources().getString(value.getString()));

            value2position.put(value, pos);
            position2value.put(pos, value);
        }
    }

    protected final int addString(@NonNull String s) {
        strings.add(s);
        return strings.size() - 1;
    }
}
