package com.example.heo_di_hoc

import Screen
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.heo_di_hoc.ui.theme.MyApplicationTheme

class MainActivity : AppCompatActivity() {
    private lateinit var quizDatabase: QuizDatabase

    //Phan ben tren them vao de xu ly database cua cau hoi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        quizDatabase = QuizDatabase.getDatabase(applicationContext)

        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = Screen.DefaultScreen.route) {
                    composable(Screen.FirstScreen.route) { FirstScreen(navController) }
                    composable(Screen.LoginScreen.route) { LoginScreen(navController) }
                    composable(Screen.DefaultScreen.route) { DefaultScreen(navController) }
                    composable(Screen.RegisterScreen.route) { RegisterScreen(navController) }
                    composable(
                        Screen.HomeScreen.route,
                        arguments = listOf(navArgument("userID") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val userID = backStackEntry.arguments?.getString("userID") ?: ""
                        HomeScreen(navController, userID)
                    }
                    composable(
                        Screen.QuizScreen.route + "/{quizId}",
                        arguments = listOf(navArgument("quizId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val quizId = backStackEntry.arguments?.getInt("quizId") ?: 1
                        QuizScreen(quizId)
                    }
                }
            }
        }
    }
}
