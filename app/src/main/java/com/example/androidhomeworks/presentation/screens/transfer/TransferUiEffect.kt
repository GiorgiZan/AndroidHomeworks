package com.example.androidhomeworks.presentation.screens.transfer

sealed interface TransferUiEffect {
    data class Failure(val error: String) : TransferUiEffect
}