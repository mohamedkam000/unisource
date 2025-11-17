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
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.unisource.app.data.MaterialsRepository
import com.unisource.app.data.MaterialItem
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.text.style.TextOverflow

private val AngledRectShape = object : Shape {
    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline {
        return Outline.Generic(
            Path().apply {
                moveTo(0f, size.height)
                lineTo(0f, 0f)
                lineTo(size.width * 0.9f, 0f) 
                lineTo(size.width, size.height)
                close() 
            }
        )
    }
}

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
                    scrolledContainerColor = Color.Transparent 
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
                        .padding(top = 8.dp, bottom = 16.dp)
                        .alpha(1f - collapsedFraction * 0.5f), 
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
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clickable { onItemClick(item) },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(100.dp)
                    .fillMaxHeight()
                    .clip(AngledRectShape) 
            ) {
                AsyncImage(
                    model = item.imageUrl,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}