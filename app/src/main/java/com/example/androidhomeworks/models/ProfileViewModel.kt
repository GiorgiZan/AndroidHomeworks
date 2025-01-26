package com.example.androidhomeworks.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidhomeworks.datastore.MyDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ProfileViewModel(private val myDataStore: MyDataStore) : ViewModel() {
    val email: Flow<String?> = myDataStore.email

    val sessionEmail: Flow<String?> = myDataStore.sessionEmail

    fun logout(onComplete: () -> Unit) {
        viewModelScope.launch {
            myDataStore.clearLoginInfo()
            myDataStore.clearSessionEmail()
            onComplete()
        }
    }
}