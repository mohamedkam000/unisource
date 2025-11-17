package com.unisource.app.ui

sealed class NavRoute(val route: String) {

    object Home : NavRoute("home")
    object Books : NavRoute("books")
    object Exams : NavRoute("exams")
    object Announcements : NavRoute("announcements")
    object Activities : NavRoute("activities")
    object Topics : NavRoute("topics")

    object Detail : NavRoute("detail/{title}/{url}") {
        fun go(title: String, url: String) =
            "detail/${Uri.encode(title)}/${Uri.encode(url)}"
    }

    object Materials : NavRoute("materials/{semester}") {
        fun go(semester: String) = "materials/$semester"
    }

    object AnnouncementDetail :
        NavRoute("announcement_detail/{title}") {
        fun go(title: String) =
            "announcement_detail/${Uri.encode(title)}"
    }

    object Schedule : NavRoute("schedule")
    object Semesters : NavRoute("schedule_semesters")
    object ScheduleOptions : NavRoute("schedule_options/{semester}") {
        fun go(semester: String) = "schedule_options/$semester"
    }
    object FullImage : NavRoute("full_image/{url}") {
        fun go(url: String) = "full_image/${Uri.encode(url)}"
    }
}