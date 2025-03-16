package com.example.androidhomeworks.domain.usecase.datastore


import com.example.androidhomeworks.data.preference_key.PreferenceKeys
import com.example.androidhomeworks.domain.repository.datastore.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetEmailUseCase{
    operator fun invoke(): Flow<String?>
}

class GetEmailUseCaseImpl @Inject constructor(private val dataStore: DataStoreRepository):GetEmailUseCase {
    override operator fun invoke(): Flow<String?> {
        return dataStore.getValue(PreferenceKeys.EMAIL, null)
    }
}
