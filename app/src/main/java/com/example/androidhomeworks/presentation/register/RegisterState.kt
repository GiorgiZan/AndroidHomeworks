package com.example.androidhomeworks.presentation.register


sealed class RegisterState {
    data object Loading : RegisterState()
    data object Success : RegisterState()
    data class Error(val message: String) : RegisterState()
}