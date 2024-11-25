package com.example.quicknews.design

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.quicknews.R
import com.example.quicknews.data.AuthState
import com.example.quicknews.data.Authmodel
import com.example.quicknews.data.calculateScreenSize
import com.example.quicknews.data.navbar_data
import com.google.android.play.core.integrity.x

@Composable
fun Home_screen(navController: NavController, authViewModel: Authmodel) {

    val screenSize = calculateScreenSize()
    val screenWidth = screenSize.first.dp
    val screenHeight = screenSize.second.dp
    val context = LocalContext.current

    val authState = authViewModel.authState.observeAsState()
    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Unauthenticated -> {
                navController.navigate("welcome_screen")
            }

            is AuthState.Error -> Toast.makeText(
                context, (authState.value as AuthState.Error).message,
                Toast.LENGTH_SHORT
            ).show()

            else -> Unit
        }
    }

    val navItems = listOf(
        navbar_data(icon = painterResource(id = R.drawable.home_screen), title = "Home"),
        navbar_data(icon = painterResource(id = R.drawable.search), title = "Search"),
        navbar_data(icon = painterResource(id = R.drawable.profile), title = "Profile"),
    )

    Box(modifier = Modifier.fillMaxSize()
        .background(MaterialTheme.colorScheme.surface)) {

        Column(modifier = Modifier.fillMaxSize()) {

            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .weight(1f) // Occupies remaining space above the navigation bar
            ) {
                Image(
                    painter = painterResource(R.drawable.app_logo),
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .size(screenWidth * 0.3f, screenHeight * 0.11f)
                )

                Icon(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Profile",
                    modifier = Modifier
                        .size(30.dp)
                        .offset(x = (screenWidth * 0.55f), y = 40.dp)
                )
            }
        }

        NavigationBar(modifier = Modifier.align(androidx.compose.ui.Alignment.BottomCenter)
            .size(width = screenWidth, height = 80.dp)
            .padding(10.dp)
            .shadow(elevation = 8.dp, shape  = RoundedCornerShape(15)),
            containerColor = MaterialTheme.colorScheme.surface,) {
            navItems.forEachIndexed { index, item ->
                NavigationBarItem(modifier = Modifier.padding(top = 15.dp),
                    selected = true,
                    onClick = {
//                        when (index) {
//                            0 -> navController.navigate("home_screen")
//                            1 -> navController.navigate("search_screen")
//                            2 -> navController.navigate("profile_screen")
//                        }
                    },
                    icon = {
                        Image(
                            painter = item.icon,
                            contentDescription = item.title,
                            modifier = Modifier.size(22.dp),
                            colorFilter = ColorFilter.tint(Color(0xFFEF2E54)) // Use ARGB hex code
                        )
                    },

                )
            }
        }
    }
}
