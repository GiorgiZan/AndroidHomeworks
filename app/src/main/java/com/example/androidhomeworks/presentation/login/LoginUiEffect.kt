package com.example.androidhomeworks.presentation.login

sealed interface LoginUiEffect {
    data object NavigateToHomeScreen : LoginUiEffect
    data class ShowErrorSnackBar(val message: String) : LoginUiEffect
}