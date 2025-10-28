package com.tc.doctor.ui

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tc.doctor.data.fakedata.FakeSpecialists
import com.tc.doctor.data.repository.SpecialistRepositoryImpl
import com.tc.doctor.domain.usecase.GetPersonInfosUseCase
import com.tc.doctor.domain.usecase.GetSpecialistsUseCase
import com.tc.doctor.ui.appointment.ConfirmationScreen
import com.tc.doctor.ui.appointment.DoctorMapScreen
import com.tc.doctor.ui.appointment.FiltersScreen
import com.tc.doctor.ui.appointment.SearchDoctorScreen
import com.tc.doctor.ui.appointment.ViewModelAppointment
import com.tc.doctor.ui.appointment.ViewModelFactoryAppointment

// Resources:
// https://medium.com/@jpmtech/navigation-in-jetpack-compose-c9e1fcfd2cdd


// can declare:
// repo, useCase(repo),
// need to use a builder factory for this viewModel(useCase)
    // TODO: because?

// Declare a centralized and sealed class of possible routes
sealed class DoctorDest(val route: String) {
    data object Option : DoctorDest("option")
    data object Filters : DoctorDest("filters")
    data object Map : DoctorDest("map")
    data object Confirmation : DoctorDest("confirmation")
    data object Search : DoctorDest("search")
    data object Doctor : DoctorDest("doctor")

    // helper function for passing arguments
    // allows you inside your onClick to use:
    // navController.navigate(ScreenName.withArgs("name", "age"))
    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
    // helper for query/non-required
    fun withQuery(vararg args: Pair<String,String?>): String {
        return buildString {
            append(route)
            if (args.isNotEmpty()) {
                args.forEach { arg ->
                    append("?")
                    append(args.joinToString("&") { (key, value) ->
                        "${Uri.encode(key)}=${Uri.encode(value ?: "")}"
                    })
                }
            }
        }
    }
}

/** Simple extension to navigate using the strongly-typed destination. */
// useful when using a sealed class
// fine for argument-free navigation
// makes call sites terse TODO: what does this mean?
// Improvements for real apps:
    // should extend with route builders for destinations with arguments(data being passed, TODO: not sure if onClick navigation is included in this)
        // TODO: store a route template, add createRoute function in the builder?
    // need an overload accepting NavOptions/popUpTo
    // centralized navigation logic -> no leaky routes
        // behaviors like popUpTo, singleTop, restoring state with helper functions
// fun NavController.navigateTo(dest: DoctorDest) = navigate(dest.route)

// OPTION for nav with arguments:
fun NavController.navigateTo(
    dest: DoctorDest,
    navOptionsBuilder: (NavOptionsBuilder.() -> Unit)? = null
){
    if (navOptionsBuilder == null) {
        navigate(dest.route)
    } else {
        navigate(dest.route) { navOptionsBuilder() }
    }
}


/** Optional: pop up to start destination and then navigate. */
fun NavController.navigateSingleTopTo(dest: DoctorDest) {
    navigate(dest.route) {
        launchSingleTop = true
        restoreState = true
    }
}

/**
 * A NavHost you can drop into your screen. It registers all known routes.
 * You can pass lambdas for each destination to render your real screens.
 * If not provided, lightweight placeholders are shown so it compiles safely.
 */
