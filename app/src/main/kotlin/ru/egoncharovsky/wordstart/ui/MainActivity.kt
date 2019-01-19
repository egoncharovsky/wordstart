package ru.egoncharovsky.wordstart.ui

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.app.AppCompatActivity
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

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityContent().setContentView(this)
    }
}


class MainActivityContent : AnkoComponent<MainActivity> {
    override fun createView(ui: AnkoContext<MainActivity>): View = with(ui) {
        drawerLayout {
            fitsSystemWindows = true

            coordinatorLayout {
                //                fitsSystemWindows = true

                themedAppBarLayout(R.style.AppTheme_AppBarOverlay) {
                    //
                    toolbar {
                        lparams(width = matchParent, height = matchParent)
                        popupTheme = R.style.AppTheme_PopupOverlay

                        textView("toolbar")
                    }
                }.lparams(width = matchParent, height = wrapContent)

                relativeLayout {
                    horizontalPadding = dimen(R.dimen.activity_horizontal_margin)
                    verticalPadding = dimen(R.dimen.activity_vertical_margin)

                    textView("Hello World!")
                }.lparams(width = matchParent, height = matchParent) {
                    behavior = AppBarLayout.ScrollingViewBehavior()
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

//
//
//
//
//