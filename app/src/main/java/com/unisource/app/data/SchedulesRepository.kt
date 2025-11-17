package com.unisource.app.data

data class ScheduleItem(
    val title: String,
    val imageUrl: String
)

object SchedulesRepository {

    val schedulesBySemester = mapOf(
        "Semester 1" to listOf(
            ScheduleItem("Academic", "https://cdn-icons-png.flaticon.com/512/7614/7614875.png"),
            ScheduleItem("Laboratory", "https://cdn-icons-png.flaticon.com/512/746/746960.png"),
            ScheduleItem("Exam", "https://cdn-icons-png.flaticon.com/256/8716/8716846.png")
        ),
        "Semester 2" to listOf(
            ScheduleItem("Academic", "https://cdn-icons-png.flaticon.com/512/7614/7614875.png"),
            ScheduleItem("Laboratory", "https://cdn-icons-png.flaticon.com/512/746/746960.png"),
            ScheduleItem("Exam", "https://cdn-icons-png.flaticon.com/256/8716/8716846.png")
        ),
        "Semester 3" to listOf(
            ScheduleItem("Academic", "https://cdn-icons-png.flaticon.com/512/7614/7614875.png"),
            ScheduleItem("Laboratory", "https://cdn-icons-png.flaticon.com/512/746/746960.png"),
            ScheduleItem("Exam", "https://cdn-icons-png.flaticon.com/256/8716/8716846.png")
        )
    )
}