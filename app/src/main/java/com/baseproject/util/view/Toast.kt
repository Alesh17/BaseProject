package com.baseproject.util.view

import android.content.Context
import android.graphics.PorterDuff
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.baseproject.R

fun Context.toast(@StringRes messageStringRes: Int) {

    val toast = Toast.makeText(this, messageStringRes, Toast.LENGTH_SHORT)
    val view = toast.view
    val text: TextView = view.findViewById(android.R.id.message)
    val color = ContextCompat.getColor(this, R.color.colorPrimary)

    view.background.setColorFilter(color, PorterDuff.Mode.SRC_IN)
    text.setTextColor(ContextCompat.getColor(this, android.R.color.white))

    toast.show()
}
