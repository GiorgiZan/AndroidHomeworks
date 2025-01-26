package com.example.androidhomeworks.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidhomeworks.dto_response.UsersDto
import com.example.androidhomeworks.retrofit.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val _stateManagement = MutableStateFlow(StateManagement())
    val stateManagement: StateFlow<StateManagement> = _stateManagement

    private val _usersList = MutableStateFlow<List<UsersDto.User>>(emptyList())
    val usersList: StateFlow<List<UsersDto.User>> = _usersList

    fun getUsers() {
        _stateManagement.value = StateManagement(isLoading = true)

        viewModelScope.launch {
            try {
                val response: Response<UsersDto> = RetrofitClient.retrofitService.getUsers()

                if (response.isSuccessful) {
                    _usersList.value = response.body()?.data ?: emptyList()
                    _stateManagement.value = StateManagement(
                        isLoading = false,
                    )
                }

            } catch (e: HttpException) {
                _stateManagement.value = StateManagement(
                    isLoading = false,
                )
            } catch (e: Exception) {
                _stateManagement.value = StateManagement(
                    isLoading = false,
                )
            }
        }
    }
}
