package com.example.androidhomeworks.dto

import kotlinx.serialization.Serializable

@Serializable
data class UsersListDto(
    val status: Boolean,
    val additional_data: String?,
    val options: String?,
    val permissions: List<String?>,
    val users: List<UserDto>
) {

    @Serializable
    data class UserDto(
        val id: Int,
        val avatar: String?,
        val first_name: String,
        val last_name: String,
        val about: String?,
        val activation_status: Double
    )
}