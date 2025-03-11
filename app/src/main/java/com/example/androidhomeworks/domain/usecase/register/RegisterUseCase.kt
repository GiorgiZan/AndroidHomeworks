package com.example.androidhomeworks.domain.usecase.register

import com.example.androidhomeworks.common.Resource
import com.example.androidhomeworks.data.remote.register.RegisterResponseDto
import com.example.androidhomeworks.domain.repository.register.RegisterRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: RegisterRepository
) {
    suspend operator fun invoke(email: String, password: String): Resource<RegisterResponseDto> {
        return repository.register(email, password)
    }
}