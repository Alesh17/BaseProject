package com.baseproject.util.view

import android.content.res.Resources
import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop
import androidx.core.view.updateLayoutParams
import androidx.core.view.updateMargins

val Int.dp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()

val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.enable() {
    this.isEnabled = true
}

fun View.disable() {
    this.isEnabled = false
}

fun View.getPadding() = Rect(paddingLeft, paddingTop, paddingRight, paddingBottom)

fun View.getMargin() = Rect(marginLeft, marginTop, marginRight, marginBottom)

fun View.updateMargin(left: Int = 0, top: Int = 0, right: Int = 0, bottom: Int = 0) {
    this.updateLayoutParams {
        (this as? ViewGroup.MarginLayoutParams)?.let {
            updateMargins(
                left = left,
                top = top,
                right = right,
                bottom = bottom
            )
        }
    }
}