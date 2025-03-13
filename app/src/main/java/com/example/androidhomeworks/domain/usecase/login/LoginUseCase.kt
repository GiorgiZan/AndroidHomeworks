package com.example.androidhomeworks.domain.usecase.login


import com.example.androidhomeworks.domain.resource.Resource
import com.example.androidhomeworks.domain.model.LoginResponse
import com.example.androidhomeworks.domain.repository.login.LoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


interface LoginUseCase{
    suspend operator fun invoke(email: String, password: String,rememberMe :Boolean): Flow<Resource<LoginResponse>>
}

class LoginUseCaseImpl @Inject constructor(
    private val loginRepository: LoginRepository
) : LoginUseCase{
    override suspend operator fun invoke(email: String, password: String,rememberMe :Boolean): Flow<Resource<LoginResponse>> {
        return loginRepository.login(email, password,rememberMe)
    }
}
