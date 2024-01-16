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
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = QizAppUiState()
            )

    fun selectLayout(isFontSettings: Boolean) {
        viewModelScope.launch {
            quizPreferencesRepository.saveFontSettingsPreference(isFontSettings)
        }
    }
}

data class QizAppUiState(
    val isFontSettings: Boolean = true
)