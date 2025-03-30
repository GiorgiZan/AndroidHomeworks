package com.example.androidhomeworks.data.mapper

import com.example.androidhomeworks.data.remote.equipment.EquipmentDTO
import com.example.androidhomeworks.domain.model.Equipment

fun EquipmentDTO.toDomain(): Equipment {
    return Equipment(
        id, name, nameDe, createdAt, bglNumber, bglVariant, orderId, main, children.map { it.toDomain() })
}