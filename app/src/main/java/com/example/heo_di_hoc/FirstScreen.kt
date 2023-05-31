package com.example.heo_di_hoc

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun FirstScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Tittle

        Text (text = "QUIZ ARENA\n\n",
            style = MaterialTheme.typography.h4,
            color = Color.White,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Login button

        Button(
            onClick = { navController.navigate("login_screen")}, //Link to Login Screen
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(48.dp)
        ) {
            Text(
                text = "Login",
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        //Register button

        Button(
            onClick = {navController.navigate("register_screen")},
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(48.dp)

        ) {
            Text(
                text = "Register",
                color = Color.White
            )
        }
        Text(
            text = "\nA game made by Minh",
            style = MaterialTheme.typography.body1,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 8.dp)
                .height(20.dp)
        )
    }
}
