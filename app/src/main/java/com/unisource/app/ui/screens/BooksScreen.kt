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
fun BooksScreen(
    onItemClick: (String, String) -> Unit
) {
    val books = List(5) {
        AppItem(
            title = "Book #${it + 1}",
            imageUrl = "https://picsum.photos/id/1025/200/200"
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Books") })
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding)
        ) {
            items(books) { book ->
                VerticalItem(
                    item = book,
                    onClick = { onItemClick(book.title, book.imageUrl) }
                )
            }
        }
    }
}