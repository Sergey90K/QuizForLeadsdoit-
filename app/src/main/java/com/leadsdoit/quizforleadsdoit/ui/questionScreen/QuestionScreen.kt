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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.leadsdoit.quizforleadsdoit.R
import com.leadsdoit.quizforleadsdoit.network.Question

@Composable
fun ShowQuestionScreen(allQuestion: List<Question>, modifier: Modifier) {
    Box(contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.fillMaxSize(),
             verticalArrangement = Arrangement.Center,
            // verticalArrangement = Arrangement.SpaceBetween,
              horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ShowListOfQuestion(
                allQuestion = allQuestion,
                onCancelButtonClicked = {},
                onNextButtonClicked = {},
                modifier)
        }
    }
}

@Composable
fun ShowButton(
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
            // the button is enabled when the user makes a selection
            //enabled = selectedValue.isNotEmpty(),
            onClick = onNextButtonClicked
        ) {
            Text(stringResource(R.string.next))
        }
    }
}

@Composable
fun ShowListOfAnswer(question: String, options: List<String>, modifier: Modifier = Modifier) {
    var selectedValue by rememberSaveable { mutableStateOf("") }
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
                            selectedValue = item
                            //  onSelectionChanged(item)
                        }
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedValue == item,
                        onClick = {
                            selectedValue = item
                            // onSelectionChanged(item)
                        }
                    )
                    Text(item)
                }
            }
        }
    }
}

@Composable
fun ShowListOfQuestion(
    allQuestion: List<Question>,
    onCancelButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    modifier: Modifier
) {
    LazyColumn() {
        itemsIndexed(allQuestion) { index, item ->
            ShowListOfAnswer(question = item.questionText, options = item.choiceOfAnswer)
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
        }
        item {
            ShowButton(
                onCancelButtonClicked = { onCancelButtonClicked },
                onNextButtonClicked = { onNextButtonClicked },
                modifier = modifier
            )
        }
    }
}

@Preview
@Composable
fun SelectOptionPreview() {
    ShowQuestionScreen(
        listOf<Question>(
            Question(
                "First question",
                listOf<String>("Option 1", "Option 2", "Option 3", "Option 4")
            ),
            Question(
                "Second question",
                listOf<String>("Option 1", "Option 2", "Option 3", "Option 4")
            ),
            Question(
                "Third question",
                listOf<String>("Option 1", "Option 2", "Option 3", "Option 4")
            ),
            Question(
                "Forth question",
                listOf<String>("Option 1", "Option 2", "Option 3", "Option 4")
            )
        ), Modifier
    )
}

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