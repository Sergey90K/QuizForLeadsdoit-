package com.leadsdoit.quizforleadsdoit.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.leadsdoit.quizforleadsdoit.QuizApplication
import com.leadsdoit.quizforleadsdoit.ui.questionScreen.QuestionViewModel
import com.leadsdoit.quizforleadsdoit.ui.resultsScreen.ResultViewModel
import com.leadsdoit.quizforleadsdoit.ui.startScreen.StartViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            StartViewModel(
                quizApplication().container.quizRepository,
                quizApplication().container.questionRepository
            )
        }
        initializer {
            QuestionViewModel(quizApplication().container.questionRepository)
        }
        initializer {
            ResultViewModel(
                this.createSavedStateHandle(),
                quizApplication().container.questionRepository
            )
        }
    }
}

fun CreationExtras.quizApplication(): QuizApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as QuizApplication)