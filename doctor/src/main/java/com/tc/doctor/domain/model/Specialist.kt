package com.tc.doctor.domain.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.input.pointer.PointerId
import java.time.LocalTime

data class Specialist(
    val id: Int,
    val name: String,
    val address: String,
    val isAvailable: Boolean,
    val workHours: ClosedRange<LocalTime> = LocalTime.of(8, 0)..LocalTime.of(20, 0),
    val stars: Float,
    @DrawableRes val imageRes: Int,
    val specialty: String,
    val languages: List<Language>
)