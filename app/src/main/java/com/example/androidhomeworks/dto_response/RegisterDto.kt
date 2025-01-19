package com.example.androidhomeworks.dto_response

import kotlinx.serialization.Serializable

@Serializable
data class RegisterDto(
    val email: String,
    val password: String
)