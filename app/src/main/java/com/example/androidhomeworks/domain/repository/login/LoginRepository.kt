package com.example.androidhomeworks.domain.repository.login

import com.example.androidhomeworks.common.Resource
import com.example.androidhomeworks.data.remote.login.LoginResponseDto

interface LoginRepository {
    suspend fun login(email: String, password: String): Resource<LoginResponseDto>
}