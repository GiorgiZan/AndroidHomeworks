package com.example.androidhomeworks.domain.usecase.validation

import javax.inject.Inject

interface EmailValidationUseCase{
    operator fun invoke(email: String): Boolean
}

class EmailValidationUseCaseImpl @Inject constructor(): EmailValidationUseCase {
    override operator fun invoke(email: String): Boolean {
        val emailPattern = java.util.regex.Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$")
        return email.isNotEmpty() && emailPattern.matcher(email).matches()
    }
}