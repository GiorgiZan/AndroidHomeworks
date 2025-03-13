package com.example.androidhomeworks.presentation.login

sealed class LoginState{
    data object Loading : LoginState()
    data object Success : LoginState()
    data class Error(val message: String) : LoginState()
}