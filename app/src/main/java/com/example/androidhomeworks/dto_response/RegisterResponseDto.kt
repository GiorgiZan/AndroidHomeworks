package com.example.androidhomeworks.dto_response

import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponseDto(
    val id: Int,
    val token: String
) {
}