package ru.egoncharovsky.wordstart.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import ru.egoncharovsky.wordstart.domain.LearningCard;

import java.util.List;

public class LearningCardAdapter extends IdentifiebleListAdapter<LearningCard> {

    public LearningCardAdapter(List<LearningCard> items) {
        super(items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
