package com.example.androidhomeworks.data.remote.retrofit

import com.example.androidhomeworks.data.remote.login.LoginResponseDto
import com.example.androidhomeworks.data.remote.register.RegisterResponseDto
import com.example.androidhomeworks.data.remote.users.UsersResponseDto
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RetrofitService {
    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<LoginResponseDto>

    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<RegisterResponseDto>

    @GET("users")
    suspend fun getUsers(@Query("page") page: Int): UsersResponseDto

}