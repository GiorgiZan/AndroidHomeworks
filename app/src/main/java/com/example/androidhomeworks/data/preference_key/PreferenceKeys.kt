package com.example.androidhomeworks.data.preference_key

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferenceKeys {
    val EMAIL = stringPreferencesKey("email")
    val REMEMBER_ME = booleanPreferencesKey("remember_me")
}