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
import kotlin.math.log


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

    private val _uiEffect = MutableSharedFlow<LoginUiEffect>()
    val uiEffect = _uiEffect.asSharedFlow()

    init {
        viewModelScope.launch {
            if (!getRememberMeUseCase().first()!!) {
                clearLoginInfoUseCase()
            }
            val savedEmail = getEmailUseCase().first()

            if (!savedEmail.isNullOrEmpty()) {
                _loginState.value = _loginState.value.copy(success = true)
                _uiEffect.emit(LoginUiEffect.NavigateToHomeScreen)
            }
        }
    }

    fun onEvent(event: LoginUiEvent) {
        when (event) {
            is LoginUiEvent.Login -> login(event.email, event.password, event.rememberMe)
            is LoginUiEvent.OnEmailChanged -> _loginState.update { state ->
                state.copy(email = event.email, isEmailValid = isEmailValid(event.email))
            }

            is LoginUiEvent.OnPasswordChanged -> _loginState.update { state ->
                state.copy(password = event.password, isPasswordValid = isPasswordValid(event.password))
            }

            is LoginUiEvent.OnRememberMeChanged -> _loginState.update { state ->
                state.copy(rememberMe = event.checked)
            }
        }
    }

    private fun login(email: String, password: String, rememberMe: Boolean) {
        viewModelScope.launch {
            _loginState.update { it.copy(isLoading = true) }

            loginUseCase(email, password, rememberMe).collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        _loginState.update { it.copy(success = true, isLoading = false) }
                        _uiEffect.emit(LoginUiEffect.NavigateToHomeScreen)
                    }

                    is Resource.Error -> {
                        _loginState.update { it.copy(isLoading = false) }
                        _uiEffect.emit(LoginUiEffect.ShowErrorSnackBar(result.errorMessage))
                    }

                    is Resource.Loading -> {
                        _loginState.update { it.copy(isLoading = true) }
                    }
                }
            }
        }
    }

    private fun isPasswordValid(password: String): Boolean{
        return passwordValidationUseCase(password)
    }

    private fun isEmailValid(email:String): Boolean{
        return emailValidationUseCase(email)
    }

}
