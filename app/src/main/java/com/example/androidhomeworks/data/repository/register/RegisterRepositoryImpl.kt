package com.example.androidhomeworks.data.repository.register

import com.example.androidhomeworks.common.ApiHelper
import com.example.androidhomeworks.data.mapper.toDomain
import com.example.androidhomeworks.domain.resource.Resource
import com.example.androidhomeworks.data.remote.retrofit.RetrofitService
import com.example.androidhomeworks.domain.model.RegisterResponse
import com.example.androidhomeworks.domain.repository.register.RegisterRepository
import com.example.androidhomeworks.domain.resource.mapResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(
    private val apiHelper: ApiHelper,
    private val retrofitService: RetrofitService
) : RegisterRepository {

    override suspend fun register(email: String, password: String): Flow<Resource<RegisterResponse>> {
        return apiHelper.handleHttpRequest {
            retrofitService.register(email, password)
        }.mapResource { it.toDomain() }
    }
}