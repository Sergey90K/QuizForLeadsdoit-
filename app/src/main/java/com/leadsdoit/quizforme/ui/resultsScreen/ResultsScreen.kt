package com.leadsdoit.quizforme.ui.resultsScreen

import android.app.Activity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.leadsdoit.quizforme.R
import com.leadsdoit.quizforme.network.Answer
import com.leadsdoit.quizforme.ui.AppViewModelProvider
import com.leadsdoit.quizforme.ui.navigation.NavigationDestination

object ResultDestination : NavigationDestination {
    override val route = "result"
    override val titleRes = R.string.results_page
    const val sourceArgs = "sourceArgs"
    val routeWithArgs = "$route/{$sourceArgs}"
}

@Composable
fun ResultScreen(
    transformData: Boolean,
    onRepeatButtonClicked: () -> Unit,
    viewModel: ResultViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = AppViewModelProvider.Factory)
) {
    val resultUiState by viewModel.questionUiState.collectAsStateWithLifecycle()
    val sourceUiState by viewModel.sourceUiState.collectAsStateWithLifecycle()
    val activity = (LocalContext.current as? Activity)
    ShowResultScreen(
        transformData = transformData,
        onRepeatButtonClicked = onRepeatButtonClicked,
        onExitButtonClicked = { activity?.finish() },
        answer = resultUiState.answer,
        result = sourceUiState,
        modifier = Modifier
    )
}

@Composable
fun ShowResultScreen(
    transformData: Boolean,
    onRepeatButtonClicked: () -> Unit,
    onExitButtonClicked: () -> Unit,
    answer: List<Answer>,
    result: Int,
    modifier: Modifier
) {
    Box(contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F, true),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                ShowCongratulation(
                    transformData = transformData,
                    result = result,
                    modifier = modifier
                )
                ShowTrueAnswer(transformData = transformData, answer = answer, modifier = modifier)
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                ShowButton(
                    onExitButtonClicked = onExitButtonClicked,
                    onRepeatButtonClicked = onRepeatButtonClicked,
                    modifier
                )
            }
        }
    }
}

@Composable
fun ShowButton(
    onExitButtonClicked: () -> Unit,
    onRepeatButtonClicked: () -> Unit,
    modifier: Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.padding_medium)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        verticalAlignment = Alignment.Bottom
    ) {
        OutlinedButton(
            modifier = Modifier.weight(1f),
            onClick = onExitButtonClicked
        ) {
            Text(stringResource(R.string.exit_the_program))
        }
        Button(
            modifier = Modifier.weight(1f),
            onClick = onRepeatButtonClicked
        ) {
            Text(stringResource(R.string.repeat))
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ShowTrueAnswer(transformData: Boolean, answer: List<Answer>, modifier: Modifier) {
    val visibleState = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }
    AnimatedVisibility(
        visibleState = visibleState,
        enter = fadeIn(
            animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy)
        ),
        exit = fadeOut(),
        modifier = modifier
    ) {
        Column(modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))) {
            answer.forEach { item ->
                Card(modifier = modifier
                    .fillMaxWidth()
                    .animateEnterExit(enter = slideInVertically(
                        animationSpec = spring(
                            stiffness = Spring.StiffnessVeryLow,
                            dampingRatio = Spring.DampingRatioLowBouncy
                        ), initialOffsetY = { it }
                    )
                    )
                ) {
                    Column(modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))) {
                        Text(
                            text = item.question,
                            style = if (transformData) {
                                MaterialTheme.typography.bodyMedium
                            } else {
                                MaterialTheme.typography.labelSmall
                            },
                        )
                        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
                        Text(
                            text = item.answer,
                            style = if (transformData) {
                                MaterialTheme.typography.bodyMedium
                            } else {
                                MaterialTheme.typography.labelSmall
                            },
                        )
                    }
                }
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
            }
        }
    }
}

@Composable
fun ShowCongratulation(transformData: Boolean, result: Int, modifier: Modifier) {
    val successfulLevel = 50
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (result > successfulLevel) {
            Text(
                text = stringResource(R.string.congratulations_you_passed_the_quiz),
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)),
                textAlign = TextAlign.Center,
                style = if (transformData) {
                    MaterialTheme.typography.labelMedium
                } else {
                    MaterialTheme.typography.headlineMedium
                },
            )
        } else {
            Text(
                text = stringResource(R.string.sorry_but_you_re_not_level_enough),
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)),
                textAlign = TextAlign.Center,
                style = if (transformData) {
                    MaterialTheme.typography.labelMedium
                } else {
                    MaterialTheme.typography.headlineMedium
                },
            )
        }
        Text(
            text = stringResource(R.string.your_result, result),
            textAlign = TextAlign.Center,
            style = if (transformData) {
                MaterialTheme.typography.bodyLarge
            } else {
                MaterialTheme.typography.labelLarge
            },
            modifier = Modifier.padding(
                bottom = dimensionResource(R.dimen.padding_medium),
                start = dimensionResource(R.dimen.padding_medium),
                end = dimensionResource(R.dimen.padding_medium)
            )
        )
    }
}