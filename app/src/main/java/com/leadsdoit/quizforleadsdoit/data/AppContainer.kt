package com.leadsdoit.quizforleadsdoit.data

import com.leadsdoit.quizforleadsdoit.network.QuizApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val quizRepository: QuizRepository
}

class DefaultAppContainer : AppContainer{
    private val baseUrl = "https://mocki.io/v1/"
    private val retrofit = Retrofit.Builder()
       // .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val retrofitService: QuizApiService by lazy {
        retrofit.create(QuizApiService::class.java)
    }
    override val quizRepository: QuizRepository by lazy {
        NetworkQuizRepository(retrofitService)
    }

}