package com.example.androidhomeworks.presentation.login

sealed interface LoginUiEvent {
    data class OnEmailChanged(val email: String) : LoginUiEvent
    data class OnPasswordChanged(val password: String) : LoginUiEvent
    data class OnRememberMeChanged(val checked:Boolean): LoginUiEvent
    data class Login(val email: String, val password: String, val rememberMe: Boolean) : LoginUiEvent
}
