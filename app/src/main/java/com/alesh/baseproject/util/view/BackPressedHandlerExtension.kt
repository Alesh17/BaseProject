package com.alesh.baseproject.util.view

import android.os.Handler
import androidx.activity.addCallback
import androidx.fragment.app.FragmentActivity
import com.alesh.baseproject.R

private var isSecondaryTap: Boolean = false

fun FragmentActivity.onBackPressedListener() {
    val fragmentActivity = this
    fragmentActivity.onBackPressedDispatcher.addCallback(this) {
        if (isSecondaryTap) fragmentActivity.finish()
        else {
            toast(R.string.tap_to_exit)
            isSecondaryTap = true
            Handler().postDelayed({ isSecondaryTap = false }, 1000)
        }
        isEnabled = true
    }
}