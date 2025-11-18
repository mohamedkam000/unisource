package com.unisource.app.ui.screens.topics

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.platform.LocalLayoutDirection
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.unisource.app.data.TopicsRepository
import com.unisource.app.model.Topic
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopicsScreen(
    onTopicClick: (String) -> Unit
) {
    val topics = TopicsRepository.topics
        .sortedByDescending { it.date }

    val grouped = topics.groupBy { it.date }
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
                                .data("https://cdn-icons-gif.flaticon.com/15401/15401436.gif")
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
                            "Topics",
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.primary
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
                items(items.size) { index ->
                    val topic = items[index]
                
                    val isFirst = index == 0
                    val isLast = index == items.lastIndex
                    val isSingle = items.size == 1
                
                    GoogleStyleAnnouncementCard(
                        topic = topic,
                        onClick = { onTopicClick(topic.title) },
                        isFirst = isFirst,
                        isLast = isLast,
                        isSingle = isSingle
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
            .padding(top = 8.dp, bottom = 8.dp, start = 16.dp),
        contentAlignment = Alignment.TopStart
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
    topic: Topic,
    onClick: () -> Unit,
    isFirst: Boolean = false,
    isLast: Boolean = false,
    isSingle: Boolean = false
) {
    val shape = when {
        isSingle -> RoundedCornerShape(20.dp)
        isFirst -> RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
        isLast -> RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
        else -> RoundedCornerShape(0.dp)
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp),
        shape = shape,
        tonalElevation = if (isFirst) 2.dp else 0.dp,
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = if (isLast) 12.dp else 0.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(topic.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = topic.title,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Medium
                ),
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(horizontal = 20.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}