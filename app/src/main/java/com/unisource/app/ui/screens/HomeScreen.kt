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
        AppItem("Engineer Day", "https://picsum.photos/id/1018/600/400"),
        AppItem("Discussion", "https://picsum.photos/id/1015/600/400"),
        AppItem("Announcement", "https://picsum.photos/id/1020/600/400"),
    )

    val categories = listOf(
        AppItem("Ads", "https://picsum.photos/id/1011/200/200"),
        AppItem("Exams", "https://picsum.photos/id/1050/200/200"),
        AppItem("Books", "https://picsum.photos/id/1080/200/200"),
        AppItem("Sheets", "https://picsum.photos/id/1040/200/200"),
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

                Spacer(Modifier.height(24.dp))
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