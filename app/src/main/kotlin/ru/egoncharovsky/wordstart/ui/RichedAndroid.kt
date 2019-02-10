package ru.egoncharovsky.wordstart.ui

import android.app.Activity
import android.content.Intent
import android.os.Parcelable
import android.widget.EditText
import java.io.Serializable

fun EditText.input(): String = this.text?.toString() ?: ""

fun <ActivityType : Activity> Activity.switchTo(activity: Class<ActivityType>,
                                                extras: Map<String, Any> = emptyMap()) {
    return switchTo(activity, false, extras)
}

fun <ActivityType : Activity> Activity.switchTo(activity: Class<ActivityType>,
                                                finish: Boolean = false,
                                                extras: Map<String, Any> = emptyMap()) {
    if (this.javaClass != activity) {
        val intent = Intent(this, activity)
        extras.forEach { intent.addExtra(it.key, it.value) }
        startActivity(intent)
        if (finish) finish()
    }
}

fun <ActivityType : Activity> Activity.requestTo(activity: Class<ActivityType>,
                                                 requestCode: Int,
                                                 extras: Map<String, Any> = emptyMap()) {
    val intent = Intent(this, activity)
    extras.forEach { intent.addExtra(it.key, it.value) }
    startActivityForResult(intent, requestCode)
}

fun Activity.returnResult(extras: Map<String, Any> = emptyMap()) {
    val output = Intent()
    extras.forEach { output.addExtra(it.key, it.value) }
    setResult(Activity.RESULT_OK, output)
    finish()
}

fun Activity.returnCanceled(extras: Map<String, Any> = emptyMap()) {
    val output = Intent()
    extras.forEach { output.addExtra(it.key, it.value) }
    setResult(Activity.RESULT_CANCELED, output)
    finish()
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
        is IntArray -> putExtra(key, value)
        is LongArray -> putExtra(key, value)
        is FloatArray -> putExtra(key, value)
        is DoubleArray -> putExtra(key, value)
        is BooleanArray -> putExtra(key, value)
        is Serializable -> putExtra(key, value)
        else -> throw IllegalArgumentException("unexpected value type $value: ${value?.javaClass} for key $key")
    }
}

inline fun <reified T> Activity.getExtra(extra: String): T? {
    return intent.extras?.get(extra) as T?
}

inline fun <reified T> Intent.getExtra(extra: String): T? {
    return extras?.get(extra) as T?
}