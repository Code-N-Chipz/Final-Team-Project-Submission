package com.tc.tinder.domain.util

import com.tc.tinder.domain.model.userdetails.Location
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

class DistanceCalculator {
    private companion object {
        const val EARTH_RADIUS_MILES = 3958.7613
    }

    /** Distance in miles between two coordinates (Location). */
    fun miles(from: Location, to: Location): Double =
        miles(from.latitude, from.longitude, to.latitude, to.longitude)

    /** Distance in miles between two coordinates (lat/lon). */
    fun miles(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val rLat1 = Math.toRadians(lat1)
        val rLat2 = Math.toRadians(lat2)

        val a = sin(dLat / 2).pow(2) + cos(rLat1) * cos(rLat2) * sin(dLon / 2).pow(2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        return EARTH_RADIUS_MILES * c
    }

}