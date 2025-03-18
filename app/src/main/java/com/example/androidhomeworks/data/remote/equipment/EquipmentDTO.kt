package com.example.androidhomeworks.data.remote.equipment

import com.example.androidhomeworks.common.UUIDSerializer
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.datetime.Instant
import java.util.UUID

@Serializable
data class EquipmentDTO(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID,
    val name: String,
    @SerialName("name_de") val nameDe: String,
    @Contextual val createdAt: Instant,
    val bglNumber: String? = null,
    val bglVariant: String? = null,
    val orderId: Int? = null,
    val main: Boolean? = null,
    val children: List<EquipmentDTO> = emptyList()
)
