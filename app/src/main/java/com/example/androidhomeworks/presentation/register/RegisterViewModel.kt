package com.example.androidhomeworks.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidhomeworks.common.Resource
import com.example.androidhomeworks.data.repository.RegisterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerRepository: RegisterRepository
) : ViewModel() {

    private val _registerState = MutableStateFlow<Resource<Unit>>(Resource.Loading)
    val registerState: StateFlow<Resource<Unit>> = _registerState


    fun register(email: String, password: String) {
        _registerState.value = Resource.Loading
        viewModelScope.launch {
            val result = registerRepository.register(email, password)
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