package com.example.androidhomeworks.presentation.home

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidhomeworks.adapters.PostsAdapter
import com.example.androidhomeworks.adapters.StoryAdapter
import com.example.androidhomeworks.common.Resource
import com.example.androidhomeworks.databinding.FragmentHomeBinding
import com.example.androidhomeworks.presentation.base_framgent.BaseFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val homeViewModel: HomeViewModel by viewModels()
    private val storyAdapter by lazy {
        StoryAdapter()
    }
    private val postAdapter by lazy {
        PostsAdapter()
    }

    override fun setUp() {
        homeViewModel.fetchStories()
        homeViewModel.fetchPosts()

        setUpStoryRecyclerView()
        setUpPostsRecyclerView()
        storyState()
        postState()
    }


    private fun storyState(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                homeViewModel.storiesState.collectLatest { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            loading()
                        }

                        is Resource.Success -> {
                            loaded()
                            val storiesList = resource.data
                            storyAdapter.submitList(storiesList)
                        }

                        is Resource.Error -> {
                            loaded()
                            Snackbar.make(binding.root, resource.errorMessage, Snackbar.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            }
        }
    }

    private fun postState(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                homeViewModel.postsState.collectLatest { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            loading()
                        }

                        is Resource.Success -> {
                            loaded()
                            val postList = resource.data
                            postAdapter.submitList(postList)
                        }

                        is Resource.Error -> {
                            loaded()
                            Snackbar.make(binding.root, resource.errorMessage, Snackbar.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            }
        }
    }


    private fun setUpStoryRecyclerView(){
        binding.storyRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = storyAdapter
        }
    }

    private fun setUpPostsRecyclerView(){
        binding.postRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = postAdapter
        }
    }



    private fun loading() {
        binding.loading.visibility = View.VISIBLE
    }

    private fun loaded() {
        binding.loading.visibility = View.GONE
    }
}