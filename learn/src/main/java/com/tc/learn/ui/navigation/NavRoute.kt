package com.tc.learn.utils.navigation

sealed class NavRoute(val route: String) {

    object Start : NavRoute("start")

    object Search : NavRoute("search")
//    object Calendar: NavRoute("calendar")

    object Calendar: NavRoute("calendar") {
        fun passId(id: String) = "calendar/$id"
    }
    object Map : NavRoute("map/{id}"){
        fun passId(id: String) = "map/$id"
    }


    object TeacherDetail : NavRoute("teacher_detail/{id}") {
        fun passId(id: String) = "teacher_detail/$id"
    }

//    object Booking : NavRoute("booking/{teacherId}/{subject}/{level}") {
//        fun passParams(teacherId: Int, subject: String, level: String) =
//            "booking/$teacherId/$subject/$level"
//    }
    object Booking : NavRoute("booking/{id}") {
        fun passParams(id: String) = "booking/$id"
    }


    // Add other screens if needed, e.g. Map, Filter, Order
    object Filter : NavRoute("filter")
    object Order : NavRoute("order")
}
