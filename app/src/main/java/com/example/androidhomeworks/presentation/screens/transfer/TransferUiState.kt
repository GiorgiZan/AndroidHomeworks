package com.example.androidhomeworks.presentation.screens.transfer

import com.example.androidhomeworks.presentation.model.UiAccount

data class TransferUiState(
    val isLoading: Boolean = false,
    val accountsList: List<UiAccount>? = null,
    val validationResult: String? = null,
    val course:Double? = null
)