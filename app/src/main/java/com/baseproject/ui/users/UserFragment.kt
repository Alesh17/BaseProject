package com.baseproject.ui.users

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import by.kirich1409.viewbindingdelegate.viewBinding
import com.baseproject.App
import com.baseproject.R
import com.baseproject.common.base.BaseFragment
import com.baseproject.databinding.FragmentUserBinding
import com.baseproject.ui.users.adapter.UserAdapter
import com.baseproject.util.error.message
import com.baseproject.util.flow.collectWhileStarted
import com.baseproject.util.recycler.decoration.LinearLayoutDecoration
import com.baseproject.util.view.addSystemWindowInsetToMargin
import com.baseproject.util.dagger.viewModel
import com.baseproject.ui.users.UserFragmentDirections.actionUserFragmentToUserDetailsFragment as actionDetails

class UserFragment : BaseFragment(R.layout.fragment_user), View.OnClickListener,
    SwipeRefreshLayout.OnRefreshListener {

    private val binding by viewBinding(FragmentUserBinding::bind)

    private val adapter by lazy { UserAdapter(::openDetails) }
    override val viewModel by viewModel { App.component.userViewModel }

    private val askPermission = registerForActivityResult(RequestPermission()) { result ->
        if (result) toast(R.string.ok)
        else toast(R.string.cancel)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButtons()
        observeViewModel()
        setupRecyclerView()
        setupSwipeToRefresh()
    }

    override fun setupInsets() {
        binding.btnInfo.addSystemWindowInsetToMargin(top = true)
    }

    private fun setupButtons() {
        binding.btnInfo.setOnClickListener(this)
    }

    private fun setupSwipeToRefresh() {
        binding.laySwipeToRefresh.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark)
        binding.laySwipeToRefresh.setOnRefreshListener(this)
    }

    private fun setupRecyclerView() {
        val margin = resources.getDimensionPixelSize(R.dimen.common_offset_vertical_s)
        binding.rvUsers.adapter = adapter
        binding.rvUsers.addItemDecoration(LinearLayoutDecoration(margin))
    }

    private fun showInfoDialog() {

        val title = R.string.not_available
        val message = R.string.not_available

        dialogBuilder(title).apply {
            message(message)
            positiveButton(R.string.ok)
            negativeButton(R.string.cancel) { this.hide() }
            show()
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btnInfo -> {
                showInfoDialog()
                askPermission.launch(ACCESS_FINE_LOCATION)
            }
        }
    }

    override fun onRefresh() {
        viewModel.getUsers()
    }

    private fun openDetails(itemPosition: Int) {
        val user = adapter.currentList[itemPosition]
        val action = actionDetails(user)
        navigate(action)
    }

    override fun observeViewModel() {
        super.observeViewModel()

        viewModel.status.collectWhileStarted(viewLifecycleOwner) {
            // doing staff with user status
        }

        viewModel.users.collectWhileStarted(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.error.collectWhileStarted(viewLifecycleOwner) {
            binding.laySwipeToRefresh.isRefreshing = false
            snackbar(binding.root, it.message())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvUsers.adapter = null
    }
}