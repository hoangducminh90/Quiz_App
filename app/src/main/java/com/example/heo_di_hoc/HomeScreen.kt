package com.example.heo_di_hoc

import Screen
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController



@Composable
fun HomeScreen(navController: NavController, userID: String?) {
    // find user by userID and get user object

    fun findUserById(userID: String): User? {
        return defaultUsers.find { it.userID == userID }
    }
    val user = findUserById(userID.toString())
    val context = LocalContext.current

    // render user data
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Quiz Arena",
            style = MaterialTheme.typography.h4,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "User Profile",
            style = MaterialTheme.typography.h5,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        if (user != null) {
            Text(
                text = user.name,
                style = MaterialTheme.typography.body1,
                color = Color.Black
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        if (user != null) {
            Text(
                text = "Point: ${user.level}",
                style = MaterialTheme.typography.body1,
                color = Color.Black
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Rule of the game",
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Click on the play button, then you can play the game. \n Base on the time you spend and number of correct answer, you will got a score for each round. \n If the other player get higher score, he/she will win that round and earn all the point you deposit. \n Each round, you can set a deposit from 1 to 5 after finish the round",
            style = MaterialTheme.typography.body1,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            navController.navigate(Screen.QuizScreen.route + "/1")
        }) {
            Text(text = "Generate Quiz Sets")
        }

}
}