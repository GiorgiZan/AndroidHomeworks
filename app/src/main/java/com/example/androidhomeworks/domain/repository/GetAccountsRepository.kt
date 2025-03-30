package com.example.androidhomeworks.domain.repository

import com.example.androidhomeworks.domain.model.Account
import com.example.androidhomeworks.domain.resource.Resource
import kotlinx.coroutines.flow.Flow

interface GetAccountsRepository {
    suspend fun getAccount(): Flow<Resource<List<Account>>>
}