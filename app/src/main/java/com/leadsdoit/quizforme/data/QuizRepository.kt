package com.leadsdoit.quizforme.data

import com.leadsdoit.quizforme.network.Question
import com.leadsdoit.quizforme.network.QuizApiService

interface QuizRepository {
    suspend fun getQuestion(): List<Question>
}

class NetworkQuizRepository(private val quizApiService: QuizApiService) : QuizRepository {
    override suspend fun getQuestion(): List<Question> {
        return quizApiService.getQuestion()
    }

}