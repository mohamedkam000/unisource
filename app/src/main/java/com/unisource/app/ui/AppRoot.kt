package com.unisource.app.ui

import android.net.Uri
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.unisource.app.ui.screens.*
import com.unisource.app.data.AnnouncementsRepository

@Composable
fun AppRoot() {
    MaterialTheme {
        Surface(color = MaterialTheme.colorScheme.surfaceContainer) {
            val nav = rememberNavController()

            NavHost(
                navController = nav,
                startDestination = NavRoute.Home.route
            ) {
                composable(NavRoute.Home.route) {
                    HomeScreen { title, url ->
                        when (title) {
                            "Books" -> nav.navigate(NavRoute.Books.route)
                            "Announcements" -> nav.navigate(NavRoute.Announcements.route)
                            "Activities" -> nav.navigate(NavRoute.Activities.route)
                            "Topics" -> nav.navigate(NavRoute.Topics.route)
                            else -> nav.navigate(NavRoute.Detail.go(title, url))
                        }
                    }
                }

                // BOOKS
                composable(NavRoute.Books.route) {
                    BooksScreen(
                        onItemClick = { title, url ->
                            nav.navigate(NavRoute.Detail.go(title, url))
                        },
                        onSemesterClick = { semester ->
                            nav.navigate(NavRoute.Materials.go(semester))
                        }
                    )
                }

                // MATERIALS
                composable(
                    NavRoute.Materials.route,
                    arguments = listOf(navArgument("semester") { type = NavType.StringType })
                ) { backStack ->
                    val semester = backStack.arguments?.getString("semester") ?: ""
                    MaterialsScreen(semester)
                }

                // ANNOUNCEMENTS
                composable(NavRoute.Announcements.route) {
                    AnnouncementsScreen { title ->
                        nav.navigate(NavRoute.AnnouncementDetail.go(title))
                    }
                }

                // ANNOUNCEMENT DETAIL
                composable(
                    NavRoute.AnnouncementDetail.route,
                    arguments = listOf(navArgument("title") { type = NavType.StringType })
                ) {
                    val title = it.arguments?.getString("title") ?: ""
                    val announcement =
                        AnnouncementsRepository.announcements.first { a -> a.title == title }
                    AnnouncementDetailScreen(announcement)
                }

                // ANY OTHER DETAIL PAGE
                composable(
                    NavRoute.Detail.route,
                    arguments = listOf(
                        navArgument("title") { type = NavType.StringType },
                        navArgument("url") { type = NavType.StringType }
                    )
                ) {
                    val title = it.arguments?.getString("title") ?: ""
                    val url = Uri.decode(it.arguments?.getString("url") ?: "")
                    DetailScreen(title, url)
                }
            }
        }
    }
}