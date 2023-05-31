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

data class User(val name: String, val level: Int, val userID: String)

val defaultUsers = listOf(
    User("Hoang Duc Minh", 100, "1"),
    User("Doan Anh Dao", 100, "2")
)

@Composable
fun RegisterScreen(navController: NavController) {
    var userName by remember { mutableStateOf("") }
    var userID by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Register User",
            style = MaterialTheme.typography.h5,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))

        // User name input field
        OutlinedTextField(
            value = userName,
            onValueChange = { userName = it },
            label = { Text("User Name") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // User ID input field
        OutlinedTextField(
            value = userID,
            onValueChange = { userID = it },
            label = { Text("User ID") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Register button
        Button(
            onClick = { navController.navigate("login_screen") },
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(48.dp)
                .background(Color.Black)
        ) {
            Text(
                text = "Register",
                color = Color.White
            )
        }
    }
}
