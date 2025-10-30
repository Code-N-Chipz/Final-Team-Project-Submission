package com.tc.doctor.data.fakedata

import com.tc.doctor.R
import com.tc.doctor.domain.model.Language
import com.tc.doctor.domain.model.Specialist
import java.time.LocalTime

object FakeSpecialists {
    val list: List<Specialist> = listOf(
        Specialist(
            id = 1,
            name = "Dr. Alice Smith",
            address = "123 Main St",
            isAvailable = true,
            workHours = LocalTime.of(9, 0)..LocalTime.of(17, 0),
            stars = 4.6f,
            imageRes = R.drawable.img_jenny_jones,
            specialty = "",
            languages = listOf(Language.ENGLISH, Language.SPANISH)
        ),
        Specialist(
            id = 2,
            name = "Dr. Bob Jones",
            address = "456 Elm St",
            isAvailable = false,
            workHours = LocalTime.of(10, 0)..LocalTime.of(18, 0),
            stars = 4.2f,
            imageRes = R.drawable.img_dispensary,
            specialty = "",
            languages = listOf(Language.ENGLISH, Language.SPANISH)
        ),
        Specialist(
            id = 3,
            name = "Dr. Carol Lee",
            address = "789 Oak Ave",
            isAvailable = true,
            workHours = LocalTime.of(8, 30)..LocalTime.of(15, 30),
            stars = 5.0f,
            imageRes = R.drawable.img_jenny_jones,
            specialty = "",
            languages = listOf(Language.ENGLISH, Language.SPANISH)
        ),
        Specialist(
            id = 4,
            name = "Dr. James Hoflof",
            address = "999 Ukon Ave",
            isAvailable = true,
            workHours = LocalTime.of(8, 30)..LocalTime.of(15, 30),
            stars = 3.0f,
            imageRes = R.drawable.img_jenny_jones,
            specialty = "",
            languages = listOf(Language.ENGLISH, Language.SPANISH)
        ),
        Specialist(
            id = 5,
            name = "Dr. Alice Smith",
            address = "123 Main St",
            isAvailable = true,
            workHours = LocalTime.of(9, 0)..LocalTime.of(17, 0),
            stars = 4.6f,
            imageRes = R.drawable.img_jenny_jones,
            specialty = "",
            languages = listOf(Language.ENGLISH, Language.SPANISH)
        ),
        Specialist(
            id = 6,
            name = "Dr. Bob Jones",
            address = "456 Elm St",
            isAvailable = false,
            workHours = LocalTime.of(10, 0)..LocalTime.of(18, 0),
            stars = 4.2f,
            imageRes = R.drawable.img_dispensary,
            specialty = "",
            languages = listOf(Language.ENGLISH, Language.SPANISH)
        ),
        Specialist(
            id = 7,
            name = "Dr. Carol Lee",
            address = "789 Oak Ave",
            isAvailable = true,
            workHours = LocalTime.of(8, 30)..LocalTime.of(15, 30),
            stars = 5.0f,
            imageRes = R.drawable.img_jenny_jones,
            specialty = "",
            languages = listOf(Language.ENGLISH, Language.SPANISH)
        ),
        Specialist(
            id = 8,
            name = "Dr. James Hoflof",
            address = "999 Ukon Ave",
            isAvailable = true,
            workHours = LocalTime.of(8, 30)..LocalTime.of(15, 30),
            stars = 3.0f,
            imageRes = R.drawable.img_jenny_jones,
            specialty = "",
            languages = listOf(Language.ENGLISH, Language.SPANISH)
        )
    )
}

