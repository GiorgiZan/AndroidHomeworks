package com.example.androidhomeworks.data.repository.location

import com.example.androidhomeworks.common.ApiHelper
import com.example.androidhomeworks.common.Resource
import com.example.androidhomeworks.data.remote.location.LocationDTO
import com.example.androidhomeworks.data.remote.retrofit.LocationApiService
import javax.inject.Inject

class LocationRepository @Inject constructor(
    private val apiService: LocationApiService,
    private val apiHelper: ApiHelper
) {

    suspend fun getLocations(): Resource<List<LocationDTO>> {
        return apiHelper.handleHttpRequest {
            apiService.getLocations()
        }
    }
}