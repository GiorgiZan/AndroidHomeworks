package com.example.androidhomeworks.presentation.login

sealed class LoginUiEvent {
    data object ShowEmailError : LoginUiEvent()
    data object ShowPasswordError : LoginUiEvent()
    data object NavigateToHomeScreen : LoginUiEvent()
}
