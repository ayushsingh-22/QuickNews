package com.example.quicknews.design

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import bottomnav_screen.home
import bottomnav_screen.profile
import bottomnav_screen.search
import com.example.quicknews.R
import com.example.quicknews.data.AuthState
import com.example.quicknews.data.Authmodel
import com.example.quicknews.data.calculateScreenSize
import com.example.quicknews.data.navbar_data

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
                navController.navigate("login") // Redirect to login screen
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
        navbar_data(icon = painterResource(id = android.R.drawable.ic_menu_myplaces), title = "Profile"),
    )

    var selectedIndex = remember { mutableStateOf(0) }

    Box(modifier = Modifier.fillMaxSize()
        .background(Color.White)) {

        Column(modifier = Modifier.fillMaxSize()) {

            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .weight(1f)
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
                    tint = Color.Gray,
                    modifier = Modifier
                        .size(30.dp)
                        .offset(x = (screenWidth * 0.55f), y = 40.dp)
                )
            }
        }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                NavigationBar(
                    modifier = Modifier
                        .size(width = screenWidth * 0.8f, height = 70.dp)
                        .align(Alignment.TopEnd)
                        .padding(10.dp)
                        .offset(x = screenWidth * 0.1f, y = -10.dp)
                        .shadow(elevation = 8.dp, shape = RoundedCornerShape(20)),
                    containerColor = Color.White
                ) {
                    navItems.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = selectedIndex.value == index,
                            onClick = { selectedIndex.value = index },
                            icon = {
                                Image(
                                    painter = item.icon,
                                    contentDescription = item.title,
                                    modifier = Modifier
                                        .size(50.dp)
                                        .align(Alignment.CenterVertically),
                                    colorFilter = ColorFilter.tint(
                                        if (selectedIndex.value == index) Color(0xFFEF2E54)
                                        else Color.Gray
                                    )
                                )
                            }
                        )
                    }
                }
            },
            content = { innerPadding ->

                NavBarScreen(
                    selectedItem = selectedIndex.value,
                    navController = navController,
                    authViewModel = authViewModel
                )
            }
        )
    }
}

@Composable
fun NavBarScreen(
    selectedItem: Int,
    navController: NavController,
    authViewModel: Authmodel
) {
    when (selectedItem) {
        0 -> home( authViewModel = authViewModel)
        1 -> search()
        2 -> profile()
    }
}


