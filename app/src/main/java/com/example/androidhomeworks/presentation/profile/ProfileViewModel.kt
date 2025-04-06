package com.example.androidhomeworks.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidhomeworks.domain.usecase.datastore.ClearLoginInfoUseCase
import com.example.androidhomeworks.domain.usecase.datastore.GetEmailUseCase
import com.example.androidhomeworks.presentation.login.LoginUiEffect
import com.example.androidhomeworks.presentation.login.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val clearLoginInfoUseCase: ClearLoginInfoUseCase,
    private val getEmailUseCase: GetEmailUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState

    private val _uiEffect = MutableSharedFlow<ProfileUiEffect>()
    val uiEffect = _uiEffect.asSharedFlow()


    init {
        getEmail()
    }

    fun onEvent(event: ProfileUiEvent){
        when(event){
            ProfileUiEvent.OnSignOutClick -> {
                logout()
            }
        }
    }

    private fun onSignOutClick(){
        sendEffect(ProfileUiEffect.OnNavigateToLogin)
    }

    private fun logout() {
        viewModelScope.launch {
            clearLoginInfoUseCase()
            onSignOutClick()
        }
    }

    private fun getEmail(){
        viewModelScope.launch {
            val email = getEmailUseCase().first() ?: ""
            updateState { copy(email = email ) }
        }
    }

    private fun updateState(state: ProfileUiState.() -> ProfileUiState) {
        _uiState.update(state)
    }

    private fun sendEffect(effect: ProfileUiEffect) {
        viewModelScope.launch(Dispatchers.Main.immediate) {
            _uiEffect.emit(effect)
        }
    }
}