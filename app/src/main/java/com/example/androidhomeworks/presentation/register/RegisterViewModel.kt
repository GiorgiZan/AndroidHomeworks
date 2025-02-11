package com.example.androidhomeworks.presentation.register

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidhomeworks.common.ApiHelper
import com.example.androidhomeworks.common.Resource
import com.example.androidhomeworks.data.remote.register.RegisterDto
import com.example.androidhomeworks.retrofit.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    private val _registerState = MutableStateFlow<Resource<Unit>>(Resource.Loading)
    val registerState: StateFlow<Resource<Unit>> = _registerState


    fun register(context: Context, email: String, password: String) {
        _registerState.value = Resource.Loading
        viewModelScope.launch {
            val result = ApiHelper.handleHttpRequest(context = context, apiCall = {
                RetrofitClient.retrofitService.register(RegisterDto(email, password))
            })
            when (result) {
                is Resource.Success -> {
                    _registerState.value = Resource.Success(Unit)
                }

                is Resource.Error -> {
                    _registerState.value = Resource.Error(result.errorMessage)

                }

                is Resource.Loading -> {
                }
            }

        }
    }

}