package com.example.androidhomeworks.domain.usecase.validation

import javax.inject.Inject

interface PasswordValidationUseCase{
    operator fun invoke(password: String): Boolean
}

class PasswordValidationUseCaseImpl @Inject constructor() :PasswordValidationUseCase {
    override operator fun invoke(password: String): Boolean {
        return password.isNotEmpty()
    }
}
