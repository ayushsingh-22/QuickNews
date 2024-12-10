package navigation

import NewsViewModel
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import navigation_screen.BrakingNews_Screen
import navigation_screen.DetailNewsScreen
import navigation_screen.home_screen
import navigation_screen.notification_screen
import navigation_screen.search_screen

@Composable
fun navigation() {
    val navController = rememberNavController()
    val newsViewModel: NewsViewModel = viewModel()

    NavHost(navController = navController, startDestination = "breaking_news") {
        composable("home") { home_screen(navController) }
        composable("notification") { notification_screen(navController) }
        composable("search") { search_screen(navController) }
        composable("breaking_news") { BrakingNews_Screen(navController, newsViewModel) }
        composable(
            "detail_news?newsUrl={newsUrl}",
            arguments = listOf(navArgument("newsUrl") { type = NavType.StringType })
        ) { backStackEntry ->
            val newsUrl = backStackEntry.arguments?.getString("newsUrl") ?: ""
            DetailNewsScreen(newsUrl)
        }
    }
}


