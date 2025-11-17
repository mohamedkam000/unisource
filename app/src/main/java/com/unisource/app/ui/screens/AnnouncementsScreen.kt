package com.unisource.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.nestedScroll
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.exitUntilCollapsedScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.unisource.app.data.AnnouncementsRepository
import com.unisource.app.ui.widgets.AnnouncementCard
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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
                title = { Text("Announcements") },
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            grouped.forEach { (date, items) ->
                item {
                    Text(
                        LocalDate.parse(date, DateTimeFormatter.ISO_DATE).toString(),
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
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