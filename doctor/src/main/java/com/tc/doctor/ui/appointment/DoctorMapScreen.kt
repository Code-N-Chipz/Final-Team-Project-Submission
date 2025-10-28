package com.tc.doctor.ui.appointment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tc.doctor.domain.model.Specialist
import com.tc.tcmap.domain.MarkerInfo
import com.tc.tcmap.domain.PersonInfo
import com.tc.tcmap.ui.MapType
import com.tc.tcmap.ui.TcMap
import theme.backgroundColor
import theme.secondaryColor
import coil3.compose.AsyncImage
import com.tc.doctor.ui.DoctorDest
import com.tc.ui.CommonButton
import com.tc.ui.WeekCalendar
import com.tc.design.R as CoreDraw

sealed class MapStates(){
    object MapState: MapStates()
    object TakeAppState: MapStates()
    object CalendarState: MapStates()
    object ConfirmAppState: MapStates()
}



@Composable
fun DoctorMapScreen(
    navController: NavController? = null,
    viewModelAppointment: ViewModelAppointment? = null,
    mapState: MapStates = MapStates.MapState
){

    val specialistList: List<Specialist> =
        viewModelAppointment?.specialists?.collectAsState(initial = emptyList())?.value ?: emptyList()

    val personInfos: List<PersonInfo> =
        viewModelAppointment?.personInfos?.collectAsState(initial = emptyList())?.value ?: emptyList()

    val specialist = specialistList.getOrNull(1)
        ?: specialistList.firstOrNull()
        ?: return // maybe a loading?

    when(mapState) {
        MapStates.MapState -> MapStateContent(
            specialist = specialist,
            personInfos,
            onPersonClick = { MapStates.TakeAppState /* need to handle connect person info id (doesn't exist yet) with specialist */ },
            onMarkerClick = { /* figure out how to focus person info slider */ },
            mapState,
            onConfirmClick = { MapStates.CalendarState },
            onTakeAppoint = { navController?.navigate(DoctorDest.Confirmation) }
            )
        MapStates.CalendarState -> CalendarStateContent(
            specialist = specialist,
            onClick = { MapStates.ConfirmAppState }
        )
        else -> throw IllegalArgumentException("No state exists")
    }
}

@Composable
private fun MapStateContent(
    specialist: Specialist,
    personInfos: List<PersonInfo>,
    onPersonClick: () -> Unit = {},
    onMarkerClick: () -> Unit = {},
    mapState: MapStates = MapStates.MapState,
    onConfirmClick: () -> Unit = {},
    onTakeAppoint: () -> Unit = {}
) {
    val markers = personInfos.mapIndexed { index, pi ->
        MarkerInfo(
            0,
            imageUrl = pi.imageUrl,
            title = pi.title,
            latitude = pi.latitude,
            longitude = pi.longitude
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        TcMap(
            MapType.MarkedMapWithPeople(
                people = personInfos,
                onPersonClick = { onPersonClick }
            ),
            markers = markers,
            onMarkerClicked = { onMarkerClick }
        )
        if (mapState == MapStates.ConfirmAppState) {
            Column(
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier.fillMaxSize()
            ) {
                Row(modifier = Modifier
                    .height(300.dp)
                    .background(color = backgroundColor)) {
                    CommonHalfPage(specialist = specialist, "Confirm", onConfirmClick)
                }
            }
        } else if (mapState == MapStates.TakeAppState) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Column(modifier = Modifier
                    .height(100.dp)
                    .background(backgroundColor)
                ){
                    Row(verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.End) {
                        IconButton(
                            onClick = {},
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                                .background(secondaryColor)
                        ) {
                            Icon(
                                painter = painterResource(CoreDraw.drawable.phone_icon),
                                contentDescription = "Call",
                                tint = Color.Unspecified
                            )
                        }
                    }
                    CommonHalfPage(specialist = specialist, "Take appointment", onTakeAppoint)
                }
            }
        }
    }
}

@Composable
private fun CalendarStateContent(specialist: Specialist, onClick: () -> Unit = {}) {
    WeekCalendar(
        modifier = Modifier,
        onSelectionChange = { onClick },
        name = specialist.name,
        imageUrl = specialist.imageRes.toString()
        ) { }
}


@Composable
private fun CommonHalfPage(specialist: Specialist, buttonText: String, onButtonClick: () -> Unit) {
    Column {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                modifier = Modifier.size(50.dp),
                model = specialist.imageRes,
                contentDescription = "Specialist Image",
                contentScale = ContentScale.Crop,
                placeholder = painterResource(CoreDraw.drawable.image_icon),
                error = painterResource(CoreDraw.drawable.image_icon)
            )
            Text(specialist.name, style = theme.typography.titleLarge)
            Text(specialist.specialty)
            Row {
                val stars = specialist.stars
                val starRes = when {
                    stars >= 4 -> CoreDraw.drawable.full_star_icon
                    stars <= 1 -> CoreDraw.drawable.star_white_icon
                    else -> CoreDraw.drawable.half_star_icon
                }
                Icon(painter = painterResource(starRes), contentDescription = "Star")
                Text(specialist.stars.toString())
            }
        }
        Column {
            Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                    "Duis lobortis sit amet odio in egestas. Pellen tesque ultricies justo.")
            HorizontalDivider()
            Row {
                Icon(painter = painterResource(CoreDraw.drawable.pin_green_icon), contentDescription = "Location Icon")
                Text(specialist.address)
            }
            HorizontalDivider()
            Row {
                Icon(painter = painterResource(CoreDraw.drawable.globe_blue_icon), contentDescription = "Globe Icon")
                Text(specialist.languages.joinToString(", "))
            }
            CommonButton(buttonText, onButtonClick)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DoctorMapScreenPreviewMapState(){
    DoctorMapScreen(mapState = MapStates.MapState)
}

@Preview(showBackground = true)
@Composable
private fun DoctorMapScreenPreviewTakeApp(){
    DoctorMapScreen(mapState = MapStates.TakeAppState)
}

@Preview(showBackground = true)
@Composable
private fun DoctorMapScreenPreviewCalendar(){
    DoctorMapScreen(mapState = MapStates.CalendarState)
}

@Preview(showBackground = true)
@Composable
private fun DoctorMapScreenPreviewConfirm(){
    DoctorMapScreen(mapState = MapStates.ConfirmAppState)
}
