package com.tc.pcrepair.navigation


sealed class PCRepairRoute(val route: String) {
    object Starter : PCRepairRoute("pc_repair_start")
    object SelectPC : PCRepairRoute("pc_select")
    object FilterPC : PCRepairRoute("pc_filter")
    object MapPC : PCRepairRoute("pc_map")
    object SearchPC : PCRepairRoute("pc_search")
    object SummeryPC : PCRepairRoute("pc_summery")
    object CalenderPC : PCRepairRoute("pc_calender")
}