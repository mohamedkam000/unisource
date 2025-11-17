package com.unisource.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.*
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.exitUntilCollapsedScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.draw.*
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.unisource.app.data.AnnouncementsRepository
import com.unisource.app.ui.widgets.AnnouncementCard
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Alignment

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnnouncementsScreen(
    onAnnouncementClick: (String) -> Unit
) {
    val announcements = AnnouncementsRepository.announcements
        .sortedByDescending { it.date }

    val grouped = announcements.groupBy { it.date }

    val scrollBehavior = exitUntilCollapsedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        val collapseFraction = scrollBehavior.state.collapsedFraction
                        val scale = 1f - (0.4f * collapseFraction)

                        AsyncImage(
                            model = "https://cdn-icons-png.flaticon.com/512/7653/7653930.png",
                            contentDescription = null,
                            modifier = Modifier
                                .size(40.dp)
                                .scale(scale)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            "Announcements",
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                    scrolledContainerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            grouped.forEach { (date, items) ->
                item {
                    Text(
                        LocalDate.parse(date, DateTimeFormatter.ISO_DATE).toString(),
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
                items(items) { announcement ->
                    AnnouncementCard(
                        announcement = announcement,
                        onClick = { onAnnouncementClick(announcement.title) }
                    )
                }
            }
        }
    }
}