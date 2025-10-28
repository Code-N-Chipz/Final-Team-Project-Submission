package com.tc.laundry.data

import androidx.annotation.DrawableRes
import com.tc.laundry.R
import java.time.LocalTime

data class Specialist(
    val name: String,
    val address: String,
    val isAvailable: Boolean,
    val workHours: ClosedRange<LocalTime> = LocalTime.of(8, 0)..LocalTime.of(20, 0),
    val stars: Float,
    @DrawableRes val imageRes: Int
)

private val specialistList: List<Specialist> = listOf(
    Specialist(
        name = "Alice Smith",
        address = "123 Main St",
        isAvailable = true,
        workHours = LocalTime.of(9, 0)..LocalTime.of(17, 0),
        stars = 4.6f,
        imageRes = R.drawable.laundry_girl
    ),
    Specialist(
        name = "Monica Jones",
        address = "456 Elm St",
        isAvailable = false,
        workHours = LocalTime.of(10, 0)..LocalTime.of(18, 0),
        stars = 4.2f,
        imageRes = R.drawable.laundry_girl
    ),
    Specialist(
        name = "Carol Lee",
        address = "789 Oak Ave",
        isAvailable = true,
        workHours = LocalTime.of(8, 30)..LocalTime.of(15, 30),
        stars = 5.0f,
        imageRes = R.drawable.laundry_girl
    ),
    Specialist(
        name = "Rachel Hoflof",
        address = "999 Ukon Ave",
        isAvailable = true,
        workHours = LocalTime.of(8, 30)..LocalTime.of(15, 30),
        stars = 3.0f,
        imageRes = R.drawable.laundry_girl
    ),
    Specialist(
        name = "Alice Smith",
        address = "123 Main St",
        isAvailable = true,
        workHours = LocalTime.of(9, 0)..LocalTime.of(17, 0),
        stars = 4.6f,
        imageRes = R.drawable.laundry_girl
    ),
    Specialist(
        name = "Janice Jones",
        address = "456 Elm St",
        isAvailable = false,
        workHours = LocalTime.of(10, 0)..LocalTime.of(18, 0),
        stars = 4.2f,
        imageRes = R.drawable.laundry_start_page
    ),
    Specialist(
        name = "Carol Lee",
        address = "789 Oak Ave",
        isAvailable = true,
        workHours = LocalTime.of(8, 30)..LocalTime.of(15, 30),
        stars = 5.0f,
        imageRes = R.drawable.laundry_girl
    ),
    Specialist(
        name = "Sophie Hoflof",
        address = "999 Ukon Ave",
        isAvailable = true,
        workHours = LocalTime.of(8, 30)..LocalTime.of(15, 30),
        stars = 3.0f,
        imageRes = R.drawable.laundry_girl
    )
)