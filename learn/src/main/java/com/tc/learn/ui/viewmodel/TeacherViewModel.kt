package com.tc.learn.ui.viewmodel

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

    init {
        loadTeachers()
    }
//    fun getTeacherById(id: String?): Teacher? = repository.getTeacherById(id)
    fun getTeacherById(id: String): Teacher? {
        return repository.getTeacherById(id)
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
