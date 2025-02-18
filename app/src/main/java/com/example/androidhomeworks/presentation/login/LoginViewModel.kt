package com.example.androidhomeworks.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidhomeworks.common.Resource
import com.example.androidhomeworks.data.repository.DataStoreRepository
import com.example.androidhomeworks.data.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    private val loginRepository: LoginRepository
) : ViewModel() {
    private val _loginState = MutableStateFlow<Resource<Unit>>(Resource.Loading)
    val loginState: StateFlow<Resource<Unit>> = _loginState


    private val _loggedInEmail = MutableStateFlow<String?>(null)
    val loggedInEmail: StateFlow<String?> = _loggedInEmail

    init {
        viewModelScope.launch {
            dataStoreRepository.clearEmailIfNotRemembered()
            dataStoreRepository.email.collectLatest { email ->
                _loggedInEmail.value = email
            }
        }

    }

    fun login(email: String, password: String, rememberMe: Boolean) {
        viewModelScope.launch {
            val result = loginRepository.login(email, password)
            when (result) {
                is Resource.Success -> {
                    dataStoreRepository.saveLoginInfo(email, rememberMe)
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