package com.example.androidhomeworks.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.androidhomeworks.data.preference_key.PreferenceKeys
import com.example.androidhomeworks.domain.repository.datastore.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class DataStoreRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : DataStoreRepository {

    override val email: Flow<String?> = dataStore.data.map { preferences ->
        preferences[PreferenceKeys.EMAIL]
    }

    override val sessionEmail: Flow<String?> = dataStore.data.map { preferences ->
        preferences[PreferenceKeys.SESSION_EMAIL]
    }

    override suspend fun saveLoginInfo(email: String) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.EMAIL] = email
        }
    }

    override suspend fun clearLoginInfo() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    override suspend fun saveSessionEmail(email: String) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.SESSION_EMAIL] = email
        }
    }

    override suspend fun clearSessionEmail() {
        dataStore.edit { preferences ->
            preferences.remove(PreferenceKeys.SESSION_EMAIL)
        }
    }
}