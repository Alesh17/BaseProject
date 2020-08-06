@file:Suppress("IMPLICIT_CAST_TO_ANY")

package com.alesh.baseproject.util.view

import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.alesh.baseproject.R

class WaitDialogSettings {
    var orientation: Int = LinearLayout.HORIZONTAL
    val background: ColorDrawable = ColorDrawable(Color.parseColor("#B3444444"))
    var cancelable: Boolean = false
    var onCancelListener: ((DialogInterface) -> Unit)? = null
}

fun Fragment.buildLoadingDialog(settings: WaitDialogSettings = WaitDialogSettings()) =
    (view as ViewGroup).buildLoadingDialog(settings)

fun ViewGroup.buildLoadingDialog(settings: WaitDialogSettings): Dialog {

    val dialogLayout = R.layout.loading_dialog
    val progressDialog = Dialog(rootView.context)
    val layout = LayoutInflater.from(rootView.context)
        .inflate(dialogLayout, this, false) as LinearLayout

    layout.orientation = settings.orientation
    progressDialog.setCancelable(settings.cancelable)
    progressDialog.setOnCancelListener(settings.onCancelListener)
    progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    progressDialog.setContentView(layout)

    progressDialog.window?.let {
        with(it) {
            setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT
            )
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            setBackgroundDrawable(settings.background)
            statusBarColor = Color.TRANSPARENT
        }
    }

    return progressDialog
}