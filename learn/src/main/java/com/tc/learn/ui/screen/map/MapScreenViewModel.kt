package com.tc.learn.ui.screen.map

import androidx.lifecycle.ViewModel
import coil.ImageLoader
import com.tc.learn.data.model.Teacher
import com.tc.learn.data.repository.TeacherRepository
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class MapScreenViewModel @Inject constructor(
    private val repository: TeacherRepository,
    val imageLoader: ImageLoader
) {
    fun getTeacherById(id: String): Teacher? {
        return repository.getTeacherById(id)
    }
}