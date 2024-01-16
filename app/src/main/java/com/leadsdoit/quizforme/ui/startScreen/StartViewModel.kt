package com.leadsdoit.quizforme.ui.startScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leadsdoit.quizforme.data.QuestionRepository
import com.leadsdoit.quizforme.data.QuizRepository
import com.leadsdoit.quizforme.network.Question
import com.leadsdoit.quizforme.ui.questionScreen.QuestionUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import okio.IOException

sealed interface QuizUiState {
    data class Success(val quizQuestion: List<Question>) : QuizUiState
    object Error : QuizUiState
    object Loading : QuizUiState
}

class StartViewModel(
    private val quizRepository: QuizRepository,
    private val questionRepository: QuestionRepository
) : ViewModel() {

    private val _quizUiState = MutableStateFlow<QuizUiState>(QuizUiState.Loading)
    var quizUiState: StateFlow<QuizUiState> = _quizUiState.asStateFlow()

    val questionUiState: StateFlow<QuestionUiState> =
        questionRepository.getAllStream().map { QuestionUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = QuestionUiState()
            )

    init {
        getQuizNetwork()
    }

    private fun getQuizNetwork() {
        viewModelScope.launch {
            _quizUiState.value = try {
                QuizUiState.Success(quizRepository.getQuestion())
            } catch (e: IOException) {
                QuizUiState.Error
            }
            writeToDatabase()
        }
    }

    fun trayLoadData() {
        _quizUiState.value = QuizUiState.Loading
        getQuizNetwork()
    }

    private suspend fun writeToDatabase() {
        when (_quizUiState.value) {
            is QuizUiState.Success -> {
                val data = (_quizUiState.value as QuizUiState.Success).quizQuestion
                questionRepository.deleteAll()
                for (item in data) {
                    questionRepository.insert(
                        com.leadsdoit.quizforme.data.Question(
                            0,
                            item.questionText,
                            item.choiceOfAnswer,
                            item.rightAnswer
                        )
                    )
                }
            }

            is QuizUiState.Error -> {}
            is QuizUiState.Loading -> {}
        }
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}