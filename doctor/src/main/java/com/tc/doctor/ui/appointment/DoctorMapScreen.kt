package com.tc.doctor.ui.appointment

import androidx.compose.runtime.Composable

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

@Composable
private fun MapStateContent() {

}

@Composable
private fun TakeAppStateContent() {

}

@Composable
private fun CalendarStateContent() {

}

@Composable
private fun ConfirmAppStateContent() {

}