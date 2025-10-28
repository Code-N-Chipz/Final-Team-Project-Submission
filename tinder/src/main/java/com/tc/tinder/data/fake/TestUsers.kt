package com.tc.tinder.data.fake

import com.tc.tinder.domain.model.userdetails.Gender
import com.tc.tinder.domain.model.userdetails.Location
import com.tc.tinder.domain.model.userdetails.User

object TestUsers {
    val list: List<User> = listOf(
        User(
            id = "u_123",
            pictures = listOf(
                "https://plus.unsplash.com/premium_photo-1687186953637-78a495aec485?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=3086",
                "https://plus.unsplash.com/premium_photo-1687186953714-94abdcf49713?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=986",
                "https://plus.unsplash.com/premium_photo-1687186954019-e8254c769584?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=986",
            ),
            firstName = "Alex",
            lastName = "Rivera",
            dateOfBirth = "1998-04-15",
            location = Location(33.7890, -84.3840), // Midtown ATL
            description = "Marketing major and creative soul. Into fashion, digital art, and meaningful conversations over sushi. I’m the type to plan a trip, create the playlist, and make sure everyone’s having fun.",
            isPremium = true,
            isBoosted = false,
            gender = Gender.NON_BINARY,
            university = "Georgia Tech",
            totalLikes = 15,
            totalSuperLikes = 2,
            totalBoosts = 1
        ),


        User(
            id = "u_124",
            pictures = listOf(
                "https://c.stocksy.com/a/AwbN00/z9/5627378.jpg",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT9SCCzBgrlGmqIo5Zmhy8LaBJqijSj0A_UFT6UT6k2v43uXfHe8AO8U59EXAH6IJ9ZTXA&usqp=CAU",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQEqnamJo8-AXRUhlS6F51QYe09th6YozAaaHzBgj9xIPobUF8TqkIbHkYtWFrrfk4gFVA&usqp=CAU",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQer8O1zVEihZ9RP1e9OcJUSaWFKwUAeOcUHplEXfeTzGl6MNtpwmUSvBtq-GoPSFNzZD0&usqp=CAU"
            ),
            firstName = "Chandra",
            lastName = "Nguyen",
            dateOfBirth = "1980-02-02",
            location = Location(33.7480, -84.3880), // Downtown ATL
            description = "Senior Software Engineer from India \uD83C\uDDEE\uD83C\uDDF3 who’s been writing code since dial-up internet was a thing. I’ve built apps that scaled faster than chai disappears in a tech office. Known for solving bugs before the intern even reproduces them, and for turning caffeine into clean architecture. I speak Java, Kotlin, and sarcasm fluently and yes, I *do* run my own side project just for fun. When I’m not coding, I’m probably giving a tech talk or arguing why tabs are superior to spaces.",
            isPremium = false,
            isBoosted = false,
            gender = Gender.MALE,
            university = "Indian Institute of Science",
            totalLikes = 5,
            totalSuperLikes = 0,
            totalBoosts = 0
        ),

        // 3) Jordan
        User(
            id = "u_125",
            pictures = listOf(
                "https://www.vice.com/wp-content/uploads/sites/2/2025/01/onlyfans-star-bonnie-blue-says-she-had-sex-with-1057-men-in-12-hours.jpg?w=2000",
                "https://assets.newsweek.com/wp-content/uploads/2025/08/2565881-bonnie-blue.jpg?w=1600&quality=75&webp=1",
                "https://www.thesun.co.uk/wp-content/uploads/2025/01/insta-bonnie-blue.jpg?strip=all&w=960",
                "https://i2-prod.nottinghampost.com/article8944503.ece/ALTERNATES/s1200b/0_WhatsApp-Image-2023-11-30-at-105951jpeg.jpg"
            ),
            firstName = "Bonnie",
            lastName = "Blue",
            dateOfBirth = "1999-05-14",
            location = Location(33.8060, -84.3960), // Buckhead
            description = "Yes it's really me :)",
            isPremium = false,
            isBoosted = true,
            gender = Gender.FEMALE,
            university = null,
            totalLikes = 0,
            totalSuperLikes = 1,
            totalBoosts = 0
        ),

        // 4) Riley
        User(
            id = "u_126",
            pictures = listOf(
                "https://plus.unsplash.com/premium_photo-1664697380328-efe1b9867953?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1yZWxhdGVkfDI0fHx8ZW58MHx8fHx8&auto=format&fit=crop&q=60&w=900",
                "https://plus.unsplash.com/premium_photo-1664443296955-800e2e99a19e?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1yZWxhdGVkfDQ0fHx8ZW58MHx8fHx8&auto=format&fit=crop&q=60&w=900",
                "https://plus.unsplash.com/premium_photo-1664378762253-20cbe29159ba?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1yZWxhdGVkfDYwfHx8ZW58MHx8fHx8&auto=format&fit=crop&q=60&w=900"
            ),
            firstName = "Riley",
            lastName = "Coleman",
            dateOfBirth = "2001-02-08",
            location = Location(33.7720, -84.3950), // Midtown South
            description = "Pronouns they/them, I'm a Designer by day, film buff by night. Ask me about A24.",
            isPremium = true,
            isBoosted = false,
            gender = Gender.GENDER_FLUID,
            university = "Arizona State University",
            totalLikes = 8,
            totalSuperLikes = 3,
            totalBoosts = 1
        ),


        User(
            id = "u_127",
            pictures = listOf(
                "https://images.prismic.io/igspace/2a123b4e-7053-4bc1-bd9f-47f45c41c6db_RNLAF_AH-64_Apache_at_the_Oirschotse_Heide_Low_Flying_Area_%2836570605232%29.jpg?auto=compress,format"
            ),
            firstName = "Apache",
            lastName = "Heli",
            dateOfBirth = "1996-09-13",
            location = Location(33.7300, -84.3780), // Grant Park
            description = "whop-whop-whop",
            isPremium = false,
            isBoosted = false,
            gender = Gender.OTHER,
            university = "Georgia Military College",
            totalLikes = 2,
            totalSuperLikes = 0,
            totalBoosts = 0
        ),


        User(
            id = "u_128",
            pictures = listOf(
                "https://i.pinimg.com/736x/e6/38/b1/e638b149d626f6d70d1fd4a6115a10b1.jpg",
                "https://images.ladbible.com/resize?type=webp&quality=70&width=3840&fit=contain&gravity=auto&url=https://images.ladbiblegroup.com/v3/assets/bltcd74acc1d0a99f3a/blt818ad1450c489f56/64577c5948e642b3d9ed3165/Snapinsta.app_344577879_1203583483692643_7484127048344997175_n_1080.jpg"

            ),
            firstName = "Mia",
            lastName = "",
            dateOfBirth = "1993-02-10",
            location = Location(33.8020, -84.3270), // Decatur area
            description = "I’ve decided to turn my life around and find a good, God fearing man  someone who reads the Bible daily (or the Quran, that’s fine too). I’m looking for a man who has never sinned a day in his life and will accept me just as I am. You must be willing to pay all the bills while I contribute nothing but good vibes and occasional prayers. Please understand, I might slip up here and there… but remember, I’m just a girl :)",
            isPremium = true,
            isBoosted = true,
            gender = Gender.FEMALE,
            university = "University of Texas",
            totalLikes = 20,
            totalSuperLikes = 2,
            totalBoosts = 1
        )
    )
}