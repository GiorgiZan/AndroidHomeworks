package com.example.androidhomeworks.presentation.home

import com.example.androidhomeworks.data.local.room.user.UserEntity


data class HomeUiState(
    val isLoading: Boolean = false,
    val success: Boolean = false,
    val listOfUsers: List<UserEntity>? = null
)