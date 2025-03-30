package com.example.androidhomeworks.data.remote.model.account_number_validation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ValidateAccountNumberDto(
    @SerialName("status") val status: String
)