package com.alesh.baseproject.ui.details

import com.alesh.baseproject.App
import com.alesh.baseproject.R
import com.alesh.baseproject.common.base.BaseFragment
import com.alesh.baseproject.util.viewModel

class UserDetailsFragment : BaseFragment(R.layout.fragment_user_details) {

    override val viewModel by viewModel { App.component.userDetailsViewModel }
}