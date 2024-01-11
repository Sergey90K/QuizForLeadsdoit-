package com.leadsdoit.quizforleadsdoit.ui.resultsScreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leadsdoit.quizforleadsdoit.data.Question
import com.leadsdoit.quizforleadsdoit.data.QuestionRepository
import com.leadsdoit.quizforleadsdoit.network.Answer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ResultViewModel(
    savedStateHandle: SavedStateHandle,
    private val questionRepository: QuestionRepository
) : ViewModel() {

    private val sourceArgs: Int = checkNotNull(savedStateHandle[ResultDestination.sourceArgs])
    val questionUiState: StateFlow<AnswerUiState> = questionRepository.getAllStream().map {
        fromQuestionToAnswer(it)
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = AnswerUiState()
        )
    private val _sourceUiState = MutableStateFlow(sourceArgs)
    val sourceUiState = _sourceUiState.asStateFlow()

    private fun fromQuestionToAnswer(question: List<Question>): AnswerUiState {
        if (question.isEmpty()) {
            return AnswerUiState()
        } else {
            var counter = 0
            var outputData: Array<Answer> = arrayOf()
            for (item in question) {
                outputData += Answer(item.question, item.answer[item.rightAnswer])
                counter++
            }
            return AnswerUiState(outputData.toList())
        }
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class AnswerUiState(val answer: List<Answer> = listOf())