package com.tc.learn.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import com.tc.learn.data.model.Level
import com.tc.learn.data.model.Subject
import com.tc.learn.data.model.Teacher
import com.tc.learn.data.repository.TeacherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeacherViewModel @Inject constructor(
    private val repository: TeacherRepository,
    val imageLoader: ImageLoader
) : ViewModel() {

    private val _teachers = MutableStateFlow<List<Teacher>>(emptyList())
    val teachers: StateFlow<List<Teacher>> = _teachers

    private val _selectedTeacher = MutableStateFlow<Teacher?>(null)
    val selectedTeacher: StateFlow<Teacher?> = _selectedTeacher

    // --- Filters ---
    var sortOption by mutableStateOf("Recommended")
    var maxPrice by mutableStateOf(500f)
    var minStar by mutableStateOf(0f)

    // --- Levels & Subjects ---
    private val _levels = MutableStateFlow<List<Level>>(emptyList())
    val levels: MutableStateFlow<List<Level>> = _levels

    private val _subjects = MutableStateFlow<List<Subject>>(emptyList())
    val subjects: MutableStateFlow<List<Subject>> = _subjects

    init {
        loadTeachers()
        loadLevelsAndSubjects()
    }

    fun filterTeachersByLevelAndSubject(selectedLevel: Level?, selectedSubject: Subject?) {
        _teachers.value = repository.filterTeachersByLevelAndSubject(
            level = selectedLevel,
            subject = selectedSubject
        )
    }

    fun filterTeachersByLevelSubjectAndName(
        selectedLevelName: String?,
        selectedSubjectName: String?,
        nameQuery: String
    ) {
        val levelObj = _levels.value.firstOrNull { it.name == selectedLevelName }
        val subjectObj = _subjects.value.firstOrNull { it.name == selectedSubjectName }

        _teachers.value = repository.filterTeachersByLevelSubjectAndName(
            level = levelObj,
            subject = subjectObj,
            nameQuery = nameQuery
        )
    }



    private fun loadLevelsAndSubjects() {
        _levels.value = repository.getAllLevels()
        _subjects.value = repository.getAllSubjects()
    }
//    fun getTeacherById(id: String?): Teacher? = repository.getTeacherById(id)
    fun getTeacherById(id: String): Teacher? {
        return repository.getTeacherById(id)
    }

    fun applyFilterPageFilters(
        sort: String,
        price: Float,
        star: Float
    ) {
        sortOption = sort
        maxPrice = price
        minStar = star

        viewModelScope.launch {
            val filtered = repository.getTeachers()
                .filter { it.price <= price && it.rating >= star }
                .let { list ->
                    when (sort) {
                        "Price: Low to High" -> list.sortedBy { it.price }
                        "Price: High to Low" -> list.sortedByDescending { it.price }
                        else -> list
                    }
                }
            _teachers.value = filtered
        }
    }
    fun getTeacherImageUrlById(id: String?): String? = repository.getTeacherById(id)?.imageUrl

    fun loadTeachers() {
        _teachers.value = repository.getTeachers()
    }
    fun filterTeachers(
        levels: List<Level> = emptyList(),
        subjects: List<Subject> = emptyList(),
        nameQuery: String = "",
        locationQuery: String = ""
    ) {
        _teachers.value = repository.filterTeachers(
            levels = levels,
            subjects = subjects,
            nameQuery = nameQuery,
            locationQuery = locationQuery
        )
    }

}
