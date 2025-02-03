package com.example.androidhomeworks.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidhomeworks.database.UserDatabase
import com.example.androidhomeworks.database.UserEntity
import com.example.androidhomeworks.retrofit.RetrofitClient.retrofitService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val userDao = UserDatabase.getDatabase(application).userDao()

    private val _users = MutableStateFlow<List<UserEntity>>(emptyList())
    val users: StateFlow<List<UserEntity>> get() = _users
    private val _stateManagement = MutableStateFlow(StateManagement())
    val stateManagement: StateFlow<StateManagement> = _stateManagement
    private val connectivityManager =
        application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager


    init {
        fetchUsersFromServer()

        viewModelScope.launch {
            userDao.getUsers().collect { userList ->
                _users.value = userList
            }
        }
    }

    private fun fetchUsersFromServer() {
        viewModelScope.launch {
            try {
                if (!isOnline()) {
                    _stateManagement.value = StateManagement(
                        errorMessage = "No internet connection, loading saved users"
                    )
                    return@launch
                }
                _stateManagement.value = StateManagement(isLoading = true)
                val response = retrofitService.getUsers()
                if (response.isSuccessful) {

                    response.body()?.let { usersList ->
                        val currentUsers = userDao.getUsers().first()

                        val newUserEntities = usersList.users.filter { apiUser ->
                            currentUsers.none { dbUser -> dbUser.id == apiUser.id }
                        }.map { userDto ->
                            UserEntity(
                                id = 0,
                                avatar = userDto.avatar,
                                first_name = userDto.first_name,
                                last_name = userDto.last_name,
                                about = userDto.about,
                                activation_status = userDto.activation_status
                            )
                        }
                        _stateManagement.value = StateManagement(
                            isLoading = false,
                            successMessage = "Users fetched"
                        )

                        if (newUserEntities.isNotEmpty()) {
                            userDao.insertUsers(newUserEntities)
                        }
                    }
                } else {
                    val errorMessage = response.errorBody()?.string() ?: "Users failed to fetch"
                    _stateManagement.value = StateManagement(
                        isLoading = false,
                        errorMessage = "Error: ${response.code()} - $errorMessage"
                    )
                }
            }
            catch (e: Exception) {
                _stateManagement.value = StateManagement(
                    isLoading = false,
                    errorMessage = e.message ?: "An Exception occurred"
                )
            }
        }
    }

    private fun isOnline(): Boolean {

        val networkInfo = connectivityManager.activeNetwork
        return networkInfo != null
    }
}

