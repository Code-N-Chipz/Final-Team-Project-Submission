package com.tc.iclickipay.ui.dashboard

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tc.laundry.ui.navigation.LaundryApp
import com.tc.laundry.ui.startpage.LaundryStartPage
import com.tc.tinder.presentation.navigation.TinderNavHost
import com.tc.eat.presentation.navigation.EatNavigation


@Preview
@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "dashboard") {
        composable("dashboard") { Dashboard(navController = navController) }

        // Define composable destinations for each screen id
        composable("screen_uber") { SimpleScreen("Uber Screen") }
        composable("screen_bank") { SimpleScreen("Bank Screen") }
        composable("screen_tinder") { TinderNavHost(parentNavController = navController) }
        composable("screen_chat") { SimpleScreen("Chat Screen") }
        composable<EatNavigation> { EatNavigation(
            navToApp = {navController.navigate("dashboard")}
        ) }
        composable("screen_hotel") { SimpleScreen("Hotel Screen") }
        composable("screen_doctor") { SimpleScreen("Doctor Screen") }
        composable("screen_pet") { SimpleScreen("Pet Screen") }
        composable("screen_mechanic") { SimpleScreen("Mechanic Screen") }
        composable("screen_pc_repair") {SimpleScreen("PcRepair Screen")}
        composable("screen_learn") { SimpleScreen("Learn Screen") }
        composable("screen_handy_man") { SimpleScreen("Handy Man Screen")}
        composable("screen_laundry") { LaundryApp(parentNavController = navController) }
        composable("screen_delivery") { SimpleScreen("Delivery Screen") }
        composable("screen_babysitter") {SimpleScreen("Babysitter Screen")}
        composable("screen_house_clean") { SimpleScreen("House Clean Screen") }
    }
}

@Composable
fun SimpleScreen(title: String) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(24.dp)
        )
    }
}