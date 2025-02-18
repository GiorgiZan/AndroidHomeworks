package com.example.androidhomeworks.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class DataStoreRepository @Inject constructor(private val dataStore: DataStore<Preferences>) {

    companion object {
        val EMAIL = stringPreferencesKey("email")
        val REMEMBER_ME = booleanPreferencesKey("remember_me")
    }

    val email: Flow<String?> = dataStore.data.map { preferences ->
        preferences[EMAIL]
    }

    private val rememberMe: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[REMEMBER_ME] ?: false
    }


    suspend fun saveLoginInfo(email: String, rememberMe: Boolean) {
        dataStore.edit { preferences ->
            preferences[EMAIL] = email
            preferences[REMEMBER_ME] = rememberMe
        }
    }


    suspend fun clearLoginInfo() {
        dataStore.edit { preferences ->
            preferences.remove(EMAIL)
        }
    }

    suspend fun clearEmailIfNotRemembered() {
        val rememberMeValue = rememberMe.map { it }.first()
        if (!rememberMeValue) {
            clearLoginInfo()
        }
    }

}
