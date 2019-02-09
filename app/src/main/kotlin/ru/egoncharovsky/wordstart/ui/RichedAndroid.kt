package ru.egoncharovsky.wordstart.ui

import android.app.Activity
import android.content.Intent
import android.os.Parcelable
import android.widget.EditText

fun EditText.input(): String = this.text?.toString() ?: ""

fun <ActivityType : Activity> Activity.switchActivityTo(activity: Class<ActivityType>,
                                                        finish: Boolean = false,
                                                        extras: Map<String, Any> = emptyMap()) {
    if (this.javaClass != activity) {
        val intent = Intent(this, activity)
        extras.forEach { intent.addExtra(it.key, it.value) }
        startActivity(intent)
        if (finish) finish()
    }
}

fun Intent.addExtra(key: String, value: Any?) {
    when (value) {
        is Long -> putExtra(key, value)
        is String -> putExtra(key, value)
        is Boolean -> putExtra(key, value)
        is Float -> putExtra(key, value)
        is Double -> putExtra(key, value)
        is Int -> putExtra(key, value)
        is Parcelable -> putExtra(key, value)
        else -> throw IllegalArgumentException("unexpected value type $value: ${value?.javaClass} for key $key")
    }
}

inline fun <reified T> Activity.getExtra(extra: String): T? {
    return intent.extras?.get(extra) as? T?
}