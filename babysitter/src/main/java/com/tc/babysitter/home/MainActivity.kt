package com.tc.babysitter.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.tc.babysitter.AppNavGraph

class MainActivity: ComponentActivity() {
    val homePage: HomePage  = HomePage()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //homePage.HomeScreen(navController = rememberNavController())
            AppNavGraph()
        }
    }
}