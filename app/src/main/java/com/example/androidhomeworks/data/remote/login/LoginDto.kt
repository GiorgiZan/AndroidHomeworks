package com.example.androidhomeworks.data.remote.login

import kotlinx.serialization.Serializable

@Serializable
data class LoginDto(
    val email: String,
    val password: String
)