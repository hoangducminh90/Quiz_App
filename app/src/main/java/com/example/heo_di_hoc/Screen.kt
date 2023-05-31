sealed class Screen(val route: String) {
    object FirstScreen : Screen("first_screen")
    object LoginScreen : Screen("login_screen")
    object DefaultScreen : Screen("default_screen")
    object RegisterScreen : Screen("register_screen")
    object HomeScreen : Screen("home_screen/{userID}") {
        fun createRoute(userID: String) = "home_screen/$userID"
    }
    object QuizScreen : Screen("quizScreen/{quizSetId}") {
        fun createRoute(quizSetId: Int) = "quizScreen/$quizSetId"
    }
}
