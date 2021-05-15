package com.baseproject.util.view

import android.graphics.Rect
import android.view.View
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding

fun View.addSystemWindowInsetToPadding(
    left: Boolean = false,
    top: Boolean = false,
    right: Boolean = false,
    bottom: Boolean = false,
    imeSensitive: Boolean = false
) = doOnApplyWindowInsetsToPadding(imeSensitive) { view, systemWindowInsets, initialPadding ->
    view.updatePadding(
        left = initialPadding.left + (if (left) systemWindowInsets.left else 0),
        top = initialPadding.top + (if (top) systemWindowInsets.top else 0),
        right = initialPadding.right + (if (right) systemWindowInsets.right else 0),
        bottom = initialPadding.bottom + (if (bottom) systemWindowInsets.bottom else 0)
    )
}

fun View.addSystemWindowInsetToMargin(
    left: Boolean = false,
    top: Boolean = false,
    right: Boolean = false,
    bottom: Boolean = false,
    imeSensitive: Boolean = false
) = doOnApplyWindowInsetsToMargin(imeSensitive) { view, systemWindowInsets, initialMargin ->
    view.updateMargin(
        left = initialMargin.left + (if (left) systemWindowInsets.left else 0),
        top = initialMargin.top + (if (top) systemWindowInsets.top else 0),
        right = initialMargin.right + (if (right) systemWindowInsets.right else 0),
        bottom = initialMargin.bottom + (if (bottom) systemWindowInsets.bottom else 0)
    )
}

/** Adjust view insets via its padding */
fun View.doOnApplyWindowInsetsToPadding(
    imeSensitive: Boolean = false,
    block: (View, Insets, Rect) -> Unit
) {

    val initialPadding = this.getPadding()

    ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
        val systemWindowInsets = insets.getSystemInsets(imeSensitive)
        block(v, systemWindowInsets, initialPadding)

        insets
    }

    requestApplyInsetsWhenAttached()
}

/** Adjust view insets via its margin */
fun View.doOnApplyWindowInsetsToMargin(
    imeSensitive: Boolean = false,
    block: (View, Insets, Rect) -> Unit
) {

    val initialMargin = this.getMargin()

    ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
        val systemWindowInsets = insets.getSystemInsets(imeSensitive)
        block(v, systemWindowInsets, initialMargin)

        insets
    }

    requestApplyInsetsWhenAttached()
}

/* Internal */

private fun WindowInsetsCompat.getSystemInsets(imeSensitive: Boolean = false): Insets {
    @WindowInsetsCompat.Type.InsetsType var insetsTypeMask: Int = WindowInsetsCompat.Type.systemBars()
    if (imeSensitive) insetsTypeMask = insetsTypeMask or WindowInsetsCompat.Type.ime()
    return this.getInsets(insetsTypeMask)
}

private fun View.requestApplyInsetsWhenAttached() {

    if (isAttachedToWindow) {
        ViewCompat.requestApplyInsets(this)
    } else {
        addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {

            override fun onViewAttachedToWindow(v: View) {
                v.removeOnAttachStateChangeListener(this)
                ViewCompat.requestApplyInsets(v)
            }

            override fun onViewDetachedFromWindow(v: View) = Unit
        })
    }
}