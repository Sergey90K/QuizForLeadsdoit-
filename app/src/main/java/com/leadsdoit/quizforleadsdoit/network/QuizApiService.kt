package com.leadsdoit.quizforleadsdoit.network

import retrofit2.http.GET

interface QuizApiService {
    @GET("21b0c486-5070-42ad-8c9b-9d22477e1293")
    suspend fun getQuestion(): List<Question>
}