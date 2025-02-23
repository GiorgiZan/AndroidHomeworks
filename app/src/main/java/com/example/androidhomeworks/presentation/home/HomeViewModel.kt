package com.example.androidhomeworks.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidhomeworks.common.Resource
import com.example.androidhomeworks.data.presenter.PostPresenter
import com.example.androidhomeworks.data.presenter.StoryPresenter
import com.example.androidhomeworks.data.repository.HomeRepository
import com.example.androidhomeworks.mapper.toPresenter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {
    private val _storiesState = MutableStateFlow<Resource<List<StoryPresenter>>>(Resource.Loading)
    val storiesState: StateFlow<Resource<List<StoryPresenter>>> = _storiesState

    private val _postsState = MutableStateFlow<Resource<List<PostPresenter>>>(Resource.Loading)
    val postsState: StateFlow<Resource<List<PostPresenter>>> = _postsState

    fun fetchStories() {
        viewModelScope.launch {
            val result = homeRepository.fetchStories()
            when (result) {
                is Resource.Success -> {
                    val stories = result.data.map { it.toPresenter() }
                    _storiesState.value = Resource.Success(stories)
                }

                is Resource.Error -> {
                    _storiesState.value = Resource.Error(result.errorMessage)
                }

                is Resource.Loading -> {
                    _storiesState.value = Resource.Loading
                }
            }
        }
    }

    fun fetchPosts(){
        viewModelScope.launch {
            val result = homeRepository.fetchPosts()
            when (result) {
                is Resource.Success -> {
                    val posts = result.data.map { it.toPresenter() }
                    _postsState.value = Resource.Success(posts)
                }

                is Resource.Error -> {
                    _postsState.value = Resource.Error(result.errorMessage)
                }

                is Resource.Loading -> {
                    _postsState.value = Resource.Loading
                }
            }
        }
    }
}

