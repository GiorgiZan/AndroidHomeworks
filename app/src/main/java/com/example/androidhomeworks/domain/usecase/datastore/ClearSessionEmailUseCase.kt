package com.example.androidhomeworks.domain.usecase.datastore

import com.example.androidhomeworks.domain.repository.datastore.DataStoreRepository
import javax.inject.Inject

interface ClearSessionEmailUseCase{
    suspend operator fun invoke()
}

class ClearSessionEmailUseCaseImpl @Inject constructor(private val dataStore: DataStoreRepository):ClearSessionEmailUseCase {
    override suspend operator fun invoke() {
        dataStore.clearSessionEmail()
    }
}
