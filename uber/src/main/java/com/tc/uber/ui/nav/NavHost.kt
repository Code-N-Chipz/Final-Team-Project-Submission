package com.tc.uber.ui.nav

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.tc.uber.R
import com.tc.uber.ui.PaymentPage
import com.tc.uber.ui.SchedulePage
import com.tc.uber.ui.UberMapPage
import com.tc.uber.ui.WelcomePage

@Composable
fun UberNavigationC(navController : NavHostController){
    val description = """
        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis lobortis sit amet odio in egestas. Pellen tesque ultricies justo.
    """.trimIndent()

    val backStack = remember { mutableStateListOf<Screen>(Screen.WelcomeScreen) }

    NavDisplay(backStack = backStack, modifier = Modifier.fillMaxSize(),
        onBack = {
            try { backStack.removeLastOrNull() }
            catch (e : IllegalArgumentException){
                navController.popBackStack()
            }
                 },
        transitionSpec = {
            fadeIn(tween(300)) togetherWith fadeOut(tween(300))
        },
        entryProvider = entryProvider {
            entry<Screen.WelcomeScreen> {
                WelcomePage(R.drawable.welcome_uber,"Transportation",
                    description, "Let's go",
                    onBack = {
                        try { backStack.removeLastOrNull() }
            catch (e : IllegalArgumentException){
                navController.popBackStack()
            }
                    }) {
                    backStack.add(Screen.EnableLocationScreen)
                }
            }

            entry<Screen.EnableLocationScreen> {
                WelcomePage(R.drawable.welcome_uber,"Enable geolocation",
                    description, "Next", onBack = {
                        backStack.removeLastOrNull()
                    }) {
                    backStack.add(Screen.MapScreen)
                }
            }

            entry<Screen.ScheduleScreen> {
                SchedulePage(onBack = {
                    backStack.removeLastOrNull()
                }){
                    backStack.add(Screen.PaymentScreen)
                }

            }

            entry<Screen.PaymentScreen> {
                PaymentPage(onHome = {
                    backStack.removeLastOrNull()
                })
            }

            entry<Screen.MapScreen> {
                UberMapPage(onHome = {
                    backStack.removeLastOrNull()
                })
            }
        }

    )
}
