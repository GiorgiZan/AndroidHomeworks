package com.example.androidhomeworks.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidhomeworks.adapters.UserAdapter
import com.example.androidhomeworks.databinding.FragmentHomeBinding
import com.example.androidhomeworks.models.HomeViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val userAdapter by lazy {
        UserAdapter()
    }
    private val homeViewModel: HomeViewModel by viewModels()

    override fun listeners() {
        binding.ivProfile.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToProfileFragment())
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.getUsers()
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = userAdapter
        homeStateManagement()
        submitUsers()
    }

    private fun submitUsers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.usersList.collectLatest { users ->
                    userAdapter.submitList(users)
                }
            }
        }
    }

    private fun homeStateManagement() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.stateManagement.collectLatest { state ->
                    if (state.isLoading) {
                        loading()
                    } else {
                        loaded()
                    }
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