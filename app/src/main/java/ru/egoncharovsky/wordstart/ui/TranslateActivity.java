package ru.egoncharovsky.wordstart.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import ru.egoncharovsky.wordstart.R;

public class TranslateActivity extends BaseActivity {

    private EditText inputTranslate;

    @Override
    public int getActivityViewId() {
        return R.layout.activity_translate;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        inputTranslate = findViewById(R.id.input_translate);
    }

    public void onTranslate(View view) {
        String text = getInputTranslateValue();
        if (!text.isEmpty()) {
            System.out.println(text);
        }
    }

    private String getInputTranslateValue() {
        Editable text = inputTranslate.getText();
        if (text != null) {
            return text.toString().trim().toLowerCase();
        }
        return "";
    }
}
