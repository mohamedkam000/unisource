package com.unisource.app.data

data class MaterialItem(
    val title: String,
    val url: String,
    val fileName: String
)

object MaterialsRepository {

    val materialsBySemester = mapOf(
        "Semester 1" to listOf(
            MaterialItem("Physics", "https://raw.githubusercontent.com/mohamedkam000/unisource_sources/main/semester_1/physics.pdf", "physics_s1.pdf"),
            MaterialItem("Math", "https://raw.githubusercontent.com/mohamedkam000/unisource_sources/main/semester_1/math.pdf", "math_s1.pdf"),
            MaterialItem("Chemistry", "https://raw.githubusercontent.com/mohamedkam000/unisource_sources/main/semester_1/chemistry.pdf", "chem_s1.pdf")
        ),
        "Semester 2" to listOf(
            MaterialItem("Physics", "https://raw.githubusercontent.com/mohamedkam000/unisource_sources/main/semester_1/physics.pdf", "physics_s2.pdf"),
            MaterialItem("Math", "https://raw.githubusercontent.com/mohamedkam000/unisource_sources/main/semester_1/math.pdf", "math_s2.pdf"),
            MaterialItem("Electronics", "https://raw.githubusercontent.com/mohamedkam000/unisource_sources/main/semester_1/electronics.pdf", "electronics_s2.pdf")
        ),
        "Semester 3" to listOf(
            MaterialItem("AI", "https://example.com/ai.pdf", "ai.pdf"),
            MaterialItem("Networks", "https://example.com/networks.pdf", "networks.pdf"),
            MaterialItem("Compiler Design", "https://example.com/compiler.pdf", "compiler.pdf")
        ),
        "Semester 4" to listOf(
            MaterialItem("AI", "https://example.com/ai.pdf", "ai.pdf"),
            MaterialItem("Networks", "https://example.com/networks.pdf", "networks.pdf"),
            MaterialItem("Compiler Design", "https://example.com/compiler.pdf", "compiler.pdf")
        ),
        "Semester 5" to listOf(
            MaterialItem("Physics", "https://example.com/physics1.pdf", "physics1.pdf"),
            MaterialItem("Math", "https://example.com/math1.pdf", "math1.pdf"),
            MaterialItem("Chemistry", "https://example.com/chem1.pdf", "chem1.pdf")
        ),
        "Semester 6" to listOf(
            MaterialItem("Physics", "https://example.com/physics2.pdf", "physics2.pdf"),
            MaterialItem("Math", "https://example.com/math2.pdf", "math2.pdf"),
            MaterialItem("Electronics", "https://example.com/electronics.pdf", "electronics.pdf")
        ),
        "Semester 7" to listOf(
            MaterialItem("AI", "https://example.com/ai.pdf", "ai.pdf"),
            MaterialItem("Networks", "https://example.com/networks.pdf", "networks.pdf"),
            MaterialItem("Compiler Design", "https://example.com/compiler.pdf", "compiler.pdf")
        ),
        "Semester 8" to listOf(
            MaterialItem("AI", "https://example.com/ai.pdf", "ai.pdf"),
            MaterialItem("Networks", "https://example.com/networks.pdf", "networks.pdf"),
            MaterialItem("Compiler Design", "https://example.com/compiler.pdf", "compiler.pdf")
        ),
        "Semester 9" to listOf(
            MaterialItem("AI", "https://example.com/ai.pdf", "ai.pdf"),
            MaterialItem("Networks", "https://example.com/networks.pdf", "networks.pdf"),
            MaterialItem("Compiler Design", "https://example.com/compiler.pdf", "compiler.pdf")
        ),
        "Semester 10" to listOf(
            MaterialItem("AI", "https://example.com/ai.pdf", "ai.pdf"),
            MaterialItem("Networks", "https://example.com/networks.pdf", "networks.pdf"),
            MaterialItem("Compiler Design", "https://example.com/compiler.pdf", "compiler.pdf")
        )
    )
}