package com.example.androidhomeworks.data.mapper

import com.example.androidhomeworks.data.remote.model.account_number_validation.ValidateAccountNumberDto
import com.example.androidhomeworks.domain.model.ValidateAccountNumber

fun ValidateAccountNumberDto.toDomain() : ValidateAccountNumber {
    return ValidateAccountNumber(
        status = status
    )
}