// NavHost is the entrypoint to our module navigation
// we need to direct from dashboard to our NavHost
// NavHost will then redirect to its starting screen
@Composable
fun DoctorNavHost(
    parentNavController: NavController? = null,
    navController: NavHostController = rememberNavController(),
    startDestination: DoctorDest = DoctorDest.Doctor,
    // TODO: understand why we setup our nav this way, it is optional?
    // TODO: what does NavBackStackEntry do? we are passing it as blank "_"
        // NavBackStackEntry:  holds route info, arguments, savedStateHandle, lifecycle, etc. Use backStackEntry.arguments?.getString("key") (or savedStateHandle) to read params.
            // it’s just a forwarding pattern. lets composable read arguments or saved state
            // preferred for DI
    option: @Composable (NavBackStackEntry, NavController) -> Unit = { _, nc ->
        PlaceholderScreen(title = "Option", onNext = { nc.navigateTo(DoctorDest.Filters) })
    },
    filters: @Composable (NavBackStackEntry, NavController) -> Unit = { _, nc ->
        PlaceholderScreen(title = "Filters", onNext = { nc.navigateTo(DoctorDest.Map) })
    },
    map: @Composable (NavBackStackEntry, NavController) -> Unit = { _, nc ->
        PlaceholderScreen(title = "Map", onNext = { nc.navigateTo(DoctorDest.Search) })
    },
    confirmation: @Composable (NavBackStackEntry, NavController) -> Unit = { _, nc ->
        PlaceholderScreen(title = "Confirmation", onNext = { nc.navigateTo(DoctorDest.Option) })
    },
    search: @Composable (NavBackStackEntry, NavController) -> Unit = { _, nc ->
        PlaceholderScreen(title = "Search", onNext = { nc.navigateTo(DoctorDest.Confirmation) })
    },
    doctor: @Composable (NavBackStackEntry, NavController) -> Unit = { _, nc ->
        PlaceholderScreen(title = "Doctor", onNext = { nc.navigateTo(DoctorDest.Option)})
    }
) {

    val repo = remember { SpecialistRepositoryImpl(FakeSpecialists.list) }
    val specialistsUseCase = remember { GetSpecialistsUseCase(repo) }
    val personInfosUseCase = remember { GetPersonInfosUseCase(specialistsUseCase) }

    val viewModelAppointment: ViewModelAppointment = viewModel(
        factory = ViewModelFactoryAppointment(specialistsUseCase, personInfosUseCase)
    )

    NavHost(
        navController = navController,
        startDestination = startDestination.route // this is what is rendered when nav to it
    ) {
        composable(
            DoctorDest.Doctor.route
        ) { backStackEntry ->
            DoctorScreen(navController, parentNavController)
        // this is only for testing -> do not use this placeholder for prod
            // doctor(backStackEntry, navController)
            // instead use:
            //     doctor: @Composable (NavBackStackEntry, NavController) -> Unit = debugOrSafePlaceholder
        }
        // define all navigation per screen inside each composable here:
        composable(
            DoctorDest.Option.route
        ) { backStackEntry ->
            OptionScreen(navController)
        }
        composable(
            DoctorDest.Filters.route
        ) { backStackEntry ->
            FiltersScreen(navController, viewModelAppointment)
        }
        composable(
            DoctorDest.Search.route
        ) { backStackEntry ->
            SearchDoctorScreen(navController, viewModelAppointment)
        }
        composable(
            DoctorDest.Map.route
        ) { backStackEntry ->
            DoctorMapScreen(navController, viewModelAppointment)
        }
        composable(
            DoctorDest.Confirmation.route
        ) { backStackEntry ->
            ConfirmationScreen(navController)
        }

    }
}


// ui for testing
@Composable
private fun PlaceholderScreen(title: String, onNext: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "[$title]\nWire up your real composable by passing a lambda to DoctorNavHost().")
        Button(onClick = onNext) { Text("Next") }
    }
}


@Preview
@Composable
private fun DoctorNavHostPreview(){
    DoctorNavHost()
}

/*
@Composable
fun DoctorRoot() {
    DoctorNavHost(
        // Provide the real Search screen and hook its Home click to navigate
        search = { _, navController ->
            SearchDoctorScreen(
                onHomeClick = {
                    // Simple forward navigation:
                    // navController.navigateTo(DoctorDest.Option)

                    // Or singleTop + restore state, popping up to start:
                    navController.navigate(DoctorDest.Option.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
        // ...other routes
    )
}
 */

/*
If you build routes by string concatenation, URL‑encode values (especially if they can contain '/' or '?').
For complex objects prefer passing IDs and retrieving data from a shared ViewModel or SavedStateHandle in the destination.
If you need return/result semantics, use SavedStateHandle or the new navigation result APIs via savedStateHandle in the backStackEntry.
Prefer object over data object unless you intentionally need the data behavior — simple route singletons are plain object.
Keep NavController creation in the host (Activity or top-level composable). Pass NavController or callbacks down, rather than creating a new NavController in each screen.
 */



// Add navController: NavController to each screen
// onClick = { navController.navigate(Screen.Name.route)

/*
composable(route = thisRoute) {
    Name(navController = navController)
}
composable(
    route = nextRoute + "/{name}/{age}", // these are mandatory arguments being passed
    route = nextRoute + "?name={name}", // this is an optional argument
    arguments = listOf(
        navArgument("name") {
            type = NavType.StringType
            defaultValue = "Philip"
            nullable = true
        },
        navArgument("age") {
            type = NavType.IntType
        }
) { entry ->
    ScreenName(name = entry.arguments?.getString("name")) // this needs to be the sae as the navArgument above
 */