package com.unisource.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
        AppItem("Schedule", "https://cdn-icons-png.flaticon.com/512/3652/3652191.png"),
        AppItem("Books", "https://cdn-icons-png.flaticon.com/512/5402/5402751.png"),
        AppItem("Exams", "https://cdn-icons-png.flaticon.com/512/9043/9043010.png"),
        AppItem("Assignments", "https://cdn-icons-png.flaticon.com/512/11265/11265088.png"),
        AppItem("Courses", "https://cdn-icons-png.flaticon.com/512/10748/10748346.png"),
)

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Unisource") })
        },
        containerColor = MaterialTheme.colorScheme.surfaceVariant
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            item {
                Text(
                    "Featured",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(16.dp)
                )

                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(horizontalCards) { card ->
                        HorizontalCard(card, onClick = { onItemClick(card.title, card.imageUrl) })
                    }
                }
            Spacer(Modifier.height(32.dp))
            }

            item {
                Text(
                    "Categories",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(16.dp)
                )
            }

            items(categories) { cat ->
                VerticalItem(
                    item = cat,
                    onClick = { onItemClick(cat.title, cat.imageUrl) }
                )
            }
        }
    }
}