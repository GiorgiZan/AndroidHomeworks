package com.example.androidhomeworks.domain.usecase.validation

import javax.inject.Inject

interface EmailValidationUseCase{
    operator fun invoke(email: String): Boolean
}

class EmailValidationUseCaseImpl @Inject constructor(): EmailValidationUseCase {
    override operator fun invoke(email: String): Boolean {
        return email.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}