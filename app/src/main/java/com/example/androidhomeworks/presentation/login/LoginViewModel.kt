package com.example.androidhomeworks.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidhomeworks.common.ApiHelper
import com.example.androidhomeworks.common.Resource
import com.example.androidhomeworks.data.local.MyDataStore
import com.example.androidhomeworks.data.remote.login.LoginDto
import com.example.androidhomeworks.retrofit.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val myDataStore: MyDataStore) : ViewModel() {
    private val _loginState = MutableStateFlow<Resource<Unit>>(Resource.Loading)
    val loginState: StateFlow<Resource<Unit>> = _loginState


    fun login(email: String, password: String, rememberMe: Boolean) {
        viewModelScope.launch {
            val result = ApiHelper.handleHttpRequest(apiCall = {
                RetrofitClient.retrofitService.login(LoginDto(email, password))
            })
            when (result) {
                is Resource.Success -> {
                    if (rememberMe) {
                        myDataStore.saveLoginInfo(email)
                    } else {
                        myDataStore.saveSessionEmail(email)
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