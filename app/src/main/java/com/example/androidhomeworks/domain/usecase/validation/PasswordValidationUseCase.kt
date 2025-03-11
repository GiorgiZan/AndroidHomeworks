package com.example.androidhomeworks.domain.usecase.validation

import javax.inject.Inject

class PasswordValidationUseCase @Inject constructor() {
    operator fun invoke(password: String): Boolean {
        return password.isNotEmpty()
    }
}
