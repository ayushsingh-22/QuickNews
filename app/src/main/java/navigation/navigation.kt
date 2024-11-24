package navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quicknews.data.Authmodel
import com.example.quicknews.design.First_screen
import com.example.quicknews.design.Home_screen
import com.example.quicknews.design.Login_screen
import com.example.quicknews.design.Register_screen

@Composable
fun navigation(modifier: Modifier = Modifier) {

    val navcontrol = rememberNavController()
    val authViewModel: Authmodel = viewModel()

    NavHost(navController = navcontrol, startDestination = "home") {

        composable("login") { Login_screen(navcontrol,authViewModel) }
        composable("register") { Register_screen(navcontrol, authViewModel) }
        composable("welcome_screen") { First_screen(navcontrol) }
        composable("home") { Home_screen(navcontrol,authViewModel) }

    }
}