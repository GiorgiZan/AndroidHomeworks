package com.example.androidhomeworks.fragment

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidhomeworks.adapter.UserAdapter
import com.example.androidhomeworks.databinding.FragmentUserBinding
import com.example.androidhomeworks.viewmodel.UserViewModel
import com.example.androidhomeworks.viewmodel.UserViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class UserFragment : BaseFragment<FragmentUserBinding>(FragmentUserBinding::inflate) {
    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory(requireActivity().application)
    }

    private val userAdapter by lazy {
        UserAdapter()
    }

    override fun setUp() {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = userAdapter
        stateManagement()
        viewLifecycleOwner.lifecycleScope.launch {
            userViewModel.users.collect { users ->
                userAdapter.submitList(users)
            }
        }
    }

    private fun stateManagement() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                userViewModel.stateManagement.collectLatest { state ->
                    if (state.isLoading) {
                        loading()
                    } else {
                        loaded()
                    }

                    state.successMessage?.let {
                        Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
                    }

                    state.errorMessage?.let {
                        Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun loading() {
        binding.loading.visibility = View.VISIBLE

    }

    private fun loaded() {
        binding.loading.visibility = View.GONE
    }
}