package com.leadsdoit.quizforleadsdoit.network

import retrofit2.http.GET

interface QuizApiService {
    @GET("67e3b205-1886-4d6b-9453-f01d2666e575")
    suspend fun getQuestion(): List<Question>
}