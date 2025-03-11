package com.example.androidhomeworks.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidhomeworks.common.Resource
import com.example.androidhomeworks.domain.usecase.datastore.GetEmailUseCase
import com.example.androidhomeworks.domain.usecase.datastore.SaveLoginInfoUseCase
import com.example.androidhomeworks.domain.usecase.datastore.SaveSessionEmailUseCase
import com.example.androidhomeworks.domain.usecase.login.LoginUseCase
import com.example.androidhomeworks.domain.usecase.validation.EmailValidationUseCase
import com.example.androidhomeworks.domain.usecase.validation.PasswordValidationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val saveLoginInfoUseCase: SaveLoginInfoUseCase,
    private val saveSessionEmailUseCase: SaveSessionEmailUseCase,
    private val emailValidationUseCase: EmailValidationUseCase,
    private val passwordValidationUseCase: PasswordValidationUseCase,
    getEmailUseCase: GetEmailUseCase

) : ViewModel() {
    private val _loginState = MutableStateFlow<Resource<Unit>>(Resource.Loading)
    val loginState: StateFlow<Resource<Unit>> = _loginState

    private val _uiEvent = MutableSharedFlow<LoginUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    val email: Flow<String?> = getEmailUseCase()


    fun login(email: String, password: String, rememberMe: Boolean) {
        viewModelScope.launch {
            val isEmailValid = emailValidationUseCase(email)
            val isPasswordValid = passwordValidationUseCase(password)

            if (!isEmailValid) {
                _uiEvent.emit(LoginUiEvent.ShowEmailError)
                return@launch
            }

            else if (!isPasswordValid) {
                _uiEvent.emit(LoginUiEvent.ShowPasswordError)
                return@launch
            }
            else {
                _uiEvent.emit(LoginUiEvent.LoginSuccess)
            }

            val result = loginUseCase(email, password)
            when (result) {
                is Resource.Success -> {
                    if (rememberMe) {
                        saveLoginInfoUseCase(email)
                    } else {
                        saveSessionEmailUseCase(email)
                    }
                    _loginState.value = Resource.Success(Unit)
                }

                is Resource.Error -> {
                    _loginState.value = Resource.Error(result.errorMessage)
                }

                is Resource.Loading -> {
                }
            }
        }
    }


}