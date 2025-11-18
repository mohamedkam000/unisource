package com.unisource.app.data

data class ScheduleItem(
    val title: String,
    val imageUrl: String
)

object SchedulesRepository {

    val schedulesBySemester = mapOf(
        "Semester 1" to listOf(
            ScheduleItem("Academic", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSpBdGYYt8or3acxFW9Do4yyJbGTOXsWXgy8a4ipz5ijpO1UzDLrkgPGrn6&s=10"),
            ScheduleItem("Laboratory", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSDrOemcL5Y0VcPDnicKcJ6PtLuH8YEoiJd7S8mcEGe0g&s=10"),
            ScheduleItem("Exam", "https://neelain.edu.sd/img/news/exam/2.jpg")
        ),
        "Semester 2" to listOf(
            ScheduleItem("Academic", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSGIMmf8Tbg3jSjOuciDMfhJ40iLuzkCkX7Pw80Qb6Ugt1SuES-kOhFDno&s=10"),
            ScheduleItem("Laboratory", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS3Kv4S3rd5_yhN00BrqEJQsgaBekTaY_vt4t_8FSL2Kw&s"),
            ScheduleItem("Exam", "https://neelain.edu.sd/img/news/71/2.jpg")
        ),
        "Semester 3" to listOf(
            ScheduleItem("Academic", "https://cdn-icons-png.flaticon.com/512/7614/7614875.png"),
            ScheduleItem("Laboratory", "https://cdn-icons-png.flaticon.com/512/746/746960.png"),
            ScheduleItem("Exam", "https://cdn-icons-png.flaticon.com/256/8716/8716846.png")
        )
    )
}