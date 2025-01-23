package com.example.androidhomeworks.retrofit

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import okhttp3.MediaType.Companion.toMediaType

object RetrofitClient {
    private const val BASE_URL = "https://reqres.in/api/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build()


    val retrofitService: RetrofitService = retrofit.create(RetrofitService::class.java)

}