package com.tc.learn.ui.screen.start

import androidx.lifecycle.ViewModel
import coil.ImageLoader
import com.tc.learn.ui.viewmodel.TeacherViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(
    val imageLoader: ImageLoader,
) : ViewModel() {
    // You can expose it directly or wrap some helper functions


}
