package com.example.androidhomeworks.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidhomeworks.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository):ViewModel() {
    val userPreferencesFlow = repository.userPreferencesFlow

    fun saveUser(firstName: String, lastName: String, email: String) {
        viewModelScope.launch {
            repository.saveUser(firstName, lastName, email)
        }
    }

}