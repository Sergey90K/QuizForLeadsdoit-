package com.leadsdoit.quizforme

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.leadsdoit.quizforme.data.AppContainer
import com.leadsdoit.quizforme.data.DefaultAppContainer
import com.leadsdoit.quizforme.data.QuizPreferencesRepository

private const val FONT_PREFERENCE_NAME = "font_preferences"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = FONT_PREFERENCE_NAME
)

class QuizApplication : Application() {
    lateinit var container: AppContainer
    lateinit var quizPreferencesRepository: QuizPreferencesRepository
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
        quizPreferencesRepository = QuizPreferencesRepository(dataStore)

    }
}