package com.unisource.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.unisource.app.data.AnnouncementsRepository
import com.unisource.app.model.Announcement
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnnouncementsScreen(
    onAnnouncementClick: (String) -> Unit
) {
    val announcements = AnnouncementsRepository.announcements
        .sortedByDescending { it.date }

    val grouped = announcements.groupBy { it.date }
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        topBar = {
            LargeTopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(end = 16.dp)
                    ) {
                        val collapseFraction = scrollBehavior.state.collapsedFraction
                        val imageSize = 64.dp
                        val scale = 1f - (0.5f * collapseFraction)
                        val alpha = 1f - (0.2f * collapseFraction)

                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data("https://cdn-icons-png.flaticon.com/512/7653/7653930.png")
                                .crossfade(true)
                                .build(),
                            contentDescription = null,
                            modifier = Modifier
                                .size(imageSize)
                                .scale(scale)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primaryContainer),
                            contentScale = ContentScale.Crop,
                            alpha = alpha
                        )

                        Spacer(modifier = Modifier.width(16.dp))
                        
                        Text(
                            "Feed",
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer,
                    scrolledContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh
                )
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentPadding = PaddingValues(bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            grouped.forEach { (date, items) ->
                item {
                    DateHeader(date)
                }
                items(items) { announcement ->
                    GoogleStyleAnnouncementCard(
                        announcement = announcement,
                        onClick = { onAnnouncementClick(announcement.title) }
                    )
                }
            }
        }
    }
}

@Composable
private fun DateHeader(dateString: String) {
    val parsedDate = LocalDate.parse(dateString)
    val formattedDate = parsedDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            shape = CircleShape,
            color = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        ) {
            Text(
                text = formattedDate,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}

@Composable
private fun GoogleStyleAnnouncementCard(
    announcement: Announcement,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .clickable(onClick = onClick)
    ) {
        Card(
            shape = RoundedCornerShape(28.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(announcement.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = announcement.title,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Medium
            ),
            color = MaterialTheme.colorScheme.onSurface
        )
        
        Spacer(modifier = Modifier.height(4.dp))

/*        Text(
            text = announcement.subtitle,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 2
        )*/
    }
}