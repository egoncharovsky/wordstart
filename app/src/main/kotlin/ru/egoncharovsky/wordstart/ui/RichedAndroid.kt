package ru.egoncharovsky.wordstart.ui

import android.app.Activity
import android.content.Intent
import android.widget.EditText

fun EditText.input(): String = this.text?.toString() ?: ""

fun <ActivityType : Activity> Activity.switchActivityTo(activity: Class<ActivityType>) {
    if (this.javaClass != activity) {
        startActivity(Intent(this, activity))
    }
}