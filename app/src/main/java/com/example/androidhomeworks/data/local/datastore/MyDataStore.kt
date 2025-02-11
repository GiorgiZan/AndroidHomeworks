package com.example.androidhomeworks.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "AppPreferences")

class MyDataStore(context: Context) {

    private val dataStore = context.dataStore

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
