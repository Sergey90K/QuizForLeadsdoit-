package com.leadsdoit.quizforleadsdoit.data

import com.leadsdoit.quizforleadsdoit.network.Question
import com.leadsdoit.quizforleadsdoit.network.QuizApiService

interface QuizRepository {
    suspend fun getQuestion(): List<Question>
}

class NetworkQuizRepository(private val quizApiService: QuizApiService) : QuizRepository{
    override suspend fun getQuestion(): List<Question> {
        return quizApiService.getQuestion()
    }

}