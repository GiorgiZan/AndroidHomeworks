package com.example.androidhomeworks.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidhomeworks.datastore.MyDataStore
import com.example.androidhomeworks.dto_response.LoginDto
import com.example.androidhomeworks.retrofit.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel(private val myDataStore: MyDataStore) : ViewModel() {
    private val _stateManagement = MutableStateFlow(StateManagement())
    val stateManagement: StateFlow<StateManagement> = _stateManagement


    fun login(email: String, password: String, rememberMe: Boolean) {
        _stateManagement.value = StateManagement(isLoading = true)

        viewModelScope.launch {
            try {
                val response = RetrofitClient.retrofitService.login(LoginDto(email, password))

                if (response.isSuccessful) {
                    if (rememberMe) {
                        myDataStore.saveLoginInfo(email)
                    } else {
                        myDataStore.saveSessionEmail(email)
                    }
                    _stateManagement.value = StateManagement(
                        isLoading = false,
                        successMessage = "Login Successful"
                    )
                } else {
                    val errorMessage = response.errorBody()?.string() ?: "Login failed"
                    _stateManagement.value = StateManagement(
                        isLoading = false,
                        errorMessage = "Error: ${response.code()} - $errorMessage"
                    )
                }
            } catch (e: HttpException) {
                _stateManagement.value = StateManagement(
                    isLoading = false,
                    errorMessage = e.response()?.errorBody()?.string()
                        ?: "An HttpException occurred"
                )
            } catch (e: Exception) {
                _stateManagement.value = StateManagement(
                    isLoading = false,
                    errorMessage = e.message ?: "An Exception occurred"
                )
            }
        }
    }


}