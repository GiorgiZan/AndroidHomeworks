package com.example.androidhomeworks.data.repository

import com.example.androidhomeworks.common.ApiHelper
import com.example.androidhomeworks.common.Resource
import com.example.androidhomeworks.data.remote.retrofit.RetrofitService
import com.example.androidhomeworks.data.remote.statistics.StatisticsDto
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val apiHelper: ApiHelper,
    private val retrofitService: RetrofitService
) {
    suspend fun fetchStatistics(): Resource<List<StatisticsDto>>{
        return apiHelper.handleHttpRequest(apiCall = {
            retrofitService.getStatistics()
        })
    }
}