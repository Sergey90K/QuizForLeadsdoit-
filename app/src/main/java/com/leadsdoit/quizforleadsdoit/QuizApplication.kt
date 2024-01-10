package com.leadsdoit.quizforleadsdoit

import android.app.Application
import com.leadsdoit.quizforleadsdoit.data.AppContainer
import com.leadsdoit.quizforleadsdoit.data.DefaultAppContainer

class QuizApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}