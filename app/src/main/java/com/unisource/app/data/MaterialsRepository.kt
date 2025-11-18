package com.unisource.app.data

data class MaterialItem(
    val title: String,
    val url: String,
    val fileName: String,
    val imageUrl: String
)

object MaterialsRepository {

    val materialsBySemester = mapOf(
        "Semester 1" to listOf(
            MaterialItem("Physics", "https://raw.githubusercontent.com/mohamedkam000/unisource_sources/main/semester_1/physics.pdf", "Unisource/semester_1/physics_s1.pdf", "https://cdn-icons-png.flaticon.com/512/7614/7614875.png"),
            MaterialItem("Math", "https://raw.githubusercontent.com/mohamedkam000/unisource_sources/main/semester_1/math.pdf", "Unisource/semester_1/math_s1.pdf", "https://cdn-icons-png.flaticon.com/512/746/746960.png"),
            MaterialItem("Chemistry", "https://raw.githubusercontent.com/mohamedkam000/unisource_sources/main/semester_1/chemistry.pdf", "Unisource/semester_1/chem_s1.pdf", "https://cdn-icons-png.flaticon.com/256/8716/8716846.png")
        ),
        "Semester 2" to listOf(
            MaterialItem("Physics", "https://raw.githubusercontent.com/mohamedkam000/unisource_sources/main/semester_1/physics.pdf", "Unisource/semester_2/physics_s2.pdf", "https://cdn-icons-png.flaticon.com/512/295/295805.png"),
            MaterialItem("Math", "https://raw.githubusercontent.com/mohamedkam000/unisource_sources/main/semester_1/math.pdf", "Unisource/semester_2/math_s2.pdf", "https://cdn-icons-png.flaticon.com/512/4720/4720458.png"),
            MaterialItem("Electronics", "https://raw.githubusercontent.com/mohamedkam000/unisource_sources/main/semester_1/electronics.pdf", "Unisource/semester_2/electronics_s2.pdf", "https://cdn-icons-png.flaticon.com/512/5287/5287439.png")
        ),
        "Semester 3" to listOf(
            MaterialItem("AI", "https://example.com/ai.pdf", "unisource/semester_3/ai.pdf", ""),
            MaterialItem("Networks", "https://example.com/networks.pdf", "networks.pdf", ""),
            MaterialItem("Compiler Design", "https://example.com/compiler.pdf", "compiler.pdf", "")
        ),
        "Semester 4" to listOf(
            MaterialItem("AI", "https://example.com/ai.pdf", "ai.pdf", ""),
            MaterialItem("Networks", "https://example.com/networks.pdf", "networks.pdf", ""),
            MaterialItem("Compiler Design", "https://example.com/compiler.pdf", "compiler.pdf", "")
        ),
        "Semester 5" to listOf(
            MaterialItem("Physics", "https://example.com/physics1.pdf", "physics1.pdf", "https://cdn-icons-png.flaticon.com/512/306/306335.png"),
            MaterialItem("Math", "https://example.com/math1.pdf", "math1.pdf", ""),
            MaterialItem("Chemistry", "https://example.com/chem1.pdf", "chem1.pdf", "https://cdn-icons-png.flaticon.com/512/8540/8540907.png")
        ),
        "Semester 6" to listOf(
            MaterialItem("Physics", "https://example.com/physics2.pdf", "physics2.pdf", ""),
            MaterialItem("Math", "https://example.com/math2.pdf", "math2.pdf", ""),
            MaterialItem("Electronics", "https://example.com/electronics.pdf", "electronics.pdf", "")
        ),
        "Semester 7" to listOf(
            MaterialItem("AI", "https://example.com/ai.pdf", "ai.pdf", ""),
            MaterialItem("Networks", "https://example.com/networks.pdf", "networks.pdf", ""),
            MaterialItem("Compiler Design", "https://example.com/compiler.pdf", "compiler.pdf", "")
        ),
        "Semester 8" to listOf(
            MaterialItem("AI", "https://example.com/ai.pdf", "ai.pdf", ""),
            MaterialItem("Networks", "https://example.com/networks.pdf", "networks.pdf", ""),
            MaterialItem("Compiler Design", "https://example.com/compiler.pdf", "compiler.pdf", "")
        ),
        "Semester 9" to listOf(
            MaterialItem("AI", "https://example.com/ai.pdf", "ai.pdf", ""),
            MaterialItem("Networks", "https://example.com/networks.pdf", "networks.pdf", ""),
            MaterialItem("Compiler Design", "https://example.com/compiler.pdf", "compiler.pdf", "")
        ),
        "Semester 10" to listOf(
            MaterialItem("AI", "https://example.com/ai.pdf", "ai.pdf", ""),
            MaterialItem("Networks", "https://example.com/networks.pdf", "networks.pdf", ""),
            MaterialItem("Compiler Design", "https://example.com/compiler.pdf", "compiler.pdf", "")
        )
    )
}