package com.example.androidhomeworks.presentation.register


sealed interface RegisterUiEvent {
    data class OnEmailChanged(val email: String) : RegisterUiEvent
    data class OnPasswordChanged(val password: String) : RegisterUiEvent
    data class OnRepeatedPasswordChanged(val password: String, val repeatedPassword: String) : RegisterUiEvent
    data object OnLoginClick : RegisterUiEvent
    data class Register(val email:String, val password: String, val repeatedPassword: String): RegisterUiEvent

}