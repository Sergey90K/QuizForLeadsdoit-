package com.leadsdoit.quizforme.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.leadsdoit.quizforme.QuizApplication
import com.leadsdoit.quizforme.ui.questionScreen.QuestionViewModel
import com.leadsdoit.quizforme.ui.resultsScreen.ResultViewModel
import com.leadsdoit.quizforme.ui.startScreen.StartViewModel

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