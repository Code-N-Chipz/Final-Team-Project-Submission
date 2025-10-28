package com.tc.babysitter.addchild

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

data class ChildData(
    val name: String = "",
    val sex: String = "",
    val age: String = "",
    val photoUri: Uri? = null
)

class ChildViewModel : ViewModel() {
    var childData by mutableStateOf(ChildData())
        private set

    fun updateName(name: String) {
        childData = childData.copy(name = name)
    }

    fun updateSex(sex: String) {
        childData = childData.copy(sex = sex)
    }

    fun updateAge(age: String) {
        childData = childData.copy(age = age)
    }

    fun updatePhoto(uri: Uri?) {
        childData = childData.copy(photoUri = uri)
    }
}
