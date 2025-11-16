package com.unisource.app.ui

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.unisource.app.ui.screens.HomeScreen
import com.unisource.app.ui.screens.DetailScreen

@Composable
fun AppRoot() {
    MaterialTheme {
        Surface(color = MaterialTheme.colorScheme.surfaceVariant) {
            val nav = rememberNavController()

            NavHost(navController = nav, startDestination = "home") {
                composable("home") {
                    HomeScreen(
                        onItemClick = { title, imageUrl ->
                            nav.navigate("detail/$title/$imageUrl")
                        }
                    )
                }

                composable("detail/{title}/{url}") { backStack ->
                    DetailScreen(
                        title = backStack.arguments?.getString("title") ?: "",
                        imageUrl = backStack.arguments?.getString("url") ?: ""
                    )
                }
            }
        }
    }
}