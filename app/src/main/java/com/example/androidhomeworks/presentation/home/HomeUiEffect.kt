package com.example.androidhomeworks.presentation.home


sealed interface HomeUiEffect {
    data object NavigateToProfile : HomeUiEffect
    data class ShowErrorSnackBar(val message: String) : HomeUiEffect
}