package com.unisource.app.ui.screens.books

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.*
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.exitUntilCollapsedScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.CircleShape
import coil.compose.AsyncImage
import com.unisource.app.model.AppItem
import com.unisource.app.ui.widgets.VerticalItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BooksScreen(
    onItemClick: (String, String) -> Unit,
    onSemesterClick: (String) -> Unit
) {
    val semesters = listOf(
        AppItem("Semester 1", "https://cdn-icons-png.flaticon.com/512/8068/8068017.png"),
        AppItem("Semester 2", "https://cdn-icons-png.flaticon.com/512/8068/8068073.png"),
        AppItem("Semester 3", "https://cdn-icons-png.flaticon.com/256/8068/8068129.png"),
        AppItem("Semester 4", "https://cdn-icons-png.flaticon.com/512/8068/8068184.png"),
        AppItem("Semester 5", "https://cdn-icons-png.freepik.com/512/8068/8068238.png"),
        AppItem("Semester 6", "https://cdn-icons-png.flaticon.com/512/8068/8068292.png"),
        AppItem("Semester 7", "https://cdn-icons-png.flaticon.com/512/9494/9494631.png"),
        AppItem("Semester 8", "https://cdn-icons-png.flaticon.com/512/8068/8068393.png"),
        AppItem("Semester 9", "https://cdn-icons-png.flaticon.com/256/8067/8067930.png"),
        AppItem("Semester 10", "https://cdn-icons-png.flaticon.com/512/9494/9494568.png")
    )

    val scrollBehavior = exitUntilCollapsedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        val collapseFraction = scrollBehavior.state.collapsedFraction
                        val scale = 1f - (0.45f * collapseFraction)

                        AsyncImage(
                            model = "https://cdn-icons-png.flaticon.com/512/5402/5402751.png",
                            contentDescription = null,
                            modifier = Modifier
                                .size(48.dp)
                                .scale(scale)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(modifier = Modifier.width(12.dp))
                        Text("Books by Semester")
                    }
                },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                    scrolledContainerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding)
        ) {
            items(semesters) { item ->
                VerticalItem(
                    item = item,
                    onClick = {
                        onSemesterClick(item.title)
                    }
                )
            }
        }
    }
}