package com.tc.doctor.ui.appointment

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tc.ui.WeekCalendar
import com.tc.doctor.R

sealed class MapStates(){
    object MapState: MapStates()
    object TakeAppState: MapStates()
    object CalendarState: MapStates()
    object ConfirmAppState: MapStates()
}



@Composable
fun DoctorMapScreen(mapState: MapStates = MapStates.MapState){

    when(mapState) {
        MapStates.MapState -> MapStateContent()
        MapStates.TakeAppState -> TakeAppStateContent()
        MapStates.CalendarState -> CalendarStateContent()
        MapStates.ConfirmAppState -> ConfirmAppStateContent()
    }
}

data class Business(
    val name: String,
    @DrawableRes val img: Int
)
val myBusiness = Business("Jenny Jones", R.drawable.img_jenny_jones)

@Composable
private fun MapStateContent() {

}

@Composable
private fun TakeAppStateContent() {

}

@Composable
private fun CalendarStateContent() {
    WeekCalendar(modifier = Modifier, onSelectionChange = {}, business = myBusiness) { }
}

@Composable
private fun ConfirmAppStateContent() {

}