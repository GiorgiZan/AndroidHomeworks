package com.example.androidhomeworks.domain.usecase.datastore


import com.example.androidhomeworks.domain.repository.datastore.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEmailUseCase @Inject constructor(private val dataStore: DataStoreRepository) {
    operator fun invoke(): Flow<String?> {
        return dataStore.email
    }
}
