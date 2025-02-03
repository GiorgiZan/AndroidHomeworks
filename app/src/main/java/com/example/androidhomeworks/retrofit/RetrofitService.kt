package com.example.androidhomeworks.retrofit

import com.example.androidhomeworks.dto.UsersListDto
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitService {

    @GET("f3f41821-7434-471f-9baa-ae3dee984e6d")
    suspend fun getUsers(): Response<UsersListDto>

}