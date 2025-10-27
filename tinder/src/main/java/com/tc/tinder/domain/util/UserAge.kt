package com.tc.tinder.domain.util

import java.time.LocalDate

fun userAgeOrNull(dob: String): Int? = try {
    val birth = LocalDate.parse(dob) // expects yyyy-MM-dd
    val today = LocalDate.now()
    var years = today.year - birth.year
    if (today < birth.plusYears(years.toLong())) years -= 1
    years
} catch (_: Exception) {
    null
}