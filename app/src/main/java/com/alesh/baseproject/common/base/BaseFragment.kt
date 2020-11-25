@file:Suppress("MemberVisibilityCanBePrivate")

package com.alesh.baseproject.common.base

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.afollestad.materialdialogs.MaterialDialog
import com.alesh.baseproject.util.livedata.EventObserver
import com.alesh.baseproject.util.view.buildLoadingDialog
import com.alesh.baseproject.util.view.dialogBuilder
import com.alesh.baseproject.util.view.hideKeyboard
import com.alesh.baseproject.util.view.onBackPressedListener
import com.alesh.baseproject.util.view.snackbar
import com.alesh.baseproject.util.view.toast

abstract class BaseFragment(@LayoutRes layoutId: Int) : Fragment(layoutId) {

    abstract val viewModel: BaseViewModel

    private var loadingDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBackPressedHandler()
    }

    override fun onStop() {
        super.onStop()
        hideKeyboard()
    }

    open fun observeViewModel() {
        viewModel.loading.observe(
            viewLifecycleOwner,
            EventObserver {
                showLoading(it)
            })
    }

    /* Messages */
    fun toast(@StringRes messageStringRes: Int) {
        requireContext().toast(messageStringRes)
    }

    fun snackbar(view: View, @StringRes messageStringRes: Int) {
        requireContext().snackbar(view, messageStringRes)
    }

    /* Dialogs */
    fun dialogBuilder(title: Int): MaterialDialog {
        return requireContext().dialogBuilder(viewLifecycleOwner, title)
    }

    /* Loadings */
    fun showLoading(isLoading: Boolean) {
        loadingDialog = if (isLoading) buildLoadingDialog().apply { show() }
        else loadingDialog?.dismiss().let { null }
    }

    /* Navigation */
    private fun onBackPressedHandler() {
        requireActivity().onBackPressedListener()
    }

    fun navigate(@IdRes resId: Int) {
        findNavController().navigate(resId)
    }

    fun navigate(direction: NavDirections) {
        findNavController().navigate(direction)
    }

    fun navigateBack() {
        findNavController().popBackStack()
    }

    /* Other */
    private fun hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }
}