package com.tc.learn.utils.navigation

sealed class NavRoute(val route: String) {

    object Start : NavRoute("start")

    object Search : NavRoute("search")
    object Calander: NavRoute("calander") 

//    object Calander: NavRoute("calander") {
//        fun passId(id: String) = "calendar/$id"
//    }
    object Map : NavRoute("map/{teacherId}"){
        fun passId(id: String) = "map/$id"
    }

    object TeacherDetail : NavRoute("teacher_detail/{teacherId}") {
        fun passId(id: String) = "teacher_detail/$id"
    }

//    object Booking : NavRoute("booking/{teacherId}/{subject}/{level}") {
//        fun passParams(teacherId: Int, subject: String, level: String) =
//            "booking/$teacherId/$subject/$level"
//    }
    object Booking : NavRoute("booking/{teacherId}") {
        fun passParams(teacherId: String) = "booking/$teacherId"
    }


    // Add other screens if needed, e.g. Map, Filter, Order
    object Filter : NavRoute("filter")
    object Order : NavRoute("order")
}
