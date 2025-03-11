package com.example.androidhomeworks.domain.usecase.validation

import javax.inject.Inject

class RepeatPasswordValidationUseCase @Inject constructor() {
    operator fun invoke(password: String, repeatPassword: String): Boolean {
        return password == repeatPassword
    }
}
