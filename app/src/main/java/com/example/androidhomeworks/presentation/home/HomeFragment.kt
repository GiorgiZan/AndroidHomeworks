package com.example.androidhomeworks.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidhomeworks.R
import com.example.androidhomeworks.adapters.UserAdapter
import com.example.androidhomeworks.data.local.room.user.UserDatabase
import com.example.androidhomeworks.databinding.FragmentHomeBinding
import com.example.androidhomeworks.presentation.base_framgent.BaseFragment
import com.example.androidhomeworks.presentation.view_model_factory.HomeViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {


    private val userAdapter by lazy {
        UserAdapter()
    }
    private val homeViewModel: HomeViewModel by viewModels {
        HomeViewModelFactory(
            database = UserDatabase.getDatabase(requireContext())
        )
    }

    override fun listeners() {
        binding.ivProfile.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToProfileFragment())
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = userAdapter

        stateManagement()
        submitUsers()
    }

    private fun stateManagement() {
        userAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading){
                loading()
            }
            else if (loadState.refresh is LoadState.Error){
                Snackbar.make(binding.root,
                    getString(R.string.failed_to_load_users), Snackbar.LENGTH_SHORT).show()
                loaded()
        }
            else {
                loaded()
            }
        }
    }


    private fun submitUsers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.usersFlow.collectLatest { pagingData ->
                    userAdapter.submitData(pagingData)
                }
            }
        }
    }


    private fun loading() {
        binding.loading.visibility = View.VISIBLE
        binding.ivProfile.isEnabled = false
    }

    private fun loaded() {
        binding.loading.visibility = View.GONE
        binding.ivProfile.isEnabled = true
    }
}