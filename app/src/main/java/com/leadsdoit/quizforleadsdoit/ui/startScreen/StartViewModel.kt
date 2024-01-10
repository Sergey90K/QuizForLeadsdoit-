package com.leadsdoit.quizforleadsdoit.ui.startScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leadsdoit.quizforleadsdoit.data.QuestionRepository
import com.leadsdoit.quizforleadsdoit.data.QuizRepository
import com.leadsdoit.quizforleadsdoit.network.Question
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    private suspend fun writeToDatabase() {
        when (_quizUiState.value) {
            is QuizUiState.Success -> {
                val data = (_quizUiState.value as QuizUiState.Success).quizQuestion
                questionRepository.deleteAll()
                for (item in data) {
                    questionRepository.insert(
                        com.leadsdoit.quizforleadsdoit.data.Question(
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

//    companion object {
//        val Factory: ViewModelProvider.Factory = viewModelFactory {
//            initializer {
//                val application =
//                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as QuizApplication)
//                val quizRepository= application.container.quizRepository
//                StartViewModel(quizRepository = quizRepository)
//
//            }
//        }
//    }
}