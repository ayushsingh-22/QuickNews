package com.example.quicknews.design

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.quicknews.R
import com.example.quicknews.data.AuthState
import com.example.quicknews.data.Authmodel
import com.example.quicknews.data.calculateScreenSize

@Composable
fun Login_screen(navController: NavHostController, authViewModel: Authmodel) {

    val screenSize = calculateScreenSize()
    val screenWidth = screenSize.first.dp
    val screenHeight = screenSize.second.dp
    val context = LocalContext.current

    val authState = authViewModel.authState.observeAsState()
    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Authenticated -> {
                navController.navigate("home")
            }

            is AuthState.Error -> Toast.makeText(
                context, (authState.value as AuthState.Error).message,
                Toast.LENGTH_SHORT
            ).show()

            else -> Unit
        }
    }

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Card(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row {

                Image(
                    painter = painterResource(id = R.drawable.right_arrow),
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .size(screenWidth * 0.08f, screenHeight * 0.1f)
                        .offset(x = 15.dp, y = 22.dp)
                        .clickable { navController.navigate("welcome_screen") }
                )

                Text(
                    text = "Login",
                    fontSize = 50.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF10a279),
                    modifier = Modifier
                        .padding(8.dp)
                        .offset(x = screenWidth * 0.25f, y = 25.dp)
                )
            }

            Column(modifier = Modifier.offset(x = screenWidth * 0.07f,y=20.dp)
                .fillMaxWidth())
            {
                Text(
                    text = "Email",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )
                OutlinedTextField(
                    value = email,
                    singleLine = true,
                    onValueChange = { email = it },
                    label = { Text("Enter Email") },
                    modifier = Modifier
                        .clip(RectangleShape)
                        .size(screenWidth * 0.85f, 70.dp)
                )

                Text(
                    text = "Password",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 10.dp)
                )
                OutlinedTextField(
                    value = password,
                    singleLine = true,
                    onValueChange = { password = it },
                    label = { Text("Enter password") },
                    modifier = Modifier.size(screenWidth * 0.85f, 70.dp),
                    visualTransformation = PasswordVisualTransformation()
                )

                Text(
                    text = "Forgot Password?",
                    modifier = Modifier.padding(top = 10.dp)
                        .clickable {

                        println("Forgot Password clicked!")
                    },
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Button(
                    onClick = { authViewModel.signup(email, password) },
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .size(screenWidth * 0.85f, screenHeight * 0.06f),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFF10a279))
                ) {
                    Text(
                        text = "Login ",
                        fontSize = 25.sp
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.offset(x=-30.dp, y = -10.dp)
                        .padding(horizontal = screenWidth * 0.1f, vertical = 25.dp,)
                ) {
                    Divider(
                        thickness = 2.dp,
                        modifier = Modifier
                            .weight(1f)
                    )
                    Text(
                        text = " OR ",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                    )
                    Divider(
                        thickness = 2.dp,
                        modifier = Modifier
                            .weight(1f)
                    )
                }

                Button(
                    onClick = { /* Handle click */ },
                    modifier = Modifier
                        .offset(y = -25.dp)
                        .size(screenWidth * 0.85f, screenHeight * 0.06f),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFF10a279))
                ) {
                    Text(
                        text = "Continue with Google ",
                        fontSize = 25.sp
                    )
                }

                Text(
                    text = "Don’t have an account?  Register",
                    modifier = Modifier.offset(10.dp,-10.dp)
                        .clickable {navController.navigate("register")},
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            }

        }
    }