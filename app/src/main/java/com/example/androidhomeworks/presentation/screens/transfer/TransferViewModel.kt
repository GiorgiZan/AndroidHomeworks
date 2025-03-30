package com.example.androidhomeworks.presentation.screens.transfer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidhomeworks.domain.resource.Resource
import com.example.androidhomeworks.domain.usecase.GetAccountUseCase
import com.example.androidhomeworks.domain.usecase.GetCourseUseCase
import com.example.androidhomeworks.domain.usecase.ValidateAccountNumberUseCase
import com.example.androidhomeworks.presentation.mapper.toPresenter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransferViewModel @Inject constructor(
    private val getAccountUseCase: GetAccountUseCase,
    private val validateAccountNumberUseCase: ValidateAccountNumberUseCase,
    private val getCourseUseCase: GetCourseUseCase
) : ViewModel() {

    private val _transferUiState = MutableStateFlow(TransferUiState())
    val transferUiState: StateFlow<TransferUiState> = _transferUiState

    private val _uiEffect = MutableSharedFlow<TransferUiEffect>()
    val uiEffect = _uiEffect.asSharedFlow()

    fun onEvent(event: TransferEvent) {
        when (event) {
            is TransferEvent.FetchAccountEvent -> getAccount()
            is TransferEvent.ValidateAccountNumber -> validateAccountNumber(event.accountNumber)
            is TransferEvent.GetCourse -> getCourse(event.fromAccount,event.toAccount)
        }
    }

    private fun getAccount() {
        viewModelScope.launch {
            _transferUiState.update { it.copy(isLoading = true) }
            getAccountUseCase().collectLatest { result ->
                when (result) {
                    is Resource.Success -> _transferUiState.update { TransferUiState(accountsList = result.data.map { it.toPresenter() }) }

                    is Resource.Error -> _uiEffect.emit(TransferUiEffect.Failure(result.errorMessage))

                    Resource.Loading -> _transferUiState.update { it.copy(isLoading = true) }
                }

            }
        }
    }

    private fun validateAccountNumber(accountNumber: String) {
        viewModelScope.launch {
            validateAccountNumberUseCase(accountNumber).collectLatest { result ->
                when (result) {
                    is Resource.Success -> _transferUiState.update {
                        TransferUiState(
                            validationResult = result.data.toString()
                        )
                    }

                    is Resource.Error -> _uiEffect.emit(TransferUiEffect.Failure(result.errorMessage))

                    Resource.Loading -> _transferUiState.update { it.copy(isLoading = true) }
                }

            }
        }
    }

    private fun getCourse(fromAccount: String, toAccount: String){
        viewModelScope.launch {
            getCourseUseCase(fromAccount,toAccount).collectLatest { result ->
                when(result){
                    is Resource.Success -> _transferUiState.update {
                        TransferUiState(
                            course = result.data.course
                        )
                    }
                    is Resource.Error -> _uiEffect.emit(TransferUiEffect.Failure(result.errorMessage))
                    Resource.Loading -> _transferUiState.update { it.copy(isLoading = true) }
                }
            }
        }
    }
}