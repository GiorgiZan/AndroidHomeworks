package com.example.androidhomeworks.domain.repository.register

import com.example.androidhomeworks.domain.resource.Resource
import com.example.androidhomeworks.data.remote.register.RegisterResponseDto
import com.example.androidhomeworks.domain.model.RegisterResponse
import kotlinx.coroutines.flow.Flow

interface RegisterRepository {
    suspend fun register(email: String, password: String): Flow<Resource<RegisterResponse>>
}