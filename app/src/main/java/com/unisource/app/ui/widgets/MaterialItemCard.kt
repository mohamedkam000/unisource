package com.unisource.app.ui.widgets

import android.content.Context
import android.os.Environment
import android.app.DownloadManager
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import java.io.File
import com.unisource.app.data.*

@Composable
fun DownloadableMaterialItemCard(
    item: MaterialItem,
    context: Context = LocalContext.current
) {
    var isDownloading by remember { mutableStateOf(false) }
    var isDownloaded by remember { mutableStateOf(checkIfDownloaded(context, item.fileName)) }

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .height(120.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = item.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(76.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Text(
                item.title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(1f)
            )

            when {
                isDownloaded -> Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )

                isDownloading -> CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    strokeWidth = 2.dp
                )

                else -> IconButton(
                    onClick = {
                        isDownloading = true
                        startDownload(
                            context = context,
                            url = item.url,
                            fileName = item.fileName,
                            onComplete = {
                                isDownloading = false
                                isDownloaded = true
                            }
                        )
                    }
                ) {
                    Icon(Icons.Default.Download, contentDescription = null)
                }
            }
        }
    }
}

fun startDownload(
    context: Context,
    url: String,
    fileName: String,
    onComplete: () -> Unit
) {
    val request = DownloadManager.Request(Uri.parse(url))
        .setTitle(fileName)
        .setDestinationInExternalPublicDir(
            Environment.DIRECTORY_DOWNLOADS,
            fileName
        )
        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)

    val dm = context.getSystemService(DownloadManager::class.java)
    dm.enqueue(request)
    onComplete()
}

fun checkIfDownloaded(context: Context, fileName: String): Boolean {
    val file = File(
        Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_DOWNLOADS
        ),
        fileName
    )
    return file.exists()
}