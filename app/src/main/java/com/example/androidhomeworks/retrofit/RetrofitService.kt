package com.example.androidhomeworks.retrofit

import com.example.androidhomeworks.dto_response.LoginDto
import com.example.androidhomeworks.dto_response.LoginResponseDto
import com.example.androidhomeworks.dto_response.RegisterDto
import com.example.androidhomeworks.dto_response.RegisterResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitService {
    @POST("login")
    suspend fun login(@Body request: LoginDto): LoginResponseDto

    @POST("register")
    suspend fun register(@Body request: RegisterDto): RegisterResponseDto
}