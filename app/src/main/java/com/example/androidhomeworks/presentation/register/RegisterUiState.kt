package com.example.androidhomeworks.presentation.register


data class RegisterUiState(
    val isLoading: Boolean = false,
    val success: Boolean = false,
    val email: String = "",
    val password: String = "",
    val repeatedPassword:String = "",
    val isEmailValid:Boolean =false,
    val isPasswordValid:Boolean =false,
    val isRepeatedPasswordValid:Boolean = false
)