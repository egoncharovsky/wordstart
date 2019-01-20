package ru.egoncharovsky.wordstart.ui

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.navigationView
import org.jetbrains.anko.design.themedAppBarLayout
import org.jetbrains.anko.support.v4.drawerLayout
import ru.egoncharovsky.wordstart.R

abstract class BaseAnkoActivity : AppCompatActivity(), AnkoComponent<BaseAnkoActivity> {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        object : AppToolbarAndMenuDecorator<BaseAnkoActivity>() {
            override fun <T> AnkoContext<T>.content(ui: AnkoContext<T>): View = component(ui)
        }.setContentView(this)

        val toolbar = findOptional<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
    }

    abstract fun <T> AnkoContext<T>.component(ui: AnkoContext<T>): View

    override fun createView(ui: AnkoContext<BaseAnkoActivity>): View = with(ui) {
        component(ui)
    }

    private abstract class AppToolbarAndMenuDecorator<T> : AnkoComponent<T> {

        abstract fun <T> AnkoContext<T>.content(ui: AnkoContext<T>): View

        override fun createView(ui: AnkoContext<T>): View = with(ui) {
            drawerLayout {
                id = R.id.drawer_layout
                fitsSystemWindows = true

                coordinatorLayout {
                    //                fitsSystemWindows = true

                    themedAppBarLayout(R.style.AppTheme_AppBarOverlay) {
                        toolbar {
                            lparams(width = matchParent, height = matchParent)
                            id = R.id.toolbar
                            popupTheme = R.style.AppTheme_PopupOverlay
                        }
                    }.lparams(width = matchParent, height = wrapContent)

                    with(AnkoContext.createDelegate(
                            relativeLayout().lparams(width = matchParent, height = matchParent) {
                                behavior = AppBarLayout.ScrollingViewBehavior()
                            }
                    )) {
                        content(ui)
                    }
                }.lparams(width = matchParent, height = matchParent)

                navigationView {
                    fitsSystemWindows = true

                    verticalLayout {
                        lparams(R.style.ThemeOverlay_AppCompat_Dark)
                        backgroundResource = R.drawable.side_nav_bar
                        gravity = Gravity.BOTTOM
                        orientation = LinearLayout.VERTICAL
                        padding = dip(20)

                        imageView(R.mipmap.ic_launcher_round) {
                            padding = dip(5)
                        }.lparams(width = wrapContent, height = wrapContent)

                        textView(R.string.nav_header_title) {
                            padding = R.dimen.nav_header_vertical_spacing
                        }.lparams(width = matchParent, height = wrapContent)

                        textView(R.string.nav_header_subtitle) {
                            textColor = Color.WHITE
                        }.lparams(width = wrapContent, height = wrapContent)
                    }

                }.lparams(height = matchParent) {
                    gravity = Gravity.START
                }
            }
        }
    }
}