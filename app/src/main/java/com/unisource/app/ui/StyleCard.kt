package com.unisource.app.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import coil.compose.AsyncImage
import coil.request.ImageRequest
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.layout.ContentScale

@Composable
private fun StyleCard(
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