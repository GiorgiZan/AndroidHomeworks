package com.example.androidhomeworks.domain.usecase.login


import com.example.androidhomeworks.common.Resource
import com.example.androidhomeworks.data.remote.login.LoginResponseDto
import com.example.androidhomeworks.domain.repository.login.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    suspend operator fun invoke(email: String, password: String): Resource<LoginResponseDto> {
        return loginRepository.login(email, password)
    }
}
