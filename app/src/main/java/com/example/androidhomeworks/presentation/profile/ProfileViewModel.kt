package com.example.androidhomeworks.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidhomeworks.data.repository.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val dataStoreRepository: DataStoreRepository) : ViewModel() {
    val email: Flow<String?> = dataStoreRepository.email


    fun logout(onComplete: () -> Unit) {
        viewModelScope.launch {
            dataStoreRepository.clearLoginInfo()
            onComplete()
        }
    }
}