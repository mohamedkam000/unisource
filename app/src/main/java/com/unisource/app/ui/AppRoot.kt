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
import com.unisource.app.data.AnnouncementsRepository

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
                            when (title) {
                                "Books" -> nav.navigate("books")
                                "Announcements" -> nav.navigate("announcements")
//                                "Activities" -> nav.navigate("activities")
//                                "Topics" -> nav.navigate("topics")
                                else -> {
                                    val encoded = Uri.encode(url)
                                    nav.navigate("detail/$title/$encoded")
                                }
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
                        onSemesterClick = { semesterTitle ->
                            nav.navigate("materials/$semesterTitle")
                        }
                    )
                }

                composable(
                    route = "materials/{semester}",
                    arguments = listOf(
                        navArgument("semester") { type = NavType.StringType }
                    )
                ) { backStack ->
                    val semester = backStack.arguments?.getString("semester") ?: "Unknown"
                    MaterialsScreen(semester = semester)
                }

                composable("announcements") {
                    AnnouncementsScreen { title ->
                        nav.navigate("announcement_detail/$title")
                    }
                }
                
                composable(
                    "announcement_detail/{title}",
                    arguments = listOf(navArgument("title") { type = NavType.StringType })
                ) { backStack ->
                    val title = backStack.arguments?.getString("title") ?: ""
                    val announcement = AnnouncementsRepository.announcements.find { it.title == title }!!
                    AnnouncementDetailScreen(announcement = announcement)
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