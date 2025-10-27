package com.tc.uber.ui.nav

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface Screen  : NavKey{
    @Serializable
    data object WelcomeScreen : Screen

    @Serializable
    data object EnableLocationScreen : Screen

    @Serializable
    data object ScheduleScreen : Screen

    @Serializable
    data object PaymentScreen : Screen

    @Serializable
    data object MapScreen : Screen
}