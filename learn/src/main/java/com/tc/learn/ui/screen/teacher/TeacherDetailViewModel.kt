package com.tc.learn.ui.screen.teacher

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tc.learn.data.model.Teacher
import com.tc.learn.data.repository.TeacherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeacherDetailViewModel @Inject constructor(
    private val repository: TeacherRepository
) : ViewModel() {

    private val _teacher = MutableStateFlow<Teacher?>(null)
    val teacher: StateFlow<Teacher?> = _teacher

    fun loadTeacher(teacherId: String) {
        viewModelScope.launch {
            _teacher.value = repository.getTeacherById(teacherId)
        }
    }
    fun getTeacherById(id: String?): Teacher? = repository.getTeacherById(id)

}
