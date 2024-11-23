package com.example.quicknews.design

import android.widget.Toast
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quicknews.data.AuthState
import com.example.quicknews.data.Authmodel
import com.example.quicknews.data.calculateScreenSize

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

    Button(
        onClick = {
            authViewModel.singout()
        },
        modifier = Modifier
            .offset(x = 50.dp, y = 25.dp)
            .size(screenWidth * 0.85f, ),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(Color(0xFF10a279))
    ) {
        Text(
            text = "Sign Out",
            fontSize = 25.sp
        )
    }
}