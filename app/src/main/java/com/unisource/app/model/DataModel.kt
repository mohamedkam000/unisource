package com.unisource.app.model

import com.unisource.app.ui.CardContent

data class Announcement(
    override val title: String,
    val content: String,
    override val imageUrl: String,
    val date: String
) : CardContent

data class Topic(
    override val title: String,
    val content: String,
    override val imageUrl: String,
    val date: String
) : CardContent

data class Activity(
    override val title: String,
    val content: String,
    override val imageUrl: String,
    val date: String
) : CardContent