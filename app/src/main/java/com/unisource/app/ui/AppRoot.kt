package com.unisource.app.ui

import android.net.Uri
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.unisource.app.ui.screens.*

@Composable
fun AppRoot() {
    MaterialTheme {
        Surface(color = MaterialTheme.colorScheme.surfaceContainer) {

            val nav = rememberNavController()

            NavHost(
                navController = nav,
                startDestination = "home"
            ) {
            
                composable("home") {
                    HomeScreen(
                        onItemClick = { title, url ->
                            if (title == "Books") {
                                nav.navigate("books")
                            } else {
                                val encoded = Uri.encode(url)
                                nav.navigate("detail/$title/$encoded")
                            }
                        }
                    )
                }
            
                composable("books") {
                    BooksScreen(
                        onItemClick = { title, url ->
                            val encoded = Uri.encode(url)
                            nav.navigate("detail/$title/$encoded")
                        },
                        onYearClick = { year ->
                            if (year == 2025) {
                                nav.navigate("materials2025")
                            }
                        }
                    )
                }
            
                composable("materials2025") {
                    MaterialsScreen(
                        onItemClick = { title, url ->
                            val encoded = Uri.encode(url)
                            nav.navigate("detail/$title/$encoded")
                        }
                    )
                }
            
                composable(
                    "detail/{title}/{url}",
                    arguments = listOf(
                        navArgument("title") { type = NavType.StringType },
                        navArgument("url") { type = NavType.StringType }
                    )
                ) { backStack ->
                    val title = backStack.arguments?.getString("title") ?: ""
                    val url = Uri.decode(backStack.arguments?.getString("url") ?: "")
                    DetailScreen(title, url)
                }
            }
        }
    }
}