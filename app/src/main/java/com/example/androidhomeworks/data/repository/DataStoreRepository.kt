package com.example.androidhomeworks.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class DataStoreRepository @Inject constructor(private val dataStore : DataStore<Preferences>) {



    companion object {
        val EMAIL = stringPreferencesKey("email")
        val SESSION_EMAIL = stringPreferencesKey("session_email")
    }

    val email: Flow<String?> = dataStore.data.map { preferences ->
        preferences[EMAIL]
    }
    val sessionEmail: Flow<String?> = dataStore.data.map { preferences ->
        preferences[SESSION_EMAIL]
    }

    suspend fun saveLoginInfo(email: String) {
        dataStore.edit { preferences ->
            preferences[EMAIL] = email
        }
    }


    suspend fun clearLoginInfo() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    suspend fun saveSessionEmail(email: String) {
        dataStore.edit { preferences ->
            preferences[SESSION_EMAIL] = email
        }
    }

    suspend fun clearSessionEmail() {
        dataStore.edit { preferences ->
            preferences.remove(SESSION_EMAIL)
        }
    }
}
