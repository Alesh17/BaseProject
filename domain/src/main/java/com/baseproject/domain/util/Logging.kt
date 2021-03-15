package com.baseproject.domain.util

import android.os.SystemClock
import android.util.Log

private const val LOG_TAG = "Alesh"

fun log(msg: Any?) = Log.v(LOG_TAG, msg.toString())

fun log(tag: String, msg: Any?) = Log.v("$LOG_TAG $tag", msg.toString())

inline fun <T> measureDuration(tag: String = "", block: () -> T): T {
    val start = SystemClock.uptimeMillis()
    val result = block()
    val time = SystemClock.uptimeMillis() - start
    log(tag, "$time millis")
    return result
}