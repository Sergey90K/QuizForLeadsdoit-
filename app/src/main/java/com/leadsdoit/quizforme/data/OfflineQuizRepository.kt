package com.leadsdoit.quizforme.data

import kotlinx.coroutines.flow.Flow

interface QuestionRepository {
    fun getAllStream(): Flow<List<Question>>
    suspend fun insert(question: Question)
    suspend fun deleteAll()
}

class OfflineQuizRepository(private val questionDao: QuestionDao) : QuestionRepository {
    override fun getAllStream(): Flow<List<Question>> = questionDao.getAllQuestion()
    override suspend fun insert(question: Question) = questionDao.insert(question)
    override suspend fun deleteAll() = questionDao.deleteAll()
}