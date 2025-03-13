package com.example.androidhomeworks.domain.usecase.register

import com.example.androidhomeworks.domain.resource.Resource
import com.example.androidhomeworks.domain.model.RegisterResponse
import com.example.androidhomeworks.domain.repository.register.RegisterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


interface RegisterUseCase{
    suspend operator fun invoke(email: String, password: String): Flow<Resource<RegisterResponse>>
}

class RegisterUseCaseImpl @Inject constructor(
    private val repository: RegisterRepository
) :RegisterUseCase{
    override suspend operator fun invoke(email: String, password: String): Flow<Resource<RegisterResponse>> {
        return repository.register(email, password)
    }
}