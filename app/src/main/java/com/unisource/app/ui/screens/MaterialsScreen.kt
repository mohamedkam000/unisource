package com.unisource.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.exitUntilCollapsedScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.unisource.app.data.MaterialsRepository
import com.unisource.app.data.MaterialItem
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.lerp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MaterialsScreen(semester: String, onItemClick: (MaterialItem) -> Unit = {}) {
    val materials = MaterialsRepository.materialsBySemester[semester] ?: emptyList()
    val scrollBehavior = exitUntilCollapsedScrollBehavior()
    val collapsedFraction = scrollBehavior.state.collapsedFraction

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                title = {
                    val alphaValue = collapsedFraction
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.offset(y = lerp(20.dp, 0.dp, alphaValue)) 
                    ) {
                        AsyncImage(
                            model = "https://cdn-icons-png.flaticon.com/512/8068/8068017.png",
                            contentDescription = null,
                            modifier = Modifier
                                .size(40.dp * alphaValue) 
                                .clip(RoundedCornerShape(10.dp)),
                            contentScale = ContentScale.Fit
                        )
                        Spacer(Modifier.width(8.dp * alphaValue))
                        Text(
                            "$semester Materials",
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.alpha(alphaValue)
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color.Transparent, 
                    scrolledContainerColor = MaterialTheme.colorScheme.surface 
                )
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .alpha(1f - collapsedFraction * 1.5f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val scale = lerp(1.5f, 1f, collapsedFraction)
                    val imageSize = lerp(120.dp, 50.dp, collapsedFraction) 

                    AsyncImage(
                        model = "https://cdn-icons-png.flaticon.com/512/8068/8068017.png",
                        contentDescription = null,
                        modifier = Modifier
                            .size(imageSize)
                            .scale(scale)
                            .clip(RoundedCornerShape(24.dp)),
                        contentScale = ContentScale.Fit
                    )
                    Spacer(Modifier.height(16.dp))
                    Text(
                        "$semester Materials",
                        style = MaterialTheme.typography.headlineMedium,
                        textAlign = TextAlign.Center
                    )
                }
            }

            items(materials) { item ->
                MaterialItemCard(item = item, onItemClick = onItemClick)
            }
        }
    }
}

@Composable
private fun MaterialItemCard(item: MaterialItem, onItemClick: (MaterialItem) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clip(RoundedCornerShape(20.dp))
            .clickable { onItemClick(item) }
    ) {
        AsyncImage(
            model = item.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(20.dp)),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            MaterialTheme.colorScheme.surface.copy(alpha = 0.85f)
                        )
                    )
                )
                .padding(12.dp)
        ) {
            Text(
                text = item.title,
                style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.onSurface
                ),
                maxLines = 2
            )
        }
    }
}