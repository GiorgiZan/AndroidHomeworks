package com.example.androidhomeworks.presentation.model

import kotlinx.datetime.Instant
import java.util.UUID

data class GetEquipment(
    val id: UUID,
    val name: String,
    val nameDe: String,
    val createdAt: Instant,
    val bglNumber: String? = null,
    val bglVariant: String? = null,
    val orderId: Int? = null,
    val main: Boolean? = null,
    val children: List<GetEquipment> = emptyList()
)


fun GetEquipment.calculateDepth(allEquipment: List<GetEquipment>, depth: Int = 0): Int {
    for (parent in allEquipment) {
        if (parent.children.contains(this)) {
            return parent.calculateDepth(allEquipment, depth + 1)
        }
    }
    return depth
}