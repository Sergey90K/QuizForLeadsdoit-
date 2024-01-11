package com.leadsdoit.quizforleadsdoit.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.leadsdoit.quizforleadsdoit.ui.questionScreen.QuestionDestination
import com.leadsdoit.quizforleadsdoit.ui.questionScreen.QuestionScreen
import com.leadsdoit.quizforleadsdoit.ui.resultsScreen.ResultDestination
import com.leadsdoit.quizforleadsdoit.ui.resultsScreen.ResultScreen
import com.leadsdoit.quizforleadsdoit.ui.startScreen.StartDestination
import com.leadsdoit.quizforleadsdoit.ui.startScreen.StartScreen

@Composable
fun QuizNavHost(
    transformData: Boolean,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = StartDestination.route,
        modifier = modifier
    ) {
        composable(route = StartDestination.route) {
            StartScreen(
                transformData = transformData,
                navigateToQuestionPage = { navController.navigate(QuestionDestination.route) })
        }
        composable(route = QuestionDestination.route) {
            QuestionScreen(
                transformData = transformData,
                onCancelButtonClicked = { navController.navigateUp() },
                navigateToResultPage = { navController.navigate("${ResultDestination.route}/$it") })
        }
        composable(
            route = ResultDestination.routeWithArgs,
            arguments = listOf(navArgument(ResultDestination.sourceArgs) {
                type = NavType.IntType
            })
        ) {
            ResultScreen(transformData = transformData, onRepeatButtonClicked = {
                navController.popBackStack(
                    StartDestination.route,
                    inclusive = false
                )
            })
        }
    }
}

