package com.leadsdoit.quizforme

import android.app.Application
import com.leadsdoit.quizforme.data.AppContainer
import com.leadsdoit.quizforme.data.DefaultAppContainer

class QuizApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}