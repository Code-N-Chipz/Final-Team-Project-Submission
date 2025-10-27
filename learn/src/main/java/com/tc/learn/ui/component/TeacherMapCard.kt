package com.tc.learn.ui.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tc.learn.data.model.Location
import com.tc.learn.data.model.Teacher
import com.tc.tcmap.domain.MarkerInfo
import com.tc.tcmap.domain.PersonInfo
import com.tc.tcmap.ui.MapType
import com.tc.tcmap.ui.TcMap
@Composable
fun TeacherMapCard(
    modifier: Modifier = Modifier,
    teacherLocation: Location,
    teacher: Teacher,
    onMarkerClick: (MarkerInfo) -> Unit,
) {
    val marker = MarkerInfo(
        imageUrl = teacher.imageUrl,
        title = teacher.name,
        latitude = teacherLocation.latitude,
        longitude = teacherLocation.longitude
    )
    TcMap(
        mapType = MapType.MarkedMapWithPeople(
            onPersonClick = { },
            people = emptyList()
        ),
        markers = listOf(marker),
        onMarkerClicked = { markerInfo ->
            //Teacher Marker Clicks are handled here
            onMarkerClick(markerInfo)
        }
    )
}
