package com.example.androidhomeworks.presentation.login


data class LoginUiState(
    val isLoading: Boolean = false,
    val success: Boolean = false,
    val email: String = "",
    val password: String = "",
    val rememberMe:Boolean = false,
    val isEmailValid:Boolean =false,
    val isPasswordValid:Boolean =false
)