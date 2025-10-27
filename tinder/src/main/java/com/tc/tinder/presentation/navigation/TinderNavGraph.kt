package com.tc.tinder.presentation.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tc.tinder.data.fake.TestUsers
import com.tc.tinder.data.repository.UserFakeRepositoryImpl
import com.tc.tinder.domain.usecase.GetCandidatesUseCase
import com.tc.tinder.presentation.screen.BoostPaymentScreen
import com.tc.tinder.presentation.screen.CameraScreen
import com.tc.tinder.presentation.screen.GeolocationScreen
import com.tc.tinder.presentation.screen.LikePaymentScreen
import com.tc.tinder.presentation.screen.MatchScreen
import com.tc.tinder.presentation.screen.PictureScreen
import com.tc.tinder.presentation.screen.ProfileSignUpScreen
import com.tc.tinder.presentation.screen.StartingScreen
import com.tc.tinder.presentation.screen.SuperLikePaymentScreen
import com.tc.tinder.presentation.screen.TutorialScreen
import com.tc.tinder.presentation.viewmodel.MatchViewModel
import com.tc.tinder.presentation.viewmodel.MatchViewModelFactory

@Composable
fun TinderNavHost() {
    val navController = rememberNavController()

    // ✅ Setup ViewModel dependencies
    val repo = remember { com.tc.tinder.data.repository.UserFakeRepositoryImpl(com.tc.tinder.data.fake.TestUsers.list) }
    val useCase = remember { com.tc.tinder.domain.usecase.GetCandidatesUseCase(repo) }
    val matchViewModel: MatchViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = com.tc.tinder.presentation.viewmodel.MatchViewModelFactory(useCase)
    )

    NavHost(
        navController = navController,
        startDestination = "start"
    ) {
        // 1️⃣ Starting screen
        composable("start") {
            StartingScreen(
                onLetsGoClick = {
                    navController.navigate("picture") {
                        popUpTo("start") { inclusive = true }
                    }
                }
            )
        }

        // 2️⃣ Picture screen
        composable("picture") {
            PictureScreen(
                onBackClick = { navController.popBackStack() },
                onAddPictureClick = {},
                onTakePicture = { navController.navigate("camera") }
            )
        }

        // ⃣ Camera
        composable("camera") {
            CameraScreen(
                onBackClick = { navController.popBackStack() },
                onPhotoCaptured = { uri ->
                    navController.navigate("signup?photo=${Uri.encode(uri.toString())}")
                }
            )
        }

        //  Profile signup
        composable(
            "signup?photo={photo}",
            arguments = listOf(
                navArgument("photo") {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) { entry ->
            val photoUri = entry.arguments?.getString("photo")?.let { Uri.parse(it) }
            ProfileSignUpScreen(
                firstPhotoUri = photoUri,
                onBackClick = { navController.popBackStack() },
                onNextClick = { navController.navigate("geolocation") }
            )
        }

        // Geolocation
        composable("geolocation") {
            GeolocationScreen(
                onBackClick = { navController.popBackStack() },
                onActivateClick = { navController.navigate("tutorial") }
            )
        }

        //  Tutorial
        composable("tutorial") {
            TutorialScreen(
                onBackClick = { navController.popBackStack() },
                onDiscoverClick = {
                    navController.navigate("match") {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

        //  Match (with paywall)
        composable("match") {
            MatchScreen(
                viewModel = matchViewModel,
                onHomeClick = {},
                onMessageClick = {},
                onUserProfileClick = {},
                onOpenBoostPaywall = { navController.navigate("boost_payment") },
                onOpenLikePaywall = { navController.navigate("like_payment") },
                onOpenSuperLikePaywall = { navController.navigate("superlike_payment") }
            )
        }

        //  Boost Payment
        composable("boost_payment") {
            BoostPaymentScreen(navController,matchViewModel)
        }

        //  Like Payment
        composable("like_payment") {
            LikePaymentScreen(navController,matchViewModel)
        }

        // Super Like Payment
        composable("superlike_payment") {
            SuperLikePaymentScreen(navController,matchViewModel)
        }
    }
}


@Preview
@Composable
fun TinderFeatureEntry() {
    TinderNavHost()
}

