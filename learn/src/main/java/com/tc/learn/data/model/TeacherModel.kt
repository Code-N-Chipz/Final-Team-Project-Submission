package com.tc.learn.data.model

data class Teacher(
    val id: Int,
    val name: String,
    val levels: List<Level>,
    val subjects: List<Subject>,
    val location: String,           //To become maps data
    val price: Double,              //An hour
    val rating: Double              //Out of 5 stars
)

enum class Level { ELEMENTARY, MIDDLE_SCHOOL, HIGH_SCHOOL, COLLEGE, UNIVERSITY }
enum class Subject { ENGLISH, FRENCH, SCIENCE, MATHS, HISTORY, ART, PHYSICAL_EDUCATION }
