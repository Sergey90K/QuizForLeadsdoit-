package com.leadsdoit.quizforleadsdoit.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class Question(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val question: String,
    val answer: List<String>,
    val rightAnswer: Int
)
