package com.example.androidhomeworks.presentation.profile

sealed interface ProfileUiEvent {
    data object OnSignOutClick:ProfileUiEvent
}