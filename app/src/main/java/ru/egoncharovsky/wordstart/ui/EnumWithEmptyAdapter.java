package ru.egoncharovsky.wordstart.ui;

import android.content.Context;
import android.support.annotation.NonNull;

@Deprecated
public class EnumWithEmptyAdapter<E extends EnumString<?>> extends EnumAdapter<E> {

    private String emptyValue;
    private int emptyPosition;

    public EnumWithEmptyAdapter(@NonNull Context context, int resource, @NonNull String placeholder) {
        super(context, resource);
        emptyValue = placeholder;
    }

    public EnumWithEmptyAdapter(@NonNull Context context, int resource, E[] values, @NonNull String placeholder) {
        this(context, resource, placeholder);
        update(values);
    }

    public int positionEmpty() {
        return emptyPosition;
    }

    @Override
    public E get(int position) {
        if (position == emptyPosition) {
            return null;
        } else {
            return super.get(position);
        }
    }

    @Override
    protected void initValues(E[] enumerated) {
        super.initValues(enumerated);
        emptyPosition = super.addString(emptyValue);
    }
}
