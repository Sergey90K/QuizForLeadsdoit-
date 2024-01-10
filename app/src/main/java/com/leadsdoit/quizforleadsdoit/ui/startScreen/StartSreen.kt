package com.leadsdoit.quizforleadsdoit.ui.startScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leadsdoit.quizforleadsdoit.R
import com.leadsdoit.quizforleadsdoit.ui.AppViewModelProvider
import com.leadsdoit.quizforleadsdoit.ui.navigation.NavigationDestination
import com.leadsdoit.quizforleadsdoit.ui.theme.QuizForLeadsdoitTheme

object StartDestination : NavigationDestination {
    override val route = "start"
    override val titleRes = R.string.app_name
}

@Composable
fun StartScreen(viewModel: StartViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = AppViewModelProvider.Factory)) {

}

@Composable
fun Test() {

}

@Composable
fun SuccessfulScreen(startAction: () -> Unit, modifier: Modifier) {
    val imageModifier = Modifier
        .size(dimensionResource(R.dimen.image_size))
    //.clip(RoundedCornerShape(16.dp))
    //.border(BorderStroke(1.dp, Color.Black))
    //.background(Color.Blue)
//    Column(
//        modifier = modifier,
//        verticalArrangement = Arrangement.SpaceBetween
//    ) {
//        Column(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
//        ) {
//            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
//            Image(
//                painter = painterResource(id = R.drawable.media_design_hydropro_v2_tower_128),
//                contentDescription = stringResource(R.string.computer_icon),
//                contentScale = ContentScale.Fit,
//                modifier = imageModifier
//                //     .clickable {  }
//            )
//            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
//            Text(
//                text = stringResource(R.string.quiz_on_knowledge_of_computer_components),
//                style = MaterialTheme.typography.bodyMedium,
//               // modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
//            )
//            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
//
//        }
//     //   Row(modifier = Modifier.weight(1f, false)) {
//            Column(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalAlignment = Alignment.CenterHorizontally,
//                //verticalArrangement = Arrangement.SpaceBetween
//              //      dimensionResource(id = R.dimen.padding_medium)
//                ){
//                    Button(onClick = startAction) {
//                        Text(stringResource(R.string.to_begin))
//                    }
//
//                }
//       // }
//
//    }
    Box(contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.fillMaxSize(),
            // verticalArrangement = Arrangement.Center,
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
            ) {
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
                Image(
                    painter = painterResource(id = R.drawable.media_design_hydropro_v2_tower_128),
                    contentDescription = stringResource(R.string.computer_icon),
                    contentScale = ContentScale.Fit,
                    modifier = imageModifier
                    //     .clickable {  }
                )
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
                Text(
                    text = stringResource(R.string.quiz_on_knowledge_of_computer_components),
                    style = MaterialTheme.typography.displayLarge,
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium)),

                    )
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))

            }
//            Image(
//                painter = painterResource(id = R.drawable.media_design_hydropro_v2_tower_128),
//                contentDescription = stringResource(R.string.computer_icon),
//                contentScale = ContentScale.Fit,
//                modifier = imageModifier
//                //     .clickable {  }
//            )
//            Text(
//                text = stringResource(R.string.quiz_on_knowledge_of_computer_components),
//                style = MaterialTheme.typography.bodyMedium,
//                modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
//            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
            ) {
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
                Button(onClick = startAction) {
                    Text(stringResource(R.string.to_begin))
                }
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
            }

        }
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            CircularProgressIndicator(
                modifier = Modifier.width(150.dp),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_loading)))
            Text(
                text = "Loading",
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier.padding(all = dimensionResource(R.dimen.padding_small))
            )
        }
    }
}

@Composable
fun ErrorScreen(
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_connection_error),
                contentDescription = stringResource(R.string.connection_error)
            )
            Text(
                text = stringResource(R.string.failed_to_load_data_from_server),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
            )
            Button(onClick = retryAction) {
                Text(stringResource(R.string.retry))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPrevies() {
    QuizForLeadsdoitTheme(darkTheme = false) {
        LoadingScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPrevies2() {
    QuizForLeadsdoitTheme(darkTheme = false) {
        ErrorScreen(retryAction = { /*TODO*/ })
    }
}

@Preview()
@Composable
fun GreetingPrevies3() {
    QuizForLeadsdoitTheme(darkTheme = false) {
        SuccessfulScreen(startAction = {/*TODO*/ }, modifier = Modifier)
    }
}