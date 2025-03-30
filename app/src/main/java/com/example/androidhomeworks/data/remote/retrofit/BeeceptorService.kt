package com.example.androidhomeworks.data.remote.retrofit

import com.example.androidhomeworks.data.remote.model.course.CourseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BeeceptorService {
    @GET("exchange")
    suspend fun getExchangeRate(
        @Query("from_account") fromAccount: String,
        @Query("to_account") toAccount: String
    ): Response<CourseDto>
}