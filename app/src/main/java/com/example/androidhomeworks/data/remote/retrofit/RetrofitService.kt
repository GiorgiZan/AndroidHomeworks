package com.example.androidhomeworks.data.remote.retrofit

import com.example.androidhomeworks.data.remote.statistics.StatisticsDto
import retrofit2.Response
import retrofit2.http.GET


interface RetrofitService {

    @GET("6dffd14a-836f-4566-b024-bd41ace3a874")
    suspend fun getStatistics() : Response<List<StatisticsDto>>
}