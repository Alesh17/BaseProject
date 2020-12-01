package com.alesh.baseproject.ui.users

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.READ_CONTACTS
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts.RequestMultiplePermissions
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.activity.result.contract.ActivityResultContracts.TakePicturePreview
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.alesh.baseproject.App
import com.alesh.baseproject.R
import com.alesh.baseproject.common.base.BaseFragment
import com.alesh.baseproject.databinding.FragmentUserBinding
import com.alesh.baseproject.ui.users.adapter.UserAdapter
import com.alesh.baseproject.util.contract.SampleContract
import com.alesh.baseproject.util.decoration.LinearLayoutDecoration
import com.alesh.baseproject.util.error.message
import com.alesh.baseproject.util.livedata.EventObserver
import com.alesh.baseproject.util.viewModel
import com.alesh.baseproject.ui.users.UserFragmentDirections.actionUserFragmentToUserDetailsFragment as actionDetails

class UserFragment : BaseFragment(R.layout.fragment_user), View.OnClickListener,
    SwipeRefreshLayout.OnRefreshListener {

    private val binding get() = bindingDraft!!
    private var bindingDraft: FragmentUserBinding? = null

    private val adapter by lazy { UserAdapter(viewModel::openDetails) }
    override val viewModel by viewModel { App.component.userViewModel }

    private val sampleContractRegistration = registerForActivityResult(SampleContract()) { result ->
        if (result != null) toast(R.string.ok)
        else toast(R.string.cancel)
    }

    /**
     * ActivityResultContract implementations available now:
     * https://developer.android.com/reference/androidx/activity/result/contract/ActivityResultContracts
     * */
    private val takePicture = registerForActivityResult(TakePicturePreview()) { bitmap ->
        binding.imageView.setImageBitmap(bitmap)
    }

    private val askPermission = registerForActivityResult(RequestPermission()) { result ->
        if (result) toast(R.string.ok)
        else toast(R.string.cancel)
    }

    private val askMultiplePermissions = registerForActivityResult(RequestMultiplePermissions()) { map ->
        for (entry in map.entries) {
            // entry.key -> android.permission.LOCATION (string)
            // entry.value -> true (boolean)
        }
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
                takePicture.launch(null)
                askPermission.launch(ACCESS_FINE_LOCATION)
                askMultiplePermissions.launch(arrayOf(ACCESS_FINE_LOCATION, READ_CONTACTS))
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