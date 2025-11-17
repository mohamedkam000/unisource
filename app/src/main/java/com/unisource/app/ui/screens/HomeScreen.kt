package com.unisource.app.ui.screens

import androidx.compose.material3.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import com.unisource.app.ui.widgets.HorizontalCard
import com.unisource.app.ui.widgets.VerticalItem
import com.unisource.app.model.AppItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onItemClick: (String, String) -> Unit
) {
    val horizontalCards = listOf(
        AppItem("Announcements", "https://cdn-icons-png.flaticon.com/512/7653/7653930.png"),
        AppItem("Activities", "https://cdn-icons-png.flaticon.com/512/18120/18120765.png"),
        AppItem("Topics", "https://cdn-icons-png.flaticon.com/512/9431/9431885.png"),
        AppItem("Discussion", "https://cdn-icons-png.flaticon.com/512/17262/17262972.png"),
    )

    val categories = listOf(
        AppItem("Books", "https://cdn-icons-png.flaticon.com/512/5402/5402751.png"),
        AppItem("Schedule", "https://cdn-icons-png.flaticon.com/512/3652/3652191.png"),
        AppItem("Exams", "https://cdn-icons-png.flaticon.com/512/9043/9043010.png"),
        AppItem("Assignments", "https://cdn-icons-png.flaticon.com/512/11265/11265088.png"),
        AppItem("Courses", "https://cdn-icons-png.flaticon.com/512/10748/10748346.png"),
)

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                title = { Text("Unisource") },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                    scrolledContainerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                ),
                scrollBehavior = scrollBehavior
            )
        },
        containerColor = MaterialTheme.colorScheme.surfaceVariant
    ) { padding ->
    }
}