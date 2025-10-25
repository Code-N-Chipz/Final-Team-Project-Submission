package com.tc.auth.ui.signin

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tc.auth.ui.navigation.AppNavigator
import com.tc.auth.ui.navigation.ComposeAppNavigator


@Composable
fun MainScreen(
    navigator: ComposeAppNavigator = hiltViewModel()
) {
    val navController = rememberNavController()

    LaunchedEffect(Unit) {
        navigator.setController(navController)
    }

    NavHost(navController = navController, startDestination = "signin") {
        composable("signin") {
            SignInScreen(
                navigator = navigator, // passes ComposeAppNavigator as AppNavigator
                name = "John"
            )
        }
        composable("signup") { SignUpScreen() }
    }
}

@Composable
fun SignUpScreen() {
    Column(modifier = Modifier) {

    }
}


@Composable
fun SignInScreen(
    navigator: AppNavigator,
//    navigator: ComposeAppNavigator,
    viewModel: SignInViewModel = hiltViewModel(),
    name: String,
) {
    val signInResult by viewModel.signInResult.observeAsState()

    LaunchedEffect(signInResult) {
        when (signInResult) {
            true -> {
                // Use navigator instead of NavController directly
                navigator.navigateTo("home")
            }
            false -> {
                // handle sign-in failure
            }
            null -> Unit
        }
    }

    SignInScreenContent(
        name = name,
        onButtonClick = {  }
    )
}

@Composable
fun SignInScreenContent(
    modifier: Modifier = Modifier,
    name: String,
    onButtonClick: () -> Unit = {},
) {
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    SignInScreenContent(name = "Test", onButtonClick = {})
}

//
//@Composable
//fun SignInScreen(
//    modifier: Modifier = Modifier,
//    navController: NavController,
//    viewModel: SignInViewModel = hiltViewModel(),
//    auth: FirebaseAuth = FirebaseAuth.getInstance(),
//    onLoginSuccess: (String) -> Unit = { navController.navigate(Screen.Home.route) },
//    name: String,
//    signUpButtonClick: () -> Unit = { navController.navigate(Screen.SignUp.route) },
//) {
//    var email by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//    var statusMessage by remember { mutableStateOf("") }
//    var isLoading by remember { mutableStateOf(false) }
//
//    val signInResult by viewModel.signInResult.observeAsState()
//
//    LaunchedEffect(signInResult) {
//        when (signInResult) {
//            true -> {
//                // Navigate to Home only once
//                navController.navigate("home") {
//                    popUpTo("signin") { inclusive = true }
//                }
//            }
//
//            false -> {
//                isLoading = false
//                statusMessage = "Sign-in failed" // optional
//            }
//
//            null -> {
//                // do nothing
//            }
//        }
//    }
//
//    Column(
//        modifier = modifier.fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.SpaceBetween
//    ) {
//        // Top content
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            modifier = Modifier.padding(top = 40.dp)
//        ) {
//            // Optional image above
//            // Image(painterResource(R.drawable.star_on), contentDescription = null)
//
//            Text("Sign In", style = MaterialTheme.typography.headlineSmall)
//            Spacer(modifier = Modifier.height(20.dp))
//            Text("Hello, $name", style = MaterialTheme.typography.bodyLarge)
//            Spacer(modifier = Modifier.height(14.dp))
//            Text(
//                "Welcome to the Learn feature! Here you can find a tutor to learn various subjects, at different levels of education.",
//                style = MaterialTheme.typography.bodyMedium,
//                modifier = Modifier.padding(horizontal = 16.dp)
//            )
//        }
//
//        // Bottom button
//        Button(
//            onClick = signUpButtonClick,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 16.dp, vertical = 24.dp)
//        ) {
//            Text("Get Started")
//        }
//
//    }
//
//
//    @Composable
//    fun SignUpCard() {
//
//    }
//
//    @Preview(showBackground = true)
//    @Composable
//    fun SignInScreenPreview() {
//        SignInScreen(
//            Modifier,
//            signUpButtonClick = {},
//            navController = { } as NavController,
//            name = "Test"
//        )
//    }
//
//
//    @Composable
//    fun SignInScreenContent(
//        modifier: Modifier = Modifier,
//        name: String,
//        onButtonClick: () -> Unit = {},
//    ) {
//        Column(
//            modifier = modifier.fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.SpaceBetween
//        ) {
//            // Top content
//            Column(
//                horizontalAlignment = Alignment.CenterHorizontally,
//                modifier = Modifier.padding(top = 40.dp)
//            ) {
//                // Optional image
//                // Image(painterResource(R.drawable.star_on), contentDescription = null)
//
//                Text("Sign In", style = MaterialTheme.typography.headlineSmall)
//                Spacer(modifier = Modifier.height(20.dp))
//                Text("Hello, $name", style = MaterialTheme.typography.bodyLarge)
//                Spacer(modifier = Modifier.height(14.dp))
//                Text(
//                    "Welcome to the Learn feature! Here you can find a tutor to learn various subjects, at different levels of education.",
//                    style = MaterialTheme.typography.bodyMedium,
//                    modifier = Modifier.padding(horizontal = 16.dp)
//                )
//            }
//
//            // Bottom button
//            Button(
//                onClick = onButtonClick,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp, vertical = 24.dp)
//            ) {
//                Text("Get Started")
//            }
//        }
//    }
//
//    @Preview(showBackground = true)
//    @Composable
//    fun SignInScreenPreview() {
//        SignInScreenContent(
//            name = "Test",
//            onButtonClick = {}
//        )
//    }
//}
