package com.example.androidhomeworks.data.remote.login

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDto(
    val token: String
)