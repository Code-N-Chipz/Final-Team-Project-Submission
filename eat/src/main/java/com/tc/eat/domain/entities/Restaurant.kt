package com.tc.eat.domain.entities

data class Restaurant(
    val name : String,
    val subtitle : String,
    val rating : Float,
    val distanceTime : Int,
    val priceRange : String,
    val logoImage : Int,
)