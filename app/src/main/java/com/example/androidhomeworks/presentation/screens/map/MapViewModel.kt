package com.example.androidhomeworks.presentation.screens.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidhomeworks.common.Resource
import com.example.androidhomeworks.data.remote.location.LocationDTO
import com.example.androidhomeworks.data.repository.location.LocationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val locationRepository: LocationRepository
) : ViewModel() {

    private val _locations = MutableStateFlow<Resource<List<LocationDTO>>>(Resource.Loading)
    val locations: StateFlow<Resource<List<LocationDTO>>> = _locations

    fun getLocations() {
        viewModelScope.launch {
            _locations.value = Resource.Loading

            val response = locationRepository.getLocations()

            _locations.value = response
        }
    }
}