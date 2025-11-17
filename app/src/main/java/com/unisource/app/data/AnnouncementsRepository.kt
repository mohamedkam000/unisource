package com.unisource.app.data

import com.unisource.app.model.Announcement

object AnnouncementsRepository {
    val announcements = listOf(
        Announcement(
            title = "Campus Festival",
            subtitle = "Join the biggest event of the year!",
            content = "The annual campus festival will feature concerts, games, and food stalls...",
            imageUrl = "https://picsum.photos/id/1018/400/300",
            date = "2025-11-17"
        ),
        Announcement(
            title = "New Library Hours",
            subtitle = "Library now opens earlier",
            content = "Starting next week, the library will open from 7:00 AM to 10:00 PM...",
            imageUrl = "https://picsum.photos/id/1019/400/300",
            date = "2025-11-17"
        ),
        Announcement(
            title = "Exam Schedule Released",
            subtitle = "Check your semester dates",
            content = "The exam timetable is now available online. Make sure to check your courses...",
            imageUrl = "https://picsum.photos/id/1020/400/300",
            date = "2025-11-16"
        )
    )
}