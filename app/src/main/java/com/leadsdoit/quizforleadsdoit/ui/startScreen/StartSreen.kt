package com.leadsdoit.quizforleadsdoit.ui.startScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.leadsdoit.quizforleadsdoit.R
import com.leadsdoit.quizforleadsdoit.ui.AppViewModelProvider
import com.leadsdoit.quizforleadsdoit.ui.navigation.NavigationDestination

object StartDestination : NavigationDestination {
    override val route = "start"
    override val titleRes = R.string.app_name
}

@Composable
fun StartScreen(
    transformData: Boolean,
    navigateToQuestionPage: () -> Unit,
    viewModel: StartViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState by viewModel.quizUiState.collectAsStateWithLifecycle()
    val questionUiState by viewModel.questionUiState.collectAsStateWithLifecycle()

    when (uiState) {
        is QuizUiState.Success -> {
            SuccessfulScreen(
                transformData = transformData,
                startAction = navigateToQuestionPage,
                modifier = Modifier
            )
        }

        is QuizUiState.Loading -> {
            LoadingScreen(transformData = transformData)
        }

        is QuizUiState.Error -> {
            ErrorScreen(
                transformData = transformData,
                navigateToQuestionPage = navigateToQuestionPage,
                permissionOfflineUiState = questionUiState.question.isNotEmpty(),
                retryAction = viewModel::trayLoadData
            )
        }
    }
}

@Composable
fun SuccessfulScreen(transformData: Boolean, startAction: () -> Unit, modifier: Modifier) {
    val imageModifier = Modifier
        .size(dimensionResource(R.dimen.image_size))
    Box(contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F, true),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.media_design_hydropro_v2_tower_128),
                    contentDescription = stringResource(R.string.computer_icon),
                    contentScale = ContentScale.Fit,
                    modifier = imageModifier
                )
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
                Text(
                    text = stringResource(R.string.quiz_on_knowledge_of_computer_components),
                    style = if (transformData) {
                        MaterialTheme.typography.displayLarge
                    } else {
                        MaterialTheme.typography.displayMedium
                    },
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)),
                    textAlign = TextAlign.Center,
                )
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Button(onClick = startAction) {
                    Text(stringResource(R.string.to_begin))
                }
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
            }
        }
    }
}

@Composable
fun LoadingScreen(transformData: Boolean, modifier: Modifier = Modifier) {
    Box(contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.loading),
                style = if (transformData) {
                    MaterialTheme.typography.displayLarge
                } else {
                    MaterialTheme.typography.displayMedium
                },
                modifier = Modifier.padding(all = dimensionResource(R.dimen.padding_small))
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
            CircularProgressIndicator(
                modifier = Modifier.width(150.dp),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        }
    }
}

@Composable
fun ErrorScreen(
    transformData: Boolean,
    navigateToQuestionPage: () -> Unit,
    permissionOfflineUiState: Boolean,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1F, true)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_connection_error),
                    contentDescription = stringResource(R.string.connection_error)
                )
                Text(
                    text = stringResource(R.string.failed_to_load_data_from_server),
                    style = if (transformData) {
                        MaterialTheme.typography.labelLarge
                    } else {
                        MaterialTheme.typography.displaySmall
                    },
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
                )
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                ShowButton(
                    navigateToQuestionPage = navigateToQuestionPage,
                    retryAction = retryAction,
                    permissionOfflineUiState = permissionOfflineUiState,
                    modifier = modifier
                )
            }
        }
    }
}

@Composable
fun ShowButton(
    navigateToQuestionPage: () -> Unit,
    retryAction: () -> Unit,
    permissionOfflineUiState: Boolean,
    modifier: Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.padding_medium)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        verticalAlignment = Alignment.Bottom
    ) {
        Button(
            modifier = Modifier.weight(1f),
            onClick = retryAction
        ) {
            Text(stringResource(R.string.retry))
        }
        Button(
            modifier = Modifier.weight(1f),
            onClick = navigateToQuestionPage,
            enabled = permissionOfflineUiState
        ) {
            Text(stringResource(R.string.offline))
        }
    }
}