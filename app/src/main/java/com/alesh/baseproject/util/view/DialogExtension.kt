package com.alesh.baseproject.util.view

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.alesh.baseproject.R

fun Context.dialogBuilder(owner: LifecycleOwner, title: Int): MaterialDialog {
    return MaterialDialog(this)
        .lifecycleOwner(owner)
        .title(title)
        .cornerRadius(res = R.dimen.cornerRadius)
        .cancelOnTouchOutside(cancelable = true)
}