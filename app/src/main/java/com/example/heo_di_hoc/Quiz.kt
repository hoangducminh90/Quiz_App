package com.example.heo_di_hoc

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.runBlocking

@Composable
fun QuizScreen(quizId: Int) {
    val context = LocalContext.current

    val quizSet = remember {
        runBlocking {
            getRandomQuizSetFromDatabase(context, quizId) ?: throw IllegalArgumentException("Quiz with ID $quizId not found")
        }
    }

    var currentQuizIndex by remember { mutableStateOf(0) }
    var selectedOption by remember { mutableStateOf(-1) }
    var isLocked by remember { mutableStateOf(false) }
    var results by remember { mutableStateOf(mutableListOf<String>()) }
    var showResults by remember { mutableStateOf(false) }

    val options = listOf(quizSet.answer1, quizSet.answer2, quizSet.answer3, quizSet.answer4)

    if (!showResults) {
        if (currentQuizIndex < 1) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Question ${currentQuizIndex + 1}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                    ) {
                        Text(
                            text = quizSet.question,
                            fontSize = 18.sp,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        options.forEachIndexed { index, option ->
                            val isCorrectAnswer = quizSet.correctAnswer == index + 1
                            val isSelectedOption = selectedOption == index + 1
                            val backgroundColor = when {
                                isSelectedOption && isCorrectAnswer -> Color.Green
                                isSelectedOption && !isCorrectAnswer -> Color.Red
                                else -> Color.White
                            }

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                                    .background(
                                        color = backgroundColor,
                                        shape = RoundedCornerShape(8.dp),
                                    )
                                    .clickable(enabled = !isLocked) {
                                        selectedOption = index + 1
                                        isLocked = true
                                    }
                            ) {
                                Text(
                                    text = option,
                                    fontSize = 16.sp,
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                        }
                    }
                    if (isLocked) {
                        val buttonText = if (currentQuizIndex < 1) "See Result" else "Continue"
                        Button(
                            onClick = {
                                results.add("Question ${currentQuizIndex + 1}: ${if (selectedOption == quizSet.correctAnswer) "Correct" else "Wrong"}")
                                showResults = true
                            },
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(top = 16.dp)
                        ) {
                            Text(text = buttonText)
                        }
                    }
                }
            }
        }
    } else {
        ResultsScreen(results)
    }
}

@Composable
fun ResultsScreen(results: List<String>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "Results", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(results) { result ->
                Text(text = result, fontSize = 18.sp)
            }
        }
    }
}
