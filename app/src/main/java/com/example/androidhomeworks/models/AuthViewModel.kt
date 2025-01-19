package com.example.androidhomeworks.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidhomeworks.dto_response.LoginDto
import com.example.androidhomeworks.dto_response.RegisterDto
import com.example.androidhomeworks.retrofit.RetrofitClient
import kotlinx.coroutines.launch
import retrofit2.HttpException

class AuthViewModel : ViewModel() {

    fun login(
        email: String,
        password: String,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                RetrofitClient.retrofitService.login(LoginDto(email, password))
                onSuccess("Login Successful")
            } catch (e: HttpException) {
                onError(e.response()?.errorBody()?.string() ?: "An HttpException occurred")
            } catch (e: Exception) {
                onError(e.message ?: "An Exception occurred")
            }
        }
    }

    fun register(
        email: String,
        password: String,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                RetrofitClient.retrofitService.register(RegisterDto(email, password))
                onSuccess("Register Successful")
            } catch (e: HttpException) {
                onError(e.response()?.errorBody()?.string() ?: "An HttpException occurred")
            } catch (e: Exception) {
                onError(e.message ?: "An Exception occurred")
            }
        }
    }
}