package com.example.quicknews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.quicknews.design.First_screen
import com.example.quicknews.design.Login_screen
import com.example.quicknews.design.Register_screen
import com.example.quicknews.ui.theme.QuickNewsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuickNewsTheme {
                //First_screen()
                //Login_screen()
                Register_screen()
            }
        }
    }
}
