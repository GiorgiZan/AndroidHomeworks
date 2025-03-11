package com.example.androidhomeworks.domain.repository.register

import com.example.androidhomeworks.common.Resource
import com.example.androidhomeworks.data.remote.register.RegisterResponseDto

interface RegisterRepository {
    suspend fun register(email: String, password: String): Resource<RegisterResponseDto>
}