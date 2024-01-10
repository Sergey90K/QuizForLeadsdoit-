package com.leadsdoit.quizforleadsdoit.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.leadsdoit.quizforleadsdoit.ui.questionScreen.QuestionDestination
import com.leadsdoit.quizforleadsdoit.ui.questionScreen.QuestionScreen
import com.leadsdoit.quizforleadsdoit.ui.resultsScreen.ResultDestination
import com.leadsdoit.quizforleadsdoit.ui.resultsScreen.ResultScreen
import com.leadsdoit.quizforleadsdoit.ui.startScreen.StartDestination
import com.leadsdoit.quizforleadsdoit.ui.startScreen.StartScreen

@Composable
fun QuizNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = StartDestination.route,
        modifier = modifier
    ) {
        composable(route = StartDestination.route) {
            StartScreen()
        }
        composable(route = QuestionDestination.route) {
            QuestionScreen()
        }
        composable(route = ResultDestination.route) {
            ResultScreen()
        }
    }
}

