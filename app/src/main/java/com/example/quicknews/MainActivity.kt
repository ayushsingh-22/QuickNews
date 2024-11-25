package com.example.quicknews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavController
import com.example.quicknews.design.First_screen
import com.example.quicknews.design.Login_screen
import com.example.quicknews.design.Register_screen
import com.example.quicknews.ui.theme.QuickNewsTheme
import navigation.navigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent() {
            QuickNewsTheme {
                //First_screen()
                navigation()
            }
        }
    }
}
