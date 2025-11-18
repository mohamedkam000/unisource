package com.unisource.app.ui.screens.activities

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
import com.unisource.app.data.ActivitiesRepository
import com.unisource.app.model.Activity
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import com.unisource.app.ui.DateHeader
import com.unisource.app.ui.StyleCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivitiesScreen(
    onActivityClick: (String) -> Unit
) {
    val activities = ActivitiesRepository.activities
        .sortedByDescending { it.date }

    val grouped = activities.groupBy { it.date }
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
                        val scale = (1f - (0.5f * collapseFraction)).coerceAtLeast(0.75f)
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
                items(items) { activity ->
                    StyleCard(
                        activity = activity,
                        onClick = { onActivityClick(activity.title) }
                    )
                }
            }
        }
    }
}