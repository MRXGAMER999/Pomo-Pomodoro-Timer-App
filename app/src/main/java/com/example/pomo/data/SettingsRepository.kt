package com.example.pomo.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "timer_settings")

class SettingsRepository(private val context: Context) {
    
    private object PreferencesKeys {
        val FOCUS_TIME = intPreferencesKey("focus_time_minutes")
        val SHORT_BREAK_TIME = intPreferencesKey("short_break_time_minutes")
        val LONG_BREAK_TIME = intPreferencesKey("long_break_time_minutes")
        val DARK_MODE = androidx.datastore.preferences.core.booleanPreferencesKey("dark_mode")
    }
    
    fun getTimerSettings(): Flow<TimerSettings> {
        return context.dataStore.data.map { preferences ->
            TimerSettings(
                focusTimeMinutes = preferences[PreferencesKeys.FOCUS_TIME] ?: 25,
                shortBreakTimeMinutes = preferences[PreferencesKeys.SHORT_BREAK_TIME] ?: 5,
                longBreakTimeMinutes = preferences[PreferencesKeys.LONG_BREAK_TIME] ?: 15,
                darkMode = preferences[PreferencesKeys.DARK_MODE] ?: false
            )
        }
    }
    
    suspend fun updateFocusTime(minutes: Int) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.FOCUS_TIME] = minutes
        }
    }
    
    suspend fun updateShortBreakTime(minutes: Int) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.SHORT_BREAK_TIME] = minutes
        }
    }
    
    suspend fun updateLongBreakTime(minutes: Int) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.LONG_BREAK_TIME] = minutes
        }
    }
    
    suspend fun updateDarkMode(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.DARK_MODE] = enabled
        }
    }
    
    suspend fun initializeDefaults() {
        try {
            // Quick initialization - just ensure DataStore is ready
            // DataStore handles defaults in getTimerSettings() method already
            context.dataStore.data.first()
        } catch (e: Exception) {
            // If there's an error reading, initialize with defaults
            context.dataStore.edit { preferences ->
                preferences[PreferencesKeys.FOCUS_TIME] = 25
                preferences[PreferencesKeys.SHORT_BREAK_TIME] = 5
                preferences[PreferencesKeys.LONG_BREAK_TIME] = 15
                preferences[PreferencesKeys.DARK_MODE] = false
            }
        }
    }
}

