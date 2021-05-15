package com.baseproject.ui.details

import com.baseproject.App
import com.baseproject.R
import com.baseproject.common.base.BaseFragment
import com.baseproject.util.dagger.viewModel

class UserDetailsFragment : BaseFragment(R.layout.fragment_user_details) {

    override val viewModel by viewModel { App.component.userDetailsViewModel }

    override fun setupInsets() {}
}