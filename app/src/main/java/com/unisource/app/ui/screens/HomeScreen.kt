package com.unisource.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.exitUntilCollapsedScrollBehavior
import androidx.compose.material3.TopAppBarDefaults.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import com.unisource.app.model.AppItem
import com.unisource.app.ui.widgets.HorizontalCard
import com.unisource.app.ui.widgets.VerticalItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onItemClick: (String, String) -> Unit
) {
/*    val horizontalCards = listOf(
        AppItem("Announcements", "https://cdn-icons-png.flaticon.com/512/7653/7653930.png"),
        AppItem("Activities", "https://cdn-icons-png.flaticon.com/512/18120/18120765.png"),
        AppItem("Topics", "https://cdn-icons-png.flaticon.com/512/9431/9431885.png"),
        AppItem("Discussion", "https://cdn-icons-png.flaticon.com/512/17262/17262972.png"),
    )*/
    
    val horizontalCards = listOf(
        AppItem("Announcements", "https://cdn-icons-gif.flaticon.com/15747/15747228.gif"),
        AppItem("Activities", "https://cdn-icons-gif.flaticon.com/14164/14164931.gif"),
        AppItem("Topics", "https://cdn-icons-gif.flaticon.com/15401/15401436.gif"),
        AppItem("Discussion", "https://cdn-icons-gif.flaticon.com/17556/17556488.gif"),
    )

    val categories = listOf(
        AppItem("Books", "https://cdn-icons-png.flaticon.com/512/5402/5402751.png"),
        AppItem("Schedule", "https://cdn-icons-png.flaticon.com/512/3652/3652191.png"),
        AppItem("Exams", "https://cdn-icons-png.flaticon.com/512/9043/9043010.png"),
        AppItem("Assignments", "https://cdn-icons-png.flaticon.com/512/11265/11265088.png"),
        AppItem("Courses", "https://cdn-icons-png.flaticon.com/512/10748/10748346.png"),
    )

    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = exitUntilCollapsedScrollBehavior(state = topAppBarState)

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        val collapseFraction = scrollBehavior.state.collapsedFraction
                        val targetScale = 1f - (0.45f * collapseFraction)
                        val targetAlpha = 1f - collapseFraction
                        val scale by animateFloatAsState(
                            targetValue = targetScale,
                            animationSpec = tween(durationMillis = 300),
                            label = "LogoScale"
                        )
                        val alpha by animateFloatAsState(
                            targetValue = targetAlpha,
                            animationSpec = tween(durationMillis = 300),
                            label = "LogoAlpha"
                        )

                        AsyncImage(
                            model = "https://raw.githubusercontent.com/mohamedkam000/unisource/main/app/src/main/res/drawable/logo.png",
                            contentDescription = null,
                            modifier = Modifier
                                .size(48.dp)
                                .scale(scale)
                                .alpha(alpha),
                            contentScale = androidx.compose.ui.layout.ContentScale.Fit
                        )

                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            "Unisource",
                            style = MaterialTheme.typography.titleLarge.copy(
                                color = MaterialTheme.colorScheme.primary
                            ),
                            modifier = Modifier.alpha(alpha)
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                    scrolledContainerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.surfaceVariant
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentPadding = PaddingValues(
                top = 16.dp,
                bottom = 24.dp
            )
        ) {
            item {
                Text(
                    "Featured",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(16.dp)
                )

                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(horizontalCards) { card ->
                        HorizontalCard(card) {
                            onItemClick(card.title, card.imageUrl)
                        }
                    }
                }
                Spacer(Modifier.height(32.dp))
            }

            item {
                Text(
                    "Categories",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(16.dp)
                )
            }

            items(categories) { cat ->
                VerticalItem(
                    item = cat,
                    onClick = { onItemClick(cat.title, cat.imageUrl) }
                )
            }
        }
    }
}