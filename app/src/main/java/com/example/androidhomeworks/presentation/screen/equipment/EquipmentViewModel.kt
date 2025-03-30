package com.example.androidhomeworks.presentation.screen.equipment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidhomeworks.domain.resource.Resource
import com.example.androidhomeworks.domain.usecase.GetEquipmentUseCase
import com.example.androidhomeworks.presentation.mapper.toPresenter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EquipmentViewModel @Inject constructor(
    private val getEquipmentUseCase: GetEquipmentUseCase
): ViewModel() {
    private val _equipmentState = MutableStateFlow(EquipmentState())
    val equipmentState: StateFlow<EquipmentState> = _equipmentState


    fun onEvent(event:EquipmentEvent){
        when(event){
            is EquipmentEvent.FetchEquipmentEvent -> getEquipment(event.filteredName)
        }
    }

    private fun getEquipment(filteredName:String) {
        viewModelScope.launch {
            _equipmentState.update { it.copy(isLoading = true) }

            getEquipmentUseCase(filteredName).collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        val getEquipmentList = result.data.map { it.toPresenter() }

                        _equipmentState.update { EquipmentState(success = getEquipmentList) }
                    }

                    is Resource.Error -> {
                        _equipmentState.update { EquipmentState(error = result.errorMessage) }
                    }

                    is Resource.Loading -> {
                        _equipmentState.update { it.copy(isLoading = true) }
                    }
                }
            }
        }
    }

}