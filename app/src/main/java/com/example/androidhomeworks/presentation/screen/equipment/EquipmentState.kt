package com.example.androidhomeworks.presentation.screen.equipment

import com.example.androidhomeworks.presentation.model.GetEquipment

data class EquipmentState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val success: List<GetEquipment>? = null
)
