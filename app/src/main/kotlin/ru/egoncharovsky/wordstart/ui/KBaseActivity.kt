package ru.egoncharovsky.wordstart.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.app_side_menu.*
import kotlinx.android.synthetic.main.app_top_toolbar.*
import ru.egoncharovsky.wordstart.ui.cards.CardsDictionaryActivity
import ru.egoncharovsky.wordstart.ui.pack.CardPacksActivity
import ru.egoncharovsky.wordstart.ui.pack.EditCardsPackActivity
import ru.egoncharovsky.wordstart.ui.translate.TranslateActivity

abstract class KBaseActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    abstract fun content(): View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(base_drawer_layout)

        base_content.addView(content())

        setSupportActionBar(toolbar_top)
        app_side_menu.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(
                this, base_drawer_layout, toolbar_top, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        base_drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        //        Locale locale = new Locale("ru");
        //        Locale.setDefault(locale);
        //        Configuration config = getBaseContext().getResources().getConfiguration();
        //        config.locale = locale;
        //        getBaseContext().getResources().updateConfiguration(config,
        //                getBaseContext().getResources().getDisplayMetrics());
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_cards_dictionary -> switchActivityTo(CardsDictionaryActivity::class.java)
            R.id.nav_translate -> switchActivityTo(TranslateActivity::class.java)
            R.id.nav_tmp_create_pack -> switchActivityTo(EditCardsPackActivity::class.java)
            R.id.nav_card_packs -> switchActivityTo(CardPacksActivity::class.java)
        }

        base_drawer_layout.closeDrawer(GravityCompat.START)

        return true
    }

    override fun onBackPressed() {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)

        when (drawer.isDrawerOpen(GravityCompat.START)) {
            true -> drawer.closeDrawer(GravityCompat.START)
            false -> super.onBackPressed()
        }
    }
}

fun EditText.input(): String = this.text?.toString() ?: ""

fun <ActivityType : Activity> Activity.switchActivityTo(activity: Class<ActivityType>) {
    if (this.javaClass != activity) {
        startActivity(Intent(this, activity))
    }
}