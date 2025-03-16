package com.example.androidhomeworks.presentation.login


data class LoginState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val success: Boolean = false
)