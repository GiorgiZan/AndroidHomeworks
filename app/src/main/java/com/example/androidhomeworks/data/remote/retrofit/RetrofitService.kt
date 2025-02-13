package com.example.androidhomeworks.data.remote.retrofit

import com.example.androidhomeworks.data.remote.login.LoginDto
import com.example.androidhomeworks.data.remote.login.LoginResponseDto
import com.example.androidhomeworks.data.remote.register.RegisterDto
import com.example.androidhomeworks.data.remote.register.RegisterResponseDto
import com.example.androidhomeworks.data.remote.users.UsersResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RetrofitService {
    @POST("login")
    suspend fun login(@Body request: LoginDto): Response<LoginResponseDto>

    @POST("register")
    suspend fun register(@Body request: RegisterDto): Response<RegisterResponseDto>

    @GET("users")
    suspend fun getUsers(@Query("page") page: Int): UsersResponseDto

}