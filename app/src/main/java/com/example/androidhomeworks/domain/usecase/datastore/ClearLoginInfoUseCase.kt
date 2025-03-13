package com.example.androidhomeworks.domain.usecase.datastore

import com.example.androidhomeworks.domain.repository.datastore.DataStoreRepository
import javax.inject.Inject

interface ClearLoginInfoUseCase{
    suspend operator fun invoke()
}

class ClearLoginInfoUseCaseImpl @Inject constructor(private val dataStore: DataStoreRepository) :
    ClearLoginInfoUseCase {
    override suspend operator fun invoke() {
        dataStore.clearLoginInfo()
    }
}