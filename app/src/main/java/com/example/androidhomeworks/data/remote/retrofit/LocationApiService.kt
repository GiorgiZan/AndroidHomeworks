package com.example.androidhomeworks.data.remote.retrofit

import com.example.androidhomeworks.data.remote.location.LocationDTO
import retrofit2.Response
import retrofit2.http.GET

interface LocationApiService {

    @GET("c4c64996-4ed9-4cbc-8986-43c4990d495a")
    suspend fun getLocations(): Response<List<LocationDTO>>
}
