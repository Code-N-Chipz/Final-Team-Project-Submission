package com.tc.learn.data.model

data class Teacher(
    val id: String = "0",
    val name: String = "",
    val levels: List<Level> = emptyList(),
    val subjects: List<Subject> = emptyList(),
    val location: Location?,           //To become maps data
    val address: String = "",
    val price: Double = 0.00,              //An hour

    private val _rating: Double = 0.0,
    val imageUrl: String = "https://picsum.photos/100",
) {
    val rating: Double get() = _rating.coerceIn(0.0, 5.0)
    val validatedPrice: Double get() = price.coerceAtLeast(0.0)

    val hasLocation: Boolean get() = location != null
    val levelNames: String get() = if (levels.isEmpty()) "N/A" else levels.joinToString { it.displayName }
    val subjectNames: String get() = if (subjects.isEmpty()) "N/A" else subjects.joinToString { it.displayName }
}

//enum class Level { ELEMENTARY, MIDDLE_SCHOOL, HIGH_SCHOOL, COLLEGE, UNIVERSITY }
enum class Level(val displayName: String) {
    ELEMENTARY("Elementary"),
    MIDDLE_SCHOOL("Middle School"),
    HIGH_SCHOOL("High School"),
    COLLEGE("College"),
    UNIVERSITY("University")
}


//enum class Subject { ENGLISH, FRENCH, SCIENCE, MATHS, HISTORY, ART, PHYSICAL_EDUCATION }

enum class Subject(val displayName: String) {
    ENGLISH("English"),
    FRENCH("French"),
    SCIENCE("Science"),
    MATHS("Maths"),
    HISTORY("History"),
    ART("Art"),
    PHYSICAL_EDUCATION("Physical Education"),

}

data class Location(val latitude: Double, val longitude: Double)

