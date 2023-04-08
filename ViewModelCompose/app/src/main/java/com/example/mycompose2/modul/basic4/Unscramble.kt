package com.example.mycompose2.modul.basic4

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mycompose2.R
import com.example.mycompose2.modul.basic4.model.GameViewModel
import com.example.mycompose2.ui.theme.MyCompose2Theme


@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    gameViewModel: GameViewModel = viewModel()
){
    val gameUiState by gameViewModel.uiState.collectAsState()
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        GameStatus(
            wordCount = gameUiState.currentWordCount,
            score = gameUiState.score
        )
        GameLayout(
            currentScrambledWord = gameUiState.currentScrambledWord,
            onKeyboardDone = { gameViewModel.userGuess },
            userGuess = gameViewModel.userGuess,
            onUserGuessChanged = { gameViewModel.updateUserGuess(it) },
            isGuessWrong = gameUiState.isGuessedWordWrong
        )
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            OutlinedButton(
                onClick = { gameViewModel.skipWord() },
                modifier = modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Text(text = stringResource(id = R.string.skip))
            }
            Button(
                modifier = modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(start = 8.dp),
                onClick = { gameViewModel.checkUserGuess() }
            ) {
                Text(text = stringResource(id = R.string.submit))
            }
        }
        if (gameUiState.isGameOver){
            FinalScoreDialog(
                score = gameUiState.score,
                onPlayAgain = { gameViewModel.resetGame() }
            )
        }
    }
}

@Composable
fun GameStatus(
    wordCount: Int,
    score: Int,
    modifier: Modifier = Modifier){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .size(48.dp),
    ) {
        Text(
            text = stringResource(id = R.string.word_count, wordCount),
            fontSize = 18.sp
        )
        Text(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End),
            text = stringResource(id = R.string.score, score),
            fontSize = 18.sp
        )
    }
}

@Composable
fun GameLayout(
    onUserGuessChanged: (String) -> Unit,
    onKeyboardDone: () -> Unit,
    isGuessWrong: Boolean,
    userGuess: String,
    currentScrambledWord: String,
    modifier: Modifier = Modifier
){
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        Text(
            text = currentScrambledWord,
            fontSize = 45.sp,
            modifier = modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = stringResource(id = R.string.instructions),
            fontSize = 17.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        OutlinedTextField(
            value = userGuess,
            singleLine = true,
            modifier = modifier.fillMaxWidth(),
            onValueChange = onUserGuessChanged,
            label = {
                if (isGuessWrong) {
                    Text(stringResource(id = R.string.wrong_guess))
                } else {
                    Text(stringResource(id = R.string.enter_your_word))
                }
            },
            isError = isGuessWrong,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { onKeyboardDone() }
            )
        )
    }
}

// Creates and shows an AlertDialog with final score
@Composable
private fun FinalScoreDialog(
    score: Int,
    onPlayAgain: () -> Unit,
    modifier: Modifier = Modifier
) {
    val activity = (LocalContext.current as Activity)

    AlertDialog(
        onDismissRequest = {
            // Dismiss the dialog when the user clicks outside the dialog or on the back
            // Button. if you want to disable that functionality
            // onCloseRequest
        },
        title = { Text(stringResource(id = R.string.congratulations)) },
        text = { Text(stringResource(id = R.string.you_scored, score)) },
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = { activity.finish() }) {
                Text(text = stringResource(id = R.string.exit))
            }
        },
        confirmButton = {
            TextButton(onClick = onPlayAgain) {
                Text(text = stringResource(id = R.string.play_again))
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyCompose2Theme {
        GameScreen()
    }
}