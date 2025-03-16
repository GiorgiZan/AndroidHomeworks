package com.example.androidhomeworks.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidhomeworks.domain.resource.Resource
import com.example.androidhomeworks.domain.usecase.datastore.ClearLoginInfoUseCase
import com.example.androidhomeworks.domain.usecase.datastore.GetEmailUseCase
import com.example.androidhomeworks.domain.usecase.datastore.GetRememberMeUseCase
import com.example.androidhomeworks.domain.usecase.login.LoginUseCase
import com.example.androidhomeworks.domain.usecase.validation.EmailValidationUseCase
import com.example.androidhomeworks.domain.usecase.validation.PasswordValidationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val emailValidationUseCase: EmailValidationUseCase,
    private val passwordValidationUseCase: PasswordValidationUseCase,
    getEmailUseCase: GetEmailUseCase,
    clearLoginInfoUseCase: ClearLoginInfoUseCase,
    getRememberMeUseCase: GetRememberMeUseCase

) : ViewModel() {
    private val _loginState = MutableStateFlow(LoginState())
    val loginState: StateFlow<LoginState> = _loginState

    private val _uiEvent = MutableSharedFlow<LoginUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()


    init {
        viewModelScope.launch {
            if (!getRememberMeUseCase().first()!!){
                clearLoginInfoUseCase()
            }
            val savedEmail = getEmailUseCase().first()

            if (!savedEmail.isNullOrEmpty()) {
                _loginState.value = _loginState.value.copy(success = true)
                _uiEvent.emit(LoginUiEvent.NavigateToHomeScreen)
            }
        }
    }

    fun login(email: String, password: String, rememberMe: Boolean) {
        viewModelScope.launch {
            if (!emailValidationUseCase(email)) {
                _uiEvent.emit(LoginUiEvent.ShowEmailError)
                return@launch
            }
            if (!passwordValidationUseCase(password)) {
                _uiEvent.emit(LoginUiEvent.ShowPasswordError)
                return@launch
            }

            _loginState.update { it.copy(isLoading = true, error = null) }

            loginUseCase(email, password, rememberMe).collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        _loginState.update { LoginState(success = true) }
                        _uiEvent.emit(LoginUiEvent.NavigateToHomeScreen)
                    }
                    is Resource.Error -> {
                        _loginState.update { LoginState(error = result.errorMessage) }
                    }
                    is Resource.Loading -> {
                        _loginState.update { it.copy(isLoading = true) }
                    }
                }
            }
        }
    }

}