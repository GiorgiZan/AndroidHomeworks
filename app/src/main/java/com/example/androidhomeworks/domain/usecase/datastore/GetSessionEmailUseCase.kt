package com.example.androidhomeworks.domain.usecase.datastore

import com.example.androidhomeworks.domain.repository.datastore.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetSessionEmailUseCase {
    operator fun invoke(): Flow<String?>
}

class GetSessionEmailUseCaseImpl @Inject constructor(private val dataStore: DataStoreRepository) :
    GetSessionEmailUseCase {
    override operator fun invoke(): Flow<String?> {
        return dataStore.sessionEmail
    }
}
