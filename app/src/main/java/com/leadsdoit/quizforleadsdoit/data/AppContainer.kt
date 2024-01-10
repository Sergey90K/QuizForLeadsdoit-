package com.leadsdoit.quizforleadsdoit.data

import android.content.Context
import com.leadsdoit.quizforleadsdoit.network.QuizApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val quizRepository: QuizRepository
    val questionRepository: QuestionRepository
}

class DefaultAppContainer(private val context: Context) : AppContainer {
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
    override val questionRepository: QuestionRepository by lazy {
        OfflineQuizRepository(QuizDatabase.getDatabase(context).questionDao())
    }

}