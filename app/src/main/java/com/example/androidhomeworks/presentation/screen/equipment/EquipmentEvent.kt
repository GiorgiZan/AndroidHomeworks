package com.example.androidhomeworks.presentation.screen.equipment

sealed class EquipmentEvent {
    data class FetchEquipmentEvent(val filteredName: String = "") : EquipmentEvent()
}