package com.tc.learn.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.tc.learn.data.model.Level
import com.tc.learn.data.model.Subject
import com.tc.learn.data.model.Teacher
import com.tc.learn.data.repository.TeacherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class TeacherViewModel @Inject constructor(
    private val repository: TeacherRepository
) : ViewModel() {

    private val _teachers = MutableStateFlow<List<Teacher>>(emptyList())
    val teachers: StateFlow<List<Teacher>> = _teachers

    init {
        loadTeachers()
    }

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
