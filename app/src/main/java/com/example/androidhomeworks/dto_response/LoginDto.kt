package com.example.androidhomeworks.dto_response

import kotlinx.serialization.Serializable

@Serializable
data class LoginDto(
    val email: String,
    val password: String
)