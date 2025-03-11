package com.example.androidhomeworks.data.repository

import com.example.androidhomeworks.common.ApiHelper
import com.example.androidhomeworks.common.Resource
import com.example.androidhomeworks.data.remote.login.LoginDto
import com.example.androidhomeworks.data.remote.login.LoginResponseDto
import com.example.androidhomeworks.data.remote.retrofit.RetrofitService
import com.example.androidhomeworks.domain.repository.login.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val apiHelper: ApiHelper,
    private val retrofitService: RetrofitService
) : LoginRepository {

    override suspend fun login(email: String, password: String): Resource<LoginResponseDto> {
        return apiHelper.handleHttpRequest {
            retrofitService.login(LoginDto(email, password))
        }
    }
}