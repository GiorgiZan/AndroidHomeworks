package com.example.androidhomeworks.presentation.home

sealed interface HomeUiEvent {
    data object OnProfileClick:HomeUiEvent
}