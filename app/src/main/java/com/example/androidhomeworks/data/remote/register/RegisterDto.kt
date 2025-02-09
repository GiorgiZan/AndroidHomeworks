package com.example.androidhomeworks.data.remote.register

import kotlinx.serialization.Serializable

@Serializable
data class RegisterDto(
    val email: String,
    val password: String
)