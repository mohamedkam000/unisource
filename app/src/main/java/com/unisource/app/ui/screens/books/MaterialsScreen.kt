package com.unisource.app.ui.screens.books

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable
import coil.compose.AsyncImage
import com.unisource.app.data.MaterialsRepository
import com.unisource.app.data.MaterialItem
import com.unisource.app.ui.widgets.DownloadableMaterialItemCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MaterialsScreen(semester: String, onItemClick: (MaterialItem) -> Unit = {}) {
    val materials = MaterialsRepository.materialsBySemester[semester] ?: emptyList()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            val collapsedFraction = scrollBehavior.state.collapsedFraction
            val iconSize = 30.dp + (26.dp * (1f - collapsedFraction))

            LargeTopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        AsyncImage(
                            model = "https://cdn-icons-png.flaticon.com/512/8068/8068017.png",
                            contentDescription = null,
                            modifier = Modifier
                                .size(iconSize)
                                .clip(RoundedCornerShape(12.dp)),
                            contentScale = ContentScale.Fit
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = semester,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    scrolledContainerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
        ) {
            items(materials) { item ->
                DownloadableMaterialItemCard(item)
            }
        }
    }
}