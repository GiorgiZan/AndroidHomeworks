package com.example.androidhomeworks.presentation.register


sealed interface RegisterUiEffect {
    data object NavigateToLogin :RegisterUiEffect
    data class ShowErrorSnackBar(val message: String) : RegisterUiEffect
}