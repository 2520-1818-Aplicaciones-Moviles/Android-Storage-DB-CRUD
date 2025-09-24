package com.example.semana5_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.semana5_2.navigation.Nav
import com.example.semana5_2.ui.theme.Semana5_2Theme
import com.example.semana5_2.viewmodels.UserViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val viewModel by viewModels<UserViewModel>()

        setContent {
            Semana5_2Theme {
               Nav(this, viewModel)
            }
        }
    }
}
