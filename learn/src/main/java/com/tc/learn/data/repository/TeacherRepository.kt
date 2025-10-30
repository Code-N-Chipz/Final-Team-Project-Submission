package com.tc.learn.data.repository

import com.tc.learn.data.model.Level
import com.tc.learn.data.model.Subject
import com.tc.learn.data.model.Teacher
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.pow

@Singleton
class TeacherRepository @Inject constructor() {
    fun getTeachers(): List<Teacher> {
        return listOf(Teacher(id = "1", name = "Alice Smith", levels = listOf(Level.ELEMENTARY), subjects = listOf(Subject.ENGLISH, Subject.MATHS), price = 20.00, _rating = 4.4, latitude = -26.2030, longitude = 28.0465), Teacher(id = "2", name = "Boris Johnson", levels = listOf(Level.ELEMENTARY), subjects = listOf(Subject.FRENCH, Subject.SCIENCE), price = 22.00, _rating = 4.5, imageUrl = "https://s.gravatar.com/avatar/62a968f41c1feb83fd1cd142e7c043f3?s=200", latitude = -26.2030, longitude = 28.0465), Teacher(id = "3", name = "Catherine Lee", levels = listOf(Level.MIDDLE_SCHOOL), subjects = listOf(Subject.MATHS, Subject.SCIENCE), price = 25.00, _rating = 4.7, latitude = -26.2030, longitude = 28.0465), Teacher(id = "4", name = "David Kim", levels = listOf(Level.HIGH_SCHOOL), subjects = listOf(Subject.ENGLISH, Subject.HISTORY), price = 30.00, _rating = 4.6, latitude = -26.2030, longitude = 28.0465), Teacher(id = "5", name = "Emily Zhang", levels = listOf(Level.HIGH_SCHOOL), subjects = listOf(Subject.SCIENCE, Subject.MATHS), price = 28.00, _rating = 4.8, latitude = -26.2030, longitude = 28.0465), Teacher(id = "6", name = "Frank Thompson", levels = listOf(Level.MIDDLE_SCHOOL, Level.HIGH_SCHOOL), subjects = listOf(Subject.ENGLISH, Subject.MATHS), price = 26.00, _rating = 4.3, latitude = -26.2030, longitude = 28.0465), Teacher(id = "7", name = "Grace Liu", levels = listOf(Level.ELEMENTARY, Level.MIDDLE_SCHOOL), subjects = listOf(Subject.FRENCH, Subject.MATHS), price = 21.00, _rating = 4.5, latitude = -26.2030, longitude = 28.0465))
    }
    // Filter by ANY of multiple levels
    fun getTeachersByLevels(levels: List<Level>): List<Teacher> {
        if (levels.isEmpty()) return getTeachers()
        return getTeachers().filter { teacher ->
            teacher.levels.any { it in levels }
        }
    }

    // Filter teachers by a single level and a single subject
    fun filterTeachersByLevelAndSubject(
        level: Level? = null,
        subject: Subject? = null
    ): List<Teacher> {
        return getTeachers().filter { teacher ->
            val matchesLevel = level == null || teacher.levels.contains(level)
            val matchesSubject = subject == null || teacher.subjects.contains(subject)
            matchesLevel && matchesSubject
        }
    }
    // Filter teachers by level, subject, and name
    fun filterTeachersByLevelSubjectAndName(
        level: Level? = null,
        subject: Subject? = null,
        nameQuery: String = ""
    ): List<Teacher> {
        return getTeachers().filter { teacher ->
            val matchesLevel = level == null || teacher.levels.contains(level)
            val matchesSubject = subject == null || teacher.subjects.contains(subject)
            val matchesName = nameQuery.isBlank() || teacher.name.contains(nameQuery, ignoreCase = true)
            matchesLevel && matchesSubject && matchesName
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

//    fun searchTeachersByLocation(latitude: Double, longitude: Double, radiusKm: Double): List<Teacher> {
//        return null
////        getTeachers().filter { teacher ->
////                val distance = haversine(teacher.latitude, teacher.longitude, latitude, longitude)
////                distance <= radiusKm
////            } ?: false
//    }

    // Haversine formula to calculate distance in km
    fun haversine(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val R = 6371.0 // Earth radius in km
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = Math.sin(dLat / 2).pow(2.0) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                Math.sin(dLon / 2).pow(2.0)
        val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
        return R * c
    }


    // Combined advanced filter
    fun filterTeachers(
        levels: List<Level> = emptyList(),
        subjects: List<Subject> = emptyList(),
        nameQuery: String = "",
        latitude: Double? = null,
        longitude: Double? = null,
        radiusKm: Double = 0.0,
        locationQuery: String
    ): List<Teacher> {
        return getTeachers().filter { teacher ->
            val matchesLevel = levels.isEmpty() || teacher.levels.any { it in levels }
            val matchesSubject = subjects.isEmpty() || teacher.subjects.any { it in subjects }
            val matchesName = nameQuery.isBlank() || teacher.name.contains(nameQuery, ignoreCase = true)
//            val matchesLocation = if (latitude != null && longitude != null && radiusKm > 0) {
//                teacher.location?.let { loc ->
//                    haversine(loc.latitude, loc.longitude, latitude, longitude) <= radiusKm
//                } ?: false
//            } else true
            matchesLevel && matchesSubject && matchesName
        }
    }

    fun getTeacherById(id: String?): Teacher? {
        return getTeachers().firstOrNull { it.id == id }
    }

    // Get all unique levels from teachers
    fun getAllLevels(): List<Level> {
        return getTeachers()
            .flatMap { it.levels }  // combine all levels
            .distinct()             // remove duplicates
    }

    // Get all unique subjects from teachers
    fun getAllSubjects(): List<Subject> {
        return getTeachers()
            .flatMap { it.subjects }  // combine all subjects
            .distinct()               // remove duplicates
    }

}