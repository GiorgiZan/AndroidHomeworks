package com.example.androidhomeworks.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidhomeworks.common.Resource
import com.example.androidhomeworks.data.remote.repository.HomeRepository
import com.example.androidhomeworks.data.remote.statistics.StatisticsDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {

    private val _statisticsState = MutableStateFlow<Resource<List<StatisticsDto>>>(Resource.Loading)
    val statisticsState: StateFlow<Resource<List<StatisticsDto>>> = _statisticsState

    fun fetchStatistics() {
        viewModelScope.launch {
            val result = homeRepository.fetchStatistics()
            when (result) {
                is Resource.Success -> {
                    _statisticsState.value = Resource.Success(result.data)
                }

                is Resource.Error -> {
                    _statisticsState.value = Resource.Error(result.errorMessage)
                }

                is Resource.Loading -> {
                    _statisticsState.value = Resource.Loading
                }
            }
        }
    }
}
