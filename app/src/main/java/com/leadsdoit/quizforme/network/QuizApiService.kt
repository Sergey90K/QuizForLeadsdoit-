package com.leadsdoit.quizforme.network

import retrofit2.http.GET

interface QuizApiService {
    @GET("6e09404b-65bb-4df3-b5d4-6bf05c1d88b5")
    suspend fun getQuestion(): List<Question>
}