package ru.egoncharovsky.wordstart.ui

import android.view.View
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalLayout

class MainActivity : BaseAnkoActivity() {

    override fun <T> AnkoContext<T>.component(ui: AnkoContext<T>): View {
        return verticalLayout {
            textView("Hello all now!")
        }
    }
}
