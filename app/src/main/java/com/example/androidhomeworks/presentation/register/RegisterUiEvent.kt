package com.example.androidhomeworks.presentation.register



sealed class RegisterUiEvent {
    data object ShowEmailError : RegisterUiEvent()
    data object ShowPasswordError : RegisterUiEvent()
    data object ShowRepeatPasswordError : RegisterUiEvent()
    data object NavigateToLoginScreen : RegisterUiEvent()

}