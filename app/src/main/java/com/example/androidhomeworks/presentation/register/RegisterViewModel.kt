package com.example.androidhomeworks.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidhomeworks.domain.resource.Resource
import com.example.androidhomeworks.domain.usecase.register.RegisterUseCase
import com.example.androidhomeworks.domain.usecase.validation.EmailValidationUseCase
import com.example.androidhomeworks.domain.usecase.validation.PasswordValidationUseCase
import com.example.androidhomeworks.domain.usecase.validation.RepeatPasswordValidationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
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

    private val _registerState = MutableStateFlow(RegisterState())
    val registerState: StateFlow<RegisterState> = _registerState

    private val _uiEvent = MutableSharedFlow<RegisterUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()


    fun register(email: String, password: String, repeatPassword: String) {
        viewModelScope.launch {
            val isEmailValid = emailValidationUseCase(email)
            val isPasswordValid = passwordValidationUseCase(password)
            val isRepeatPasswordValid = repeatPasswordValidationUseCase(password, repeatPassword)

            if (!isEmailValid) {
                _uiEvent.emit(RegisterUiEvent.ShowEmailError)
                return@launch
            } else if (!isPasswordValid) {
                _uiEvent.emit(RegisterUiEvent.ShowPasswordError)
                return@launch
            } else if (!isRepeatPasswordValid) {
                _uiEvent.emit(RegisterUiEvent.ShowRepeatPasswordError)
                return@launch
            }
            _registerState.update { it.copy(isLoading = true, error = null) }

            registerUseCase(email, password).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _registerState.update { RegisterState(success = true) }
                        _uiEvent.emit(RegisterUiEvent.NavigateToLoginScreen)
                    }

                    is Resource.Error -> {
                        _registerState.update { RegisterState(error = result.errorMessage) }
                    }

                    is Resource.Loading -> {
                        _registerState.update { it.copy(isLoading = true) }
                    }
                }
            }
        }
    }

}