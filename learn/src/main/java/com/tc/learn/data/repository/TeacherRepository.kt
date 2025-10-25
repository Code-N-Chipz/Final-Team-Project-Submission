package com.tc.learn.data.repository

import com.tc.learn.data.model.Level
import com.tc.learn.data.model.Subject
import com.tc.learn.data.model.Teacher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TeacherRepository @Inject constructor() {
    fun getTeachers(): List<Teacher> {
        return listOf(
            Teacher(
                id = 1,
                name = "Alice Smith",
                listOf<Level>(Level.ELEMENTARY),
                subjects = listOf<Subject>(Subject.ENGLISH, Subject.MATHS),
                location = "Test location",
                price = 20.00,
                rating = 4.4
            ),
            Teacher(
                id = 2,
                name = "Boris Johnson",
                listOf<Level>(Level.ELEMENTARY),
                subjects = listOf<Subject>(Subject.FRENCH, Subject.SCIENCE),
                location = "Test location",
                price = 22.00,
                rating = 4.5
            )
        )
    }
    // Filter by ANY of multiple levels
    fun getTeachersByLevels(levels: List<Level>): List<Teacher> {
        if (levels.isEmpty()) return getTeachers()
        return getTeachers().filter { teacher ->
            teacher.levels.any { it in levels }
        }
    }

    // Filter by ANY of multiple subjects
    fun getTeachersBySubjects(subjects: List<Subject>): List<Teacher> {
        if (subjects.isEmpty()) return getTeachers()
        return getTeachers().filter { teacher ->
            teacher.subjects.any { it in subjects }
        }
    }

    // Search by name (case-insensitive)
    fun searchTeachersByName(query: String): List<Teacher> {
        if (query.isBlank()) return getTeachers()
        return getTeachers().filter { teacher ->
            teacher.name.contains(query, ignoreCase = true)
        }
    }

    // Search by location (case-insensitive)
    //Needs to be updated to maps inclusion

    fun searchTeachersByLocation(query: String): List<Teacher> {
        if (query.isBlank()) return getTeachers()
        return getTeachers().filter { teacher ->
            teacher.location.contains(query, ignoreCase = true)
        }
    }

    // Combined advanced filter
    fun filterTeachers(
        levels: List<Level> = emptyList(),
        subjects: List<Subject> = emptyList(),
        nameQuery: String = "",
        locationQuery: String = ""
    ): List<Teacher> {
        return getTeachers().filter { teacher ->
            val matchesLevel = levels.isEmpty() || teacher.levels.any { it in levels }
            val matchesSubject = subjects.isEmpty() || teacher.subjects.any { it in subjects }
            val matchesName = nameQuery.isBlank() || teacher.name.contains(nameQuery, ignoreCase = true)
            val matchesLocation = locationQuery.isBlank() || teacher.location.contains(locationQuery, ignoreCase = true)
            matchesLevel && matchesSubject && matchesName && matchesLocation
        }
    }
}