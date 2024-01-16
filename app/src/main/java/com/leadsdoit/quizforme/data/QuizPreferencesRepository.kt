package com.leadsdoit.quizforme.data

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class QuizPreferencesRepository(private val dataStore: DataStore<Preferences>) {
    private companion object {
        val IS_FONT_SETTINGS = booleanPreferencesKey("is_font_settings")
        const val TAG = "QuizPreferencesRepo"
    }

    suspend fun saveFontSettingsPreference(isFontSetting: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_FONT_SETTINGS] = isFontSetting
        }
    }

    val isFontSettings: Flow<Boolean> = dataStore.data
        .catch {
            if (it is IOException) {
                Log.e(TAG, "Error reading preferences.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[IS_FONT_SETTINGS] ?: true
        }
}