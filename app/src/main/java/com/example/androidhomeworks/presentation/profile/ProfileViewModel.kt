package com.example.androidhomeworks.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidhomeworks.domain.usecase.datastore.ClearSessionEmailUseCase
import com.example.androidhomeworks.domain.usecase.datastore.GetSessionEmailUseCase
import com.example.androidhomeworks.domain.usecase.datastore.ClearLoginInfoUseCase
import com.example.androidhomeworks.domain.usecase.datastore.GetEmailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val clearLoginInfoUseCase: ClearLoginInfoUseCase,
    private val clearSessionEmailUseCase: ClearSessionEmailUseCase,
    getEmailUseCase: GetEmailUseCase,
    getSessionEmailUseCase: GetSessionEmailUseCase
) : ViewModel() {
    val email: Flow<String?> = getEmailUseCase()

    val sessionEmail: Flow<String?> = getSessionEmailUseCase()

    fun logout(onComplete: () -> Unit) {
        viewModelScope.launch {
            clearLoginInfoUseCase()
            clearSessionEmailUseCase()
            onComplete()
        }
    }
}