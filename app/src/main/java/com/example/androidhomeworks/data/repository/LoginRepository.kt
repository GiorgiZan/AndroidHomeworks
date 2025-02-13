package com.example.androidhomeworks.data.repository

import com.example.androidhomeworks.common.ApiHelper
import com.example.androidhomeworks.common.Resource
import com.example.androidhomeworks.data.remote.login.LoginDto
import com.example.androidhomeworks.data.remote.login.LoginResponseDto
import com.example.androidhomeworks.data.remote.retrofit.RetrofitService
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val apiHelper: ApiHelper,
    private val retrofitService: RetrofitService
) {
    suspend fun login(email: String, password: String): Resource<LoginResponseDto> {
        return apiHelper.handleHttpRequest(apiCall = {
            retrofitService.login(LoginDto(email, password))
        })
    }
}