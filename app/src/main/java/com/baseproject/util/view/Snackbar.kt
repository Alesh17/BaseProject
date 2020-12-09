@file:SuppressLint("WrongConstant")

package com.baseproject.util.view

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.baseproject.R
import com.google.android.material.snackbar.Snackbar

private const val snackbarDuration: Int = 4000

fun Context.snackbar(view: View, @StringRes messageStringRes: Int) {

    hideKeyboard(view)

    if (view.isAttachedToWindow) {
        val color = R.color.colorPrimary
        val actionColor = R.color.colorAccent
        val snackbar = Snackbar.make(view, getString(messageStringRes), Snackbar.LENGTH_INDEFINITE)
            .setDuration(snackbarDuration)
            .setActionTextColor(ContextCompat.getColor(applicationContext, actionColor))
            .setAction(getString(R.string.close_caps)) { }
        snackbar.view.setBackgroundColor(ContextCompat.getColor(applicationContext, color))
        snackbar.show()
    }
}