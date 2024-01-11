package com.leadsdoit.quizforleadsdoit.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.leadsdoit.quizforleadsdoit.R
import com.leadsdoit.quizforleadsdoit.ui.navigation.QuizNavHost


//@Composable
//fun QuizApp(startViewModel : StartViewModel = viewModel(factory = AppViewModelProvider.Factory)){
//    //val uiState by startViewModel.quizUiState.collectAsStateWithLifecycle()
//    //Test()
//   // Log.d("Test", uiState.toString())
//
//}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizApp(navController: NavHostController = rememberNavController()) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { QuizTopAppBar(scrollBehavior = scrollBehavior) }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            QuizNavHost(navController = navController)
        }
    }

}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun QuizTopAppBar(
//    title: String,
//    canNavigateBack: Boolean,
//    modifier: Modifier = Modifier,
//    scrollBehavior: TopAppBarScrollBehavior? = null,
//    navigateUp: () -> Unit = {}
//) {
//    CenterAlignedTopAppBar(
//        title = { Text(title) },
//        modifier = modifier,
//        scrollBehavior = scrollBehavior,
//        navigationIcon = {
//            if (canNavigateBack) {
//                IconButton(onClick = navigateUp) {
//                    Icon(
//                        imageVector = Icons.Filled.ArrowBack,
//                        contentDescription = stringResource(R.string.back_button)
//                    )
//                }
//            }
//        }
//    )
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizTopAppBar(scrollBehavior: TopAppBarScrollBehavior, modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.logo_size))
                        .padding(dimensionResource(R.dimen.padding_small)),
                    painter = painterResource(R.drawable.logo),
                    contentDescription = stringResource(R.string.company_icon)
                )
                Text(
                    text = stringResource(R.string.quiz),
                    style = MaterialTheme.typography.displaySmall
                )
            }
        },
        modifier = modifier
    )
}