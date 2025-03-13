package com.example.androidhomeworks.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(
    val id: Int,
    val token: String
) {
}