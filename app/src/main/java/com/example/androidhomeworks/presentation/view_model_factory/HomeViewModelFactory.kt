package com.example.androidhomeworks.presentation.view_model_factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidhomeworks.data.local.room.user.UserDatabase
import com.example.androidhomeworks.presentation.home.HomeViewModel

class HomeViewModelFactory(
    private val database: UserDatabase,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
