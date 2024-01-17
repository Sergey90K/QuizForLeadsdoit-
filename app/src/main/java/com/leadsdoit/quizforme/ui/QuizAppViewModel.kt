package com.leadsdoit.quizforme.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leadsdoit.quizforme.data.QuizPreferencesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class QuizAppViewModel(private val quizPreferencesRepository: QuizPreferencesRepository) :
    ViewModel() {
    val uiState: StateFlow<QizAppUiState> =
        quizPreferencesRepository.isFontSettings.map { isFontSettings ->
            QizAppUiState(isFontSettings)
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = QizAppUiState()
            )

    fun selectFont(isFontSettings: Boolean) {
        viewModelScope.launch {
            quizPreferencesRepository.saveFontSettingsPreference(isFontSettings)
        }
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class QizAppUiState(
    val isFontSettings: Boolean = true
)