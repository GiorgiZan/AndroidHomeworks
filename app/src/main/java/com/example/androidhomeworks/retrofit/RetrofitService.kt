package com.example.androidhomeworks.retrofit

import com.example.androidhomeworks.dto_response.LoginDto
import com.example.androidhomeworks.dto_response.LoginResponseDto
import com.example.androidhomeworks.dto_response.RegisterDto
import com.example.androidhomeworks.dto_response.RegisterResponseDto
import com.example.androidhomeworks.dto_response.UsersDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitService {
    @POST("login")
    suspend fun login(@Body request: LoginDto): Response<LoginResponseDto>

    @POST("register")
    suspend fun register(@Body request: RegisterDto): Response<RegisterResponseDto>

    @GET("users?page=1")
    suspend fun getUsers(): Response<UsersDto>

}