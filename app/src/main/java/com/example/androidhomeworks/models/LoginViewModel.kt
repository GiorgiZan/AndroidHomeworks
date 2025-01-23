package com.example.androidhomeworks.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidhomeworks.dto_response.LoginDto
import com.example.androidhomeworks.retrofit.RetrofitClient
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel : ViewModel() {

    fun login(
        email: String,
        password: String,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.retrofitService.login(LoginDto(email, password))

                if (response.isSuccessful) {
                    onSuccess("Login Successful")
                } else {
                    val errorMessage = response.errorBody()?.string() ?: "Login failed"
                    onError("Error: ${response.code()} - $errorMessage")
                }
            } catch (e: HttpException) {
                onError(e.response()?.errorBody()?.string() ?: "An HttpException occurred")
            } catch (e: Exception) {
                onError(e.message ?: "An Exception occurred")
            }
        }
    }


}