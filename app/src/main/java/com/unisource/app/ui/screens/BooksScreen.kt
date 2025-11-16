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
    onItemClick: (String, String) -> Unit,
    onYearClick: (Int) -> Unit
) {
    val years = (2017..2025).toList().reversed()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Books by Year") })
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier.padding(padding)
        ) {
            items(years) { year ->
                val item = AppItem(
                    title = year.toString(),
                    imageUrl = "https://picsum.photos/seed/$year/200/200"
                )

                VerticalItem(
                    item = item,
                    onClick = {
                        if (year == 2025) {
                            onYearClick(year)
                        } else {
                            onItemClick(item.title, item.imageUrl)
                        }
                    }
                )
            }
        }
    }
}