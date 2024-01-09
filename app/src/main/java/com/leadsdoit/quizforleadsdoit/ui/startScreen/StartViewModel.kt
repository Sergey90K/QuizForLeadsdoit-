package com.leadsdoit.quizforleadsdoit.ui.startScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.leadsdoit.quizforleadsdoit.QuizApplication
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

class StartViewModel(private val quizRepository: QuizRepository) : ViewModel() {

    private val _quizUiState = MutableStateFlow<QuizUiState>(QuizUiState.Loading)
    var quizUiState:StateFlow<QuizUiState> = _quizUiState.asStateFlow()

    init {
        getQuizNetwork()
    }

    private fun getQuizNetwork() {
        viewModelScope.launch {
            _quizUiState.value = try {
                QuizUiState.Success(quizRepository.getQuestion())
            }catch (e: IOException){
                QuizUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as QuizApplication)
                val quizRepository= application.container.quizRepository
                StartViewModel(quizRepository = quizRepository)

            }
        }
    }
}