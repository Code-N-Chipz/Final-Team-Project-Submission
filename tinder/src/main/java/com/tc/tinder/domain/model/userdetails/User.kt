package com.tc.tinder.domain.model.userdetails


data class User(
    val id: String,
    val pictures: List<String>,
    val firstName: String,
    val lastName: String,
    val dateOfBirth: String,
    val location: Location,
    val description: String?,
    val isPremium: Boolean = false,
    val isBoosted: Boolean = false,
    val gender: Gender,
    val university: String? = null,
    val totalLikes: Int = 0,
    val totalSuperLikes: Int = 0,
    val totalBoosts: Int = 0
)





val currentUser = User(
    id = "current_001",
    pictures = listOf(
        "https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?auto=format&fit=crop&w=800&q=80",
    ),
    firstName = "Brian",
    lastName = "Downer",
    dateOfBirth = "2000-05-31",
    location = Location(latitude = 33.8280, longitude = -84.3800),
    description = " Tech enthusiast passionate about Jetpack Compose, Kotlin, and clean architecture.",
    isPremium = true,
    isBoosted = false,
    gender = Gender.MALE,
    university = "CUNY Brooklyn College",
    totalLikes = 0,
    totalSuperLikes = 0,
    totalBoosts = 0


)