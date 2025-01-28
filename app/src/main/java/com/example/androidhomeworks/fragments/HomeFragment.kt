package com.example.androidhomeworks.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
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

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = userAdapter

        stateManagement()
        submitUsers()
    }

    private fun stateManagement() {
        userAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading){
                loading()
            } else {
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