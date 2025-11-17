package com.unisource.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.*
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.exitUntilCollapsedScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.input.*
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
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
        AppItem("Semester 1", "https://picsum.photos/id/1010/200/200"),
        AppItem("Semester 2", "https://picsum.photos/id/1011/200/200"),
        AppItem("Semester 3", "https://picsum.photos/id/1012/200/200"),
        AppItem("Semester 4", "https://picsum.photos/id/1013/200/200"),
        AppItem("Semester 5", "https://picsum.photos/id/1014/200/200"),
        AppItem("Semester 6", "https://picsum.photos/id/1015/200/200"),
        AppItem("Semester 7", "https://picsum.photos/id/1016/200/200"),
        AppItem("Semester 8", "https://picsum.photos/id/1017/200/200"),
        AppItem("Semester 9", "https://picsum.photos/id/1020/200/200"),
        AppItem("Semester 10", "https://picsum.photos/id/1021/200/200")
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
                            model = "https://picsum.photos/id/1003/200/200",
                            contentDescription = null,
                            modifier = Modifier
                                .size(48.dp)
                                .scale(scale),
                            contentScale = ContentScale.Fit
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