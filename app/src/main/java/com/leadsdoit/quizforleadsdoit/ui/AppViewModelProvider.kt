package com.leadsdoit.quizforleadsdoit.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.leadsdoit.quizforleadsdoit.QuizApplication
import com.leadsdoit.quizforleadsdoit.ui.startScreen.StartViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            StartViewModel(quizApplication().container.quizRepository,
                quizApplication().container.questionRepository)
        }
    }
}

fun CreationExtras.quizApplication(): QuizApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as QuizApplication)