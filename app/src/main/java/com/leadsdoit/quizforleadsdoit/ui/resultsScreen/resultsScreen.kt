package com.leadsdoit.quizforleadsdoit.ui.resultsScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.leadsdoit.quizforleadsdoit.R
import com.leadsdoit.quizforleadsdoit.network.Answer

@Composable
fun ShowResultScreen(answer: List<Answer>, result: Int, modifier: Modifier) {
    Box(contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ShowCongratulation(result = result, modifier = modifier)
            ShowTrueAnswer(answer = answer, modifier = modifier)
            ShowButton(onExitButtonClicked = {}, onRepeatButtonClicked = {}, modifier)
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
            //.weight(1f, false)
            .padding(dimensionResource(R.dimen.padding_medium)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        verticalAlignment = Alignment.Bottom
    ) {
        OutlinedButton(modifier = Modifier.weight(1f), onClick = onExitButtonClicked) {
            Text(stringResource(R.string.exit_the_program))
        }
        Button(
            modifier = Modifier.weight(1f),
            //enabled = selectedValue.isNotEmpty(),
            onClick = onRepeatButtonClicked
        ) {
            Text(stringResource(R.string.repeat_game))
        }
    }
}

@Composable
fun ShowTrueAnswer(answer: List<Answer>, modifier: Modifier) {
    Column(modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))) {
        answer.forEach { item ->
            Card(modifier = modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))) {
                    Text(
                        text = item.question,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
                    Text(
                        text = item.answer,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
        }
    }
}

@Composable
fun ShowCongratulation(result: Int, modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = stringResource(R.string.congratulations_you_passed_the_quiz),
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
        )
        Text(
            text = stringResource(R.string.your_result, result),
            modifier = Modifier.padding(
                bottom = dimensionResource(R.dimen.padding_medium),
                start = dimensionResource(R.dimen.padding_medium),
                end = dimensionResource(R.dimen.padding_medium)
            )
        )
    }
}

//@Preview
//@Composable
//fun ShowRez() {
//    ShowTrueAnswer(
//        answer =
//        listOf(
//            Answer("Question one", "Answer one"),
//            Answer("Question two", "Answer two"),
//            Answer("Question tre", "Answer tre"),
//            Answer("Question for", "Answer for")
//        ), modifier = Modifier
//    )
//}

@Preview
@Composable
fun ShowRez2() {
    ShowResultScreen(
        answer = listOf(
            Answer("Question one", "Answer one"),
            Answer("Question two", "Answer two"),
            Answer("Question tre", "Answer tre"),
            Answer("Question for", "Answer for")
        ), result = 50, modifier = Modifier
    )
}