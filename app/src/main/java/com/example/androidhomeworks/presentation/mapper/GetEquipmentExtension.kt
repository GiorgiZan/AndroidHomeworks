package com.example.androidhomeworks.presentation.mapper

import com.example.androidhomeworks.domain.model.Equipment
import com.example.androidhomeworks.presentation.model.GetEquipment

fun Equipment.toPresenter(): GetEquipment {
    return GetEquipment(
        id = this.id,
        name = this.name,
        nameDe = this.nameDe,
        createdAt = this.createdAt,
        bglNumber = this.bglNumber,
        bglVariant = this.bglVariant,
        orderId = this.orderId,
        main = this.main,
        children = this.children.map { it.toPresenter() }
    )
}

fun GetEquipment.flatten(): List<GetEquipment> {
    return listOf(this) + children.flatMap { it.flatten() }
}