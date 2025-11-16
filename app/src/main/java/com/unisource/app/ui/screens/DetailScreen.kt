package com.unisource.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(title: String, imageUrl: String) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(title) })
        }
    ) { p ->
        Column(
            modifier = Modifier
                .padding(p)
                .fillMaxSize()
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(Modifier.height(20.dp))

            Text(
                text = "Details about $title",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}