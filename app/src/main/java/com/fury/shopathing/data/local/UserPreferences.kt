package com.fury.shopathing.data.local

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Extension property to create the DataStore
private val Context.dataStore by preferencesDataStore("user_settings")

class UserPreferences(private val context: Context) {

    // Define the Key
    private val IS_DARK_MODE = booleanPreferencesKey("is_dark_mode")

    // Get the Data (Flow updates automatically when data changes)
    val isDarkMode: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[IS_DARK_MODE] ?: false // Default to Light Mode (false)
        }

    // Save the Data
    suspend fun toggleDarkMode(enable: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_DARK_MODE] = enable
        }
    }
}