package com.example.androidhomeworks.domain.repository

import com.example.androidhomeworks.domain.model.ValidateAccountNumber
import com.example.androidhomeworks.domain.resource.Resource
import kotlinx.coroutines.flow.Flow

interface ValidateAccountNumberRepository {
    suspend fun validateAccount(accountNumber: String): Flow<Resource<ValidateAccountNumber>>
}