package com.example.androidhomeworks.domain.repository.login

import com.example.androidhomeworks.domain.resource.Resource
import com.example.androidhomeworks.data.remote.login.LoginResponseDto
import com.example.androidhomeworks.domain.model.LoginResponse
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun login(email: String, password: String, rememberMe :Boolean): Flow<Resource<LoginResponse>>
}