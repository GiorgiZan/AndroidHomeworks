package com.example.androidhomeworks.domain.usecase.datastore

import com.example.androidhomeworks.domain.repository.datastore.DataStoreRepository
import javax.inject.Inject

class SaveLoginInfoUseCase @Inject constructor(private val dataStore: DataStoreRepository) {
    suspend operator fun invoke(email: String) {
        dataStore.saveLoginInfo(email)
    }
}