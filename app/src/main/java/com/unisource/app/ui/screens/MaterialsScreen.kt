package com.unisource.app.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import com.unisource.app.model.AppItem
import com.unisource.app.ui.widgets.VerticalItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MaterialsScreen(
    onItemClick: (String, String) -> Unit
) {
    val materials = listOf(
        AppItem("Physics", "https://picsum.photos/id/28/200/200"),
        AppItem("Chemistry", "https://picsum.photos/id/29/200/200"),
        AppItem("Mathematics", "https://picsum.photos/id/30/200/200"),
        AppItem("Biology", "https://picsum.photos/id/31/200/200"),
        AppItem("Computer Science", "https://picsum.photos/id/32/200/200"),
        AppItem("English", "https://picsum.photos/id/33/200/200")
    )

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Materials (2025)") })
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(materials) { mat ->
                VerticalItem(
                    item = mat,
                    onClick = { onItemClick(mat.title, mat.imageUrl) }
                )
            }
        }
    }
}