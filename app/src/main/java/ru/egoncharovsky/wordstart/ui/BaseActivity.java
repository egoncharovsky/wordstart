package ru.egoncharovsky.wordstart.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.MenuItem;
import android.widget.EditText;
import ru.egoncharovsky.wordstart.R;
import ru.egoncharovsky.wordstart.ui.cards.CardsDictionaryActivity;
import ru.egoncharovsky.wordstart.ui.pack.CardPacksActivity;
import ru.egoncharovsky.wordstart.ui.pack.EditCardsPackActivity;
import ru.egoncharovsky.wordstart.ui.translate.TranslateActivity;

public abstract class BaseActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    protected NavigationView menu;

    /**
     * @return {@link R.layout}
     */
    public abstract int getActivityViewId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getActivityViewId());

        Toolbar toolbar = findViewById(R.id.toolbar_top);
        setSupportActionBar(toolbar);

        menu = findViewById(R.id.app_side_menu);
        menu.setNavigationItemSelectedListener(this);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

//        Locale locale = new Locale("ru");
//        Locale.setDefault(locale);
//        Configuration config = getBaseContext().getResources().getConfiguration();
//        config.locale = locale;
//        getBaseContext().getResources().updateConfiguration(config,
//                getBaseContext().getResources().getDisplayMetrics());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_cards_dictionary:
                switchActivityTo(CardsDictionaryActivity.class);
                break;
            case R.id.nav_translate:
                switchActivityTo(TranslateActivity.class);
                break;
            case R.id.nav_card_packs:
                switchActivityTo(CardPacksActivity.class);
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    protected <ActivityType extends Activity> void switchActivityTo(Class<ActivityType> activity) {
        if (getClass() != activity) {
            startActivity(new Intent(this, activity));
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    protected String inputOf(EditText editText) {//todo move to edit text wrapper?
        Editable text = editText.getText();
        if (text != null) {
            return text.toString();
        }
        return "";
    }
}
