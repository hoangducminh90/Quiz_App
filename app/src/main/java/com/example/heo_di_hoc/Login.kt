package com.example.heo_di_hoc

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun LoginScreen(navController: NavController) {
    var userId by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Please login by your UserID",
            style = MaterialTheme.typography.h5,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))

        // User ID input field
        OutlinedTextField(
            value = userId,
            onValueChange = { userId = it },
            label = { Text("User ID") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Login button
        Button(
            onClick = {
                navController.navigate("home_screen/$userId")
            },
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(48.dp)
                .background(Color.Black)
        ) {
            Text(
                text = "Login",
                color = Color.White
            )
        }
        Text(
            text = "In this screen, you must type in your ID number, it is an unique integer number. \nIf you type correctly, you can login successfully",
            style = MaterialTheme.typography.body1,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}
