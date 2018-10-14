package ru.egoncharovsky.wordstart.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.widget.*;
import ru.egoncharovsky.wordstart.R;
import ru.egoncharovsky.wordstart.domain.LearningCard;
import ru.egoncharovsky.wordstart.domain.service.LearningCardsDictionaryService;

import java.util.List;

public class CardsDictionary extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards_dictionary);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        LearningCardsDictionaryService cardsService = LearningCardsDictionaryService.getInstace();
        List<LearningCard> cards = cardsService.getAll();

        ListView cardsView = findViewById(R.id.list_cards);
        cardsView.setAdapter(createCardsAdapter(cards));

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private ListAdapter createCardsAdapter(List<LearningCard> cards) {
        final LayoutInflater inflater = LayoutInflater.from(this);

        return new ArrayAdapter<LearningCard>(this, R.layout.list_item_card, cards) {

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    convertView = inflater.inflate(R.layout.list_item_card, parent, false);
                }

                TextView textView = convertView.findViewById(R.id.list_item_card_word);
                textView.setText(getItem(position).getWord().getValue()+ " - " + getItem(position).getTranslationWord().getValue());

                return convertView;
            }
        };
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        boolean b = super.onNavigationItemSelected(item);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return b;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.cards_dictionary, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
