package com.tc.learn.data.model

fun Teacher.updateRating(newRating: Double): Teacher =
    this.copy(_rating = newRating)

fun Teacher.updatePrice(newPrice: Double, newLocation: Location): Teacher =
    this.copy(price = newPrice, location = newLocation)

fun Teacher.toLatLng(): Pair<Double, Double>? =
    this.location?.let { Pair(it.latitude, it.longitude) }

//fun Teacher.subjectNames():

fun Teacher.shortDescription(): String =
    "$name teaches ${subjectNames} for ${levelNames} at \$$validatedPrice/hr (Rating: $rating)"

fun Teacher.getAddress(): String =
    "$name teaches ${subjectNames} for ${levelNames} at \$$validatedPrice/hr (Rating: $rating)"
