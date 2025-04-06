package com.example.androidhomeworks.presentation.profile

sealed interface ProfileUiEffect {
    data object OnNavigateToLogin:ProfileUiEffect
}