package com.unisource.app.model

import com.unisource.app.ui.CardContent

data class Announcement(
    val title: String,
    val content: String,
    val imageUrl: String,
    val date: String
) : CardContent

data class Topic(
    val title: String,
    val content: String,
    val imageUrl: String,
    val date: String
) : CardContent

data class Activity(
    val title: String,
    val content: String,
    val imageUrl: String,
    val date: String
) : CardContent