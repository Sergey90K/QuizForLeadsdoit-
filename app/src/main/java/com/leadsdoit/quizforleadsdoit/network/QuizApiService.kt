package com.leadsdoit.quizforleadsdoit.network

import retrofit2.http.GET

interface QuizApiService {
    @GET("88568eaa-377e-46c9-a151-fd7b0bd257ed")
    suspend fun getQuestion(): List<Question>
}