package com.example.androidhomeworks.domain.usecase.datastore

import com.example.androidhomeworks.data.preference_key.PreferenceKeys
import com.example.androidhomeworks.domain.repository.datastore.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetRememberMeUseCase {
    operator fun invoke(): Flow<Boolean?>
}

class GetRememberMeUseCaseImpl @Inject constructor(private val dataStore: DataStoreRepository) :
    GetRememberMeUseCase {
    override operator fun invoke(): Flow<Boolean?> {
        return dataStore.getValue(PreferenceKeys.REMEMBER_ME, false)
    }
}
