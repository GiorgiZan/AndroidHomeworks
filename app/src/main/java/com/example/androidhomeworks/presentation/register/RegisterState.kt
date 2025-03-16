package com.example.androidhomeworks.presentation.register


data class RegisterState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val success: Boolean = false
)