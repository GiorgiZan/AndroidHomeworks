package com.example.androidhomeworks.viewmodel


data class StateManagement(
    val isLoading: Boolean = false,
    val successMessage: String? = null,
    val errorMessage: String? = null
)