package com.example.androidhomeworks.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidhomeworks.domain.resource.Resource
import com.example.androidhomeworks.domain.usecase.register.RegisterUseCase
import com.example.androidhomeworks.domain.usecase.validation.EmailValidationUseCase
import com.example.androidhomeworks.domain.usecase.validation.PasswordValidationUseCase
import com.example.androidhomeworks.domain.usecase.validation.RepeatPasswordValidationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val emailValidationUseCase: EmailValidationUseCase,
    private val passwordValidationUseCase: PasswordValidationUseCase,
    private val repeatPasswordValidationUseCase: RepeatPasswordValidationUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState

    private val _uiEffect = MutableSharedFlow<RegisterUiEffect>()
    val uiEffect = _uiEffect.asSharedFlow()


    fun onEvent(event: RegisterUiEvent) {
        when (event) {
            RegisterUiEvent.OnLoginClick -> onLoginNav()
            is RegisterUiEvent.OnEmailChanged -> updateState {
                copy(
                    email = event.email,
                    isEmailValid = isEmailValid(event.email)
                )
            }

            is RegisterUiEvent.OnPasswordChanged -> updateState {
                copy(
                    password = event.password,
                    isPasswordValid = isPasswordValid(event.password)
                )
            }

            is RegisterUiEvent.OnRepeatedPasswordChanged -> updateState {
                copy(
                    repeatedPassword = event.repeatedPassword,
                    isRepeatedPasswordValid = isRepeatedPasswordValid(event.password, event.repeatedPassword)
                )
            }

            is RegisterUiEvent.Register -> register(event.email, event.password)
        }
    }

    private fun register(email: String, password: String) {
        viewModelScope.launch {
            updateState { copy(isLoading = true) }

            registerUseCase(email, password).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _uiState.update { RegisterUiState(success = true, isLoading = false) }
                        sendEffect(RegisterUiEffect.NavigateToLogin)
                    }

                    is Resource.Error -> {
                        updateState { copy(isLoading = false) }
                        sendEffect(RegisterUiEffect.ShowErrorSnackBar(result.errorMessage))
                    }

                    is Resource.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }
                }
            }
        }
    }

    private fun onLoginNav() {
        sendEffect(RegisterUiEffect.NavigateToLogin)
    }

    private fun isPasswordValid(password: String): Boolean {
        return passwordValidationUseCase(password)
    }

    private fun isRepeatedPasswordValid(password: String, repeatedPassword: String): Boolean {
        return repeatPasswordValidationUseCase(password, repeatedPassword)
    }

    private fun isEmailValid(email: String): Boolean {
        return emailValidationUseCase(email)
    }


    private fun updateState(state: RegisterUiState.() -> RegisterUiState) {
        _uiState.update(state)
    }

    private fun sendEffect(effect: RegisterUiEffect) {
        viewModelScope.launch(Dispatchers.Main.immediate) {
            _uiEffect.emit(effect)
        }
    }

}