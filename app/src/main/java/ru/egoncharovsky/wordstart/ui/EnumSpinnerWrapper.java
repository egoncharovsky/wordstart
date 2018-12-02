package ru.egoncharovsky.wordstart.ui;

import android.widget.AdapterView;
import android.widget.Spinner;

public class EnumSpinnerWrapper<E extends EnumString<?>> {

    private Spinner spinner;
    private EnumAdapter<E> adapter;

    public EnumSpinnerWrapper(Spinner spinner, EnumAdapter<E> adapter) {
        this.spinner = spinner;
        this.adapter = adapter;
        spinner.setAdapter(adapter);
    }

    public E getSelected() {
        return adapter.get(spinner.getSelectedItemPosition());
    }

    public void setSelected(E value) {
        spinner.setSelection(adapter.position(value));
    }

    public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener listener) {
        spinner.setOnItemSelectedListener(listener);
    }
}
