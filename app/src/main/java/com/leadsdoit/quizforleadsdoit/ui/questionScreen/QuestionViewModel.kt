package com.leadsdoit.quizforleadsdoit.ui.questionScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leadsdoit.quizforleadsdoit.data.Question
import com.leadsdoit.quizforleadsdoit.data.QuestionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class QuestionViewModel(private val questionRepository: QuestionRepository) : ViewModel() {
    val questionUiState: StateFlow<QuestionUiState> =
        questionRepository.getAllStream().map { QuestionUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = QuestionUiState()
            )

    private val _allowShowQuestion: MutableStateFlow<Array<Boolean>> =
        MutableStateFlow(createInitialAllow())
    val allowShowQuestionUiState: StateFlow<Array<Boolean>> = _allowShowQuestion.asStateFlow()
    private val _selectedValue: MutableStateFlow<String> = MutableStateFlow("")
    val selectedValueUiState: StateFlow<String> = _selectedValue.asStateFlow()
    private val _showCheckButton = MutableStateFlow(true)
    val showCheckButtonUiState: StateFlow<Boolean> = _showCheckButton.asStateFlow()
    private val counter = MutableStateFlow(0)
    private val _scoreUiState = MutableStateFlow(0)
    val scoreUiState = _scoreUiState.asStateFlow()
    private var trueAnswer = 0

    fun checkTheAnswer() {
        if (counter.value < questionUiState.value.question.size) {
            if (questionUiState.value.question[counter.value].answer[questionUiState.value.question[counter.value].rightAnswer] ==
                selectedValueUiState.value
            ) {
                trueAnswer++
                _scoreUiState.value = 100 / questionUiState.value.question.size * trueAnswer
            } else {

            }
        }
        _allowShowQuestion.value[counter.value] = false
        if (counter.value < _allowShowQuestion.value.size - 1) {
            counter.value++
            _allowShowQuestion.value[counter.value] = true
            _selectedValue.value = ""
        } else {
            _showCheckButton.value = false
        }
    }

    fun selectValue(value: String) {
        _selectedValue.value = value
    }

    private fun createInitialAllow(): Array<Boolean> {
        var data: Array<Boolean>
        if (questionUiState.value.question.isEmpty()) {
            data = Array(5) { false }
        } else {
            data = Array(questionUiState.value.question.size) { false }
        }
        data[0] = true
        return data
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class QuestionUiState(val question: List<Question> = listOf())