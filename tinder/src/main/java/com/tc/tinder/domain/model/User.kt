package com.tc.tinder.domain.model


data class User(
    val id: String,
    val profilePicture: String,
    val firstName: String,
    val lastName: String,
    val dateOfBirth: String,
    val location: Location,
    val description: String?,
    val isPremium: Boolean = false,
    val isBoosted: Boolean = false,
    val gender: Gender
)

enum class Gender {
    MALE,
    FEMALE,
    NON_BINARY,
    TRANSGENDER_MALE,
    TRANSGENDER_FEMALE,
    GENDER_FLUID,
    AGENDER,
    OTHER
}

data class Location(
    val latitude: Double,
    val longitude: Double
)
