package com.example.semana5_2.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.semana5_2.view.Home
import com.example.semana5_2.view.ListUser
import com.example.semana5_2.viewmodels.UserViewModel

@Composable
fun Nav(context: Context, viewModel: UserViewModel) {

    val saveScreen = rememberNavController()

    NavHost(navController = saveScreen, startDestination = "V1") {
        composable("V1"){ Home(saveScreen, context, viewModel) }
        composable("V2"){ ListUser(saveScreen, context, viewModel) }
    }


}