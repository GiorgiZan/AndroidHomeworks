package com.example.androidhomeworks.domain.usecase.validation

import javax.inject.Inject

class EmailValidationUseCase @Inject constructor() {
    operator fun invoke(email: String): Boolean {
        return email.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}