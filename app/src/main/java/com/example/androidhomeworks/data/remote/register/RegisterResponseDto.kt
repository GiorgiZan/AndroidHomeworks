package com.example.androidhomeworks.data.remote.register

import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponseDto(
    val id: Int,
    val token: String
) {
}