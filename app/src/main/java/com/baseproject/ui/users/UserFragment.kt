package com.baseproject.ui.users

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.baseproject.App
import com.baseproject.R
import com.baseproject.common.base.BaseFragment
import com.baseproject.databinding.FragmentUserBinding
import com.baseproject.ui.users.adapter.UserAdapter
import com.baseproject.util.decoration.LinearLayoutDecoration
import com.baseproject.util.error.message
import com.baseproject.util.livedata.EventObserver
import com.baseproject.util.viewModel
import com.baseproject.ui.users.UserFragmentDirections.actionUserFragmentToUserDetailsFragment as actionDetails

class UserFragment : BaseFragment(R.layout.fragment_user), View.OnClickListener,
    SwipeRefreshLayout.OnRefreshListener {

    private val binding get() = bindingDraft!!
    private var bindingDraft: FragmentUserBinding? = null

    private val adapter by lazy { UserAdapter(viewModel::openDetails) }
    override val viewModel by viewModel { App.component.userViewModel }

    private val askPermission = registerForActivityResult(RequestPermission()) { result ->
        if (result) toast(R.string.ok)
        else toast(R.string.cancel)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingDraft = FragmentUserBinding.bind(view)
        setupButtons()
        observeViewModel()
        setupRecyclerView()
        setupSwipeToRefresh()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bindingDraft = null
        binding.btnInfo.setOnClickListener(null)
        viewModel.details.removeObservers(viewLifecycleOwner)
        viewModel.user.removeObservers(viewLifecycleOwner)
        viewModel.error.removeObservers(viewLifecycleOwner)
        viewModel.loading.removeObservers(viewLifecycleOwner)
    }

    override fun observeViewModel() {
        super.observeViewModel()

        viewModel.details.observe(
            viewLifecycleOwner,
            EventObserver {
                val action = actionDetails(it)
                navigate(action)
            })

        viewModel.user.observe(
            viewLifecycleOwner,
            EventObserver {
                adapter.submitList(it)
            })

        viewModel.error.observe(
            viewLifecycleOwner,
            EventObserver {
                binding.laySwipeToRefresh.isRefreshing = false
                snackbar(binding.root, it.message())
            })
    }

    override fun onRefresh() {
        viewModel.getUsers()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btnInfo -> {
                showInfoDialog()
                askPermission.launch(ACCESS_FINE_LOCATION)
            }
        }
    }

    private fun setupButtons() {
        binding.btnInfo.setOnClickListener(this)
    }

    private fun setupSwipeToRefresh() {
        binding.laySwipeToRefresh.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark)
        binding.laySwipeToRefresh.setOnRefreshListener(this)
    }

    private fun setupRecyclerView() {
        val margin = resources.getDimensionPixelSize(R.dimen.base_margin)
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
}