package com.leadsdoit.quizforleadsdoit.ui.questionScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.leadsdoit.quizforleadsdoit.R
import com.leadsdoit.quizforleadsdoit.ui.AppViewModelProvider
import com.leadsdoit.quizforleadsdoit.ui.navigation.NavigationDestination

object QuestionDestination : NavigationDestination {
    override val route = "question"
    override val titleRes = R.string.questions_page
}

@Composable
fun QuestionScreen(
    navigateToResultPage: (Int) -> Unit,
    viewModel: QuestionViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = AppViewModelProvider.Factory
    )
) {
    val questionUiState by viewModel.questionUiState.collectAsStateWithLifecycle()
    val selectedValueUiState by viewModel.selectedValueUiState.collectAsStateWithLifecycle()
    val allowShowQuestionUiState by viewModel.allowShowQuestionUiState.collectAsStateWithLifecycle()
    val showCheckButtonUiState by viewModel.showCheckButtonUiState.collectAsStateWithLifecycle()
    val scoreUiState by viewModel.scoreUiState.collectAsStateWithLifecycle()

    ShowQuestionScreen(
        scoreUiState = scoreUiState,
        navigateToResultPage = navigateToResultPage,
        showCheckButton = showCheckButtonUiState,
        allowShowQuestion = allowShowQuestionUiState,
        selectValue = viewModel::selectValue,
        selectedValue = selectedValueUiState,
        allQuestion = questionUiState.question,
        onNextButtonClicked = viewModel::checkTheAnswer,
        modifier = Modifier
    )
}

@Composable
fun ShowQuestionScreen(
    scoreUiState: Int,
    navigateToResultPage: (Int) -> Unit,
    showCheckButton: Boolean,
    allowShowQuestion: Array<Boolean>,
    selectValue: (String) -> Unit,
    selectedValue: String,
    allQuestion: List<com.leadsdoit.quizforleadsdoit.data.Question>,
    onNextButtonClicked: () -> Unit,
    modifier: Modifier
) {
    Box(contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            // verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ShowListOfQuestion(
                scoreUiState =scoreUiState,
                navigateToResultPage = navigateToResultPage,
                showCheckButton = showCheckButton,
                allowShowQuestion = allowShowQuestion,
                selectValue = selectValue,
                selectedValue = selectedValue,
                allQuestion = allQuestion,
                onCancelButtonClicked = {},
                onNextButtonClicked = onNextButtonClicked,
                modifier
            )
        }
    }
}

@Composable
fun ShowListOfQuestion(
    scoreUiState : Int,
    navigateToResultPage: (Int) -> Unit,
    showCheckButton: Boolean,
    allowShowQuestion: Array<Boolean>,
    selectValue: (String) -> Unit,
    selectedValue: String,
    allQuestion: List<com.leadsdoit.quizforleadsdoit.data.Question>,
    onCancelButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    modifier: Modifier
) {
    LazyColumn() {
        itemsIndexed(allQuestion) { index, item ->
            ShowListOfAnswer(
                allowShowQuestion = allowShowQuestion[index],
                selectValue = selectValue,
                selectedValue = selectedValue,
                question = item.question,
                options = item.answer
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
        }
        item {
            ShowButton(
                scoreUiState = scoreUiState,
                navigateToResultPage = navigateToResultPage,
                showCheckButton = showCheckButton,
                selectedValue = selectedValue,
                onCancelButtonClicked = onCancelButtonClicked,
                onNextButtonClicked = onNextButtonClicked,
                modifier = modifier
            )
        }
    }
}

@Composable
fun ShowListOfAnswer(
    allowShowQuestion: Boolean,
    selectValue: (String) -> Unit,
    selectedValue: String,
    question: String,
    options: List<String>,
    modifier: Modifier = Modifier
) {
    if (allowShowQuestion) {
        Card(modifier = modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))) {
                Text(
                    text = question,
                    style = MaterialTheme.typography.bodyMedium,
                )
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
                options.forEach { item ->
                    Row(
                        modifier = Modifier.selectable(
                            selected = selectedValue == item,
                            onClick = {
                                selectValue(item)
                                //  onSelectionChanged(item)
                            }
                        ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = selectedValue == item,
                            onClick = {
                                selectValue(item)
                                // onSelectionChanged(item)
                            }
                        )
                        Text(item)
                    }
                }
            }
        }
    }
}

@Composable
fun ShowButton(
    scoreUiState : Int,
    navigateToResultPage: (Int) -> Unit,
    showCheckButton: Boolean,
    selectedValue: String,
    onCancelButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    modifier: Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            //.weight(1f, false)
            .padding(dimensionResource(R.dimen.padding_medium)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        verticalAlignment = Alignment.Bottom
    ) {
        OutlinedButton(modifier = Modifier.weight(1f), onClick = onCancelButtonClicked) {
            Text(stringResource(R.string.cancel))
        }
        Button(
            modifier = Modifier.weight(1f),
            enabled = selectedValue.isNotEmpty(),
            onClick = {
                if (showCheckButton) {
                    onNextButtonClicked()
                } else {
                    navigateToResultPage(scoreUiState)
                }
            }
        ) {
            if (showCheckButton) {
                Text(stringResource(R.string.next))
            } else {
                Text(stringResource(R.string.check))
            }

        }
    }
}

//@Preview
//@Composable
//fun SelectOptionPreview() {
//    ShowQuestionScreen(
//        listOf<Question>(
//            Question(
//                "First question",
//                listOf<String>("Option 1", "Option 2", "Option 3", "Option 4"),
//                0
//            ),
//            Question(
//                "Second question",
//                listOf<String>("Option 1", "Option 2", "Option 3", "Option 4"),
//                4
//            ),
//            Question(
//                "Third question",
//                listOf<String>("Option 1", "Option 2", "Option 3", "Option 4"),
//                4
//            ),
//            Question(
//                "Forth question",
//                listOf<String>("Option 1", "Option 2", "Option 3", "Option 4"),
//                1
//            )
//        ), Modifier
//    )
//}

//@Preview
//@Composable
//fun SelectOptionPreview2() {
//    ShowListOfQuestion(
//        listOf<Question>(
//            Question(
//                "First question",
//                listOf<String>("Option 1", "Option 2", "Option 3", "Option 4")
//            ),
//            Question(
//                "Second question",
//                listOf<String>("Option 1", "Option 2", "Option 3", "Option 4")
//            ),
//            Question(
//                "Third question",
//                listOf<String>("Option 1", "Option 2", "Option 3", "Option 4")
//            ),
//            Question(
//                "Forth question",
//                listOf<String>("Option 1", "Option 2", "Option 3", "Option 4")
//            )
//        )
//    )
//}