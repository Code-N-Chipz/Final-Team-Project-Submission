package com.tc.learn.ui.screen.map

import coil.ImageLoader
import com.tc.learn.data.model.Teacher
import com.tc.learn.data.repository.TeacherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.ViewModel

@HiltViewModel
class MapScreenViewModel @Inject constructor(
    private val repository: TeacherRepository,
    val imageLoader: ImageLoader
) : ViewModel() {   // <-- Add this
    fun getTeacherById(id: String): Teacher? {
        return repository.getTeacherById(id)
    }
}
