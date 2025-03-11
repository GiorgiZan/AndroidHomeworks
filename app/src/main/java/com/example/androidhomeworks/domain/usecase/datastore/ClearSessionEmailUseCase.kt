package com.example.androidhomeworks.domain.usecase

import com.example.androidhomeworks.domain.repository.datastore.DataStoreRepository
import javax.inject.Inject

class ClearSessionEmailUseCase @Inject constructor(private val dataStore: DataStoreRepository) {
    suspend operator fun invoke() {
        dataStore.clearSessionEmail()
    }
}
