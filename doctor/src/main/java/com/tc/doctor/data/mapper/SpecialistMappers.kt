package com.tc.doctor.data.mapper

import com.tc.doctor.domain.model.Specialist
import com.tc.tcmap.domain.PersonInfo


fun Specialist.toPersonInfo(): PersonInfo =
    PersonInfo(
        imageUrl = imageRes.toString(),
        title = name,
        profession = specialty,
        rating = stars,
        distance = 10.0F,
        proximity = "Close",
        latitude = 33.867735,
        longitude = -84.600884
    )

fun List<Specialist>.toPersonInfoList(): List<PersonInfo> =
    this.map { it.toPersonInfo() }