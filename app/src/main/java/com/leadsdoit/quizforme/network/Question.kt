package com.leadsdoit.quizforme.network

data class Question(
    val questionText: String,
    val choiceOfAnswer : List<String>,
    val rightAnswer: Int
)
