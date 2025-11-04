package com.tc.mechanic.navigation.navigation


sealed class MechanicRoute(val route: String) {
    object Starter : MechanicRoute("mechanic_start")
    object SelectCar : MechanicRoute("Select_Car")
    object Filter : MechanicRoute("filter")
    object Map : MechanicRoute("Map")
    object Search : MechanicRoute("mechanic_search")
    object Summery : MechanicRoute("Summery")
    object Calender : MechanicRoute("Calender")

    object Dashboard: MechanicRoute("dashboard")
}