package com.tc.tinder.domain.util

import kotlin.math.roundToInt

fun formatMilesLabel(miles: Double): String =
    when {
        miles < 0.5 -> "< 1 mile away"
        miles < 10 -> String.format("%.1f miles away", miles)
        else -> "${miles.roundToInt()} mi away"
    }
