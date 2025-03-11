package com.example.androidhomeworks.data.preference_key

import androidx.datastore.preferences.core.stringPreferencesKey

object PreferenceKeys {
    val EMAIL = stringPreferencesKey("email")
    val SESSION_EMAIL = stringPreferencesKey("session_email")
}