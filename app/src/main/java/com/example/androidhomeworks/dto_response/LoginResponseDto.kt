package com.example.androidhomeworks.dto_response

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDto(
    val token: String
)