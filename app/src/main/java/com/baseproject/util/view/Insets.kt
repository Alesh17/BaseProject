package com.baseproject.util.view

import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.updatePadding

fun View.addSystemWindowInsetToPadding(
    left: Boolean = false,
    top: Boolean = false,
    right: Boolean = false,
    bottom: Boolean = false
) {
    val initialPadding = this.getPadding()

    ViewCompat.setOnApplyWindowInsetsListener(this) { view, insets ->

        view.updatePadding(
            left = initialPadding.left + (if (left) insets.systemWindowInsets.left else 0),
            top = initialPadding.top + (if (top) insets.systemWindowInsets.top else 0),
            right = initialPadding.right + (if (right) insets.systemWindowInsets.right else 0),
            bottom = initialPadding.bottom + (if (bottom) insets.systemWindowInsets.bottom else 0)
        )

        insets
    }

    requestApplyInsetsWhenAttached()
}

fun View.addSystemWindowInsetToMargin(
    left: Boolean = false,
    top: Boolean = false,
    right: Boolean = false,
    bottom: Boolean = false
) {
    val initialMargin = this.getMargin()

    ViewCompat.setOnApplyWindowInsetsListener(this) { view, insets ->

        view.updateMargin(
            left = initialMargin.left + (if (left) insets.systemWindowInsets.left else 0),
            top = initialMargin.top + (if (top) insets.systemWindowInsets.top else 0),
            right = initialMargin.right + (if (right) insets.systemWindowInsets.right else 0),
            bottom = initialMargin.bottom + (if (bottom) insets.systemWindowInsets.bottom else 0)
        )

        insets
    }

    requestApplyInsetsWhenAttached()
}

/* Internal */

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