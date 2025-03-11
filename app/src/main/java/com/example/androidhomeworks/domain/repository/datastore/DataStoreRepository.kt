package com.example.androidhomeworks.domain.repository.datastore

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    val email: Flow<String?>
    val sessionEmail: Flow<String?>

    suspend fun saveLoginInfo(email: String)
    suspend fun clearLoginInfo()
    suspend fun saveSessionEmail(email: String)
    suspend fun clearSessionEmail()
}