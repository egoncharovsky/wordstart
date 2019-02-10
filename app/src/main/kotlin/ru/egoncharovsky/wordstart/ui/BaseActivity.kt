package ru.egoncharovsky.wordstart.ui

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_base.*
import ru.egoncharovsky.wordstart.R
import ru.egoncharovsky.wordstart.ui.cards.CardsDictionaryActivity
import ru.egoncharovsky.wordstart.ui.pack.CardPacksActivity
import ru.egoncharovsky.wordstart.ui.translate.TranslateActivity

abstract class BaseActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    @LayoutRes
    abstract fun contentViewId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_base)
        base_content.addView(layoutInflater?.inflate(contentViewId(), null))

        setSupportActionBar(base_toolbar)
        base_navigation_menu.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(
                this, base_drawer_layout, base_toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        base_drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_cards_dictionary -> switchTo(CardsDictionaryActivity::class.java)
            R.id.nav_translate -> switchTo(TranslateActivity::class.java)
            R.id.nav_card_packs -> switchTo(CardPacksActivity::class.java)
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