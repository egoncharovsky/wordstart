package ru.egoncharovsky.wordstart.ui.pack;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import ru.egoncharovsky.wordstart.R;
import ru.egoncharovsky.wordstart.ui.BaseActivityOld;
import ru.egoncharovsky.wordstart.ui.EditFinishListner;
import ru.egoncharovsky.wordstart.ui.ModelView;
import ru.egoncharovsky.wordstart.ui.cards.CardsList;
import ru.egoncharovsky.wordstart.ui.cards.CardsListActionsHandler;
import ru.egoncharovsky.wordstart.ui.cards.CardsListView;

public class EditCardsPackActivity extends BaseActivityOld implements ModelView<EditCardsPackModel> {

    public static String CARD_PACK_NAME = "cardPack.name";

    private EditCardsController controller;

    private EditText packName;
    private TextView cardsTotal;
    private CardsListView cardsListView;
    private Button cancel;
    private Button save;

    @Override
    public int getActivityViewId() {
        return R.layout.activity_edit_pack;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        controller = new EditCardsControllerImpl(this);
    }

    @Override
    public void init(EditCardsPackModel model) {
        packName = findViewById(R.id.input_edit_pack_name);
        packName.addTextChangedListener(new EditFinishListner() {
            @Override
            public void onEditFinish(Editable s) {
                save.setEnabled(readyToSave());
            }
        });
        cardsTotal = findViewById(R.id.text_edit_pack_cards_total);
        cardsListView = new CardsListView(this, (RecyclerView) findViewById(R.id.list_edit_pack_cards), new CardsListActionsHandler() {
            @Override
            public CardsListActions itemActions() {
                return new CardsListActions() {
                    @Override
                    public void onItemClick(CardsList.Item item) {

                    }

                    @Override
                    public void onItemLongClick(CardsList.Item item) {

                    }
                };
            }
        });

        cancel = findViewById(R.id.button_edit_pack_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        save = findViewById(R.id.button_edit_pack_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        update(model);
    }

    @Override
    public void update(EditCardsPackModel model) {
        packName.setText(model.getPackName());
        cardsTotal.setText(getResources().getString(R.string.edit_pack_cards_total, model.getCardsTotal()));
        cardsListView.update(model.getCards());
    }

    private boolean readyToSave() {
        return !inputOf(packName).isEmpty();
    }
}
