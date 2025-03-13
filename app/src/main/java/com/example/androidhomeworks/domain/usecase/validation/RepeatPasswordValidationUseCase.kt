package com.example.androidhomeworks.domain.usecase.validation

import javax.inject.Inject

interface RepeatPasswordValidationUseCase {
    operator fun invoke(password: String, repeatPassword: String): Boolean
}

class RepeatPasswordValidationUseCaseImpl @Inject constructor() : RepeatPasswordValidationUseCase {
    override operator fun invoke(password: String, repeatPassword: String): Boolean {
        return password == repeatPassword
    }
}
