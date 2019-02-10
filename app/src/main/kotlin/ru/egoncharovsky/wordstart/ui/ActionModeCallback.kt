package ru.egoncharovsky.wordstart.ui

import android.app.Activity
import android.support.annotation.MenuRes
import android.support.v7.app.AppCompatActivity
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.view.View

abstract class ActionModeCallback(
        val activity: Activity,
        @MenuRes val menuResId: Int,
        val title: String? = null,
        val subtitle: String? = null) : ActionMode.Callback {

    private var mode: ActionMode? = null

    override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
        this.mode = mode
        mode.menuInflater.inflate(menuResId, menu)
        mode.title = title
        mode.subtitle = subtitle
        return true
    }

    override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
        return false
    }

    override fun onDestroyActionMode(mode: ActionMode) {
        this.mode = null
    }

    fun startActionMode() {
        activity.startActionMode(this)
    }

    fun finishActionMode() {
        mode?.finish()
    }

    fun started(): Boolean = mode != null
}