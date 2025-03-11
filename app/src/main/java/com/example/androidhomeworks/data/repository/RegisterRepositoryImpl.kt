package com.example.androidhomeworks.data.repository

import com.example.androidhomeworks.common.ApiHelper
import com.example.androidhomeworks.common.Resource
import com.example.androidhomeworks.data.remote.register.RegisterDto
import com.example.androidhomeworks.data.remote.register.RegisterResponseDto
import com.example.androidhomeworks.data.remote.retrofit.RetrofitService
import com.example.androidhomeworks.domain.repository.register.RegisterRepository
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(
    private val apiHelper: ApiHelper,
    private val retrofitService: RetrofitService
) : RegisterRepository {

    override suspend fun register(email: String, password: String): Resource<RegisterResponseDto> {
        return apiHelper.handleHttpRequest {
            retrofitService.register(RegisterDto(email, password))
        }
    }
}