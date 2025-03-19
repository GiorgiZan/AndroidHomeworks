package com.example.androidhomeworks.domain.model

import kotlinx.datetime.Instant
import java.util.UUID

data class Equipment(
    val id: UUID,
    val name: String,
    val nameDe: String,
    val createdAt: Instant,
    val bglNumber: String? = null,
    val bglVariant: String? = null,
    val orderId: Int? = null,
    val main: Boolean? = null,
    val children: List<Equipment> = emptyList()
)

fun Equipment.flatten(): List<Equipment> {
    return listOf(this) + children.flatMap { it.flatten() }
}