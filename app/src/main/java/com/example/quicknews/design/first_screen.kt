package com.example.quicknews.design

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.quicknews.R
import com.example.quicknews.data.calculateScreenSize
import kotlinx.coroutines.delay

@Composable
fun First_screen(navHostController: NavHostController) {

    val screenSize = calculateScreenSize()
    val screenWidth = screenSize.first.dp
    val screenHeight = screenSize.second.dp

    Column(modifier = Modifier
        .fillMaxSize()
        .offset(x = 40.dp, y = 15.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.app_logo),
            contentDescription = "App Logo",
            modifier = Modifier
                .size(screenWidth * 0.8f, screenHeight * 0.4f)
                .offset(x = 7.dp),
        )

        Spacer(modifier = Modifier.height(25.dp))
        Button(
            onClick = { navHostController.navigate("login") },
            modifier = Modifier
                .size(screenWidth * 0.9f, screenHeight * 0.06f)
                .offset(x = -(screenWidth * 0.03f)),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFF10a279))
        ) {
            Text(text = "Login",
               fontSize = 25.sp)
        }

        HorizontalDivider(modifier = Modifier
            .height(25.dp)
            .size(screenWidth * 0.85f)
            .offset(x = 0.dp, y = 10.dp),
            thickness = 2.dp,)

        Button(
            onClick = { navHostController.navigate("register") },
            modifier = Modifier
                .size(screenWidth * 0.9f, screenHeight * 0.06f)
                .offset(x = -(screenWidth * 0.03f)),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFF10a279))
        ) {
            Text(text = "Register",
                fontSize = 25.sp)
        }

        var offsetY by remember { mutableStateOf(300.dp) }
        val animatedOffsetY by animateDpAsState(
            targetValue = offsetY,
            animationSpec = tween(durationMillis = 1000)
        )

        LaunchedEffect(Unit) {
            delay(500)
            offsetY = 50.dp
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = "Developed by AYUSH",
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset(
                        x = (-50).dp,
                        y = animatedOffsetY - 100.dp
                    ),
                fontSize = 16.sp,
            )
        }

    }
}
