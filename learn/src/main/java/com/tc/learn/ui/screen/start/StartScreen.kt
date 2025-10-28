package com.tc.learn.ui.screen.start

import com.tc.learn.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.Coil.imageLoader
import coil.ImageLoader
import coil.compose.AsyncImage
import com.tc.learn.ui.screen.search.SearchScreen
import com.tc.learn.ui.screen.teacher.TeacherScreen
import com.tc.learn.ui.viewmodel.TeacherViewModel
import com.tc.learn.ui.navigation.AppNavigator
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.tc.learn.ui.component.ButtonWithTextOnly

@Composable
fun StartScreen(
    modifier: Modifier = Modifier,
    name: String,
    navigator: AppNavigator,
    viewModel: StartViewModel = hiltViewModel(),
    onButtonClick: () -> Unit
) {
    val imageLoader = viewModel.imageLoader

    Column(modifier = modifier
        .fillMaxSize()
        .padding(16.dp), verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        // Image at the top
        AsyncImage(
            model = R.drawable.start_image,
            contentDescription = "Teacher Start Screen Image",
            imageLoader = imageLoader,
            modifier = Modifier
                .size(100.dp)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Tutor Feature", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(40.dp))
            Text("Hello, $name", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                "Welcome to the Learn feature! Here you can find a tutor to learn various subjects, at different levels of education.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
        Spacer(modifier = Modifier.weight(1f))

//        Button(
//            onClick = onButtonClick,
//            modifier = Modifier
//                .align(Alignment.CenterHorizontally)
//                .fillMaxWidth()
//        ) {
//            Text("Get Started")
//        }

        ButtonWithTextOnly(
            text = "GetStarted",
            onClick = onButtonClick
        )

        Spacer(modifier = Modifier.height(26.dp))

    }
}

@Preview(showBackground = true)
@Composable
fun StartScreenPreview() {
    // Dummy navigator just for preview
    val fakeNavigator = object : AppNavigator {
        override fun navigateTo(route: String) {}
        override fun goBack() {}
        override fun setController(controller: NavController) {}
    }
    StartScreen(
        modifier = Modifier,
        name = "Test",
        navigator = fakeNavigator,
        onButtonClick = {  }
    )
}
