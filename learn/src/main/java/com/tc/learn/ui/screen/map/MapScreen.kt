package com.tc.learn.ui.screen.map

import android.R
import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.tc.learn.ui.navigation.AppNavigator
import com.tc.learn.ui.viewmodel.TeacherViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.google.maps.android.compose.*
import com.tc.learn.data.model.Teacher
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


//data class TeacherLocation(
//    val name: String,
//    val imageUrl: String,
//    val rating: Float,
//    val latitude: Double,
//    val longitude: Double
//)
@SuppressLint("UnrememberedMutableState")
@Composable
fun MapScreen (
    navigator: AppNavigator,
    viewModel: TeacherViewModel = hiltViewModel(),
    teacherId: String = ""){

    val context = LocalContext.current
    val userLocation = LatLng(-26.2041, 28.0473) // Johannesburg (testing)
    val teachers by viewModel.teachers.collectAsState(emptyList())

//    val teachers by viewModel?.teachers?.collectAsState(emptyList())
//        ?: remember {
//            mutableStateOf(
//                listOf(
//                    Teacher(id = "1", name = "John Doe", rating = 4.5f, imageUrl = "https://placekitten.com/200/200", -26.205, 28.047),
//                    Teacher(id = "2", name = "Jane Smith", rating = 4.9f, "https://placekitten.com/201/200", -26.203, 28.049)
//                )
//            )
//        }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(userLocation, 15f)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // Google Map
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            uiSettings = MapUiSettings(zoomControlsEnabled = false)
        ) {
            teachers.forEach { teacher ->
                val location = LatLng(teacher.latitude, teacher.longitude)
                Marker(
                    state = MarkerState(position = location),
                    title = teacher.name
                )
            }

            // User location marker (blue dot)
            Marker(
                state = MarkerState(position = userLocation),
                title = "You"
            )
        }

        // Search bar
        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Search") },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_menu_search),
                    contentDescription = "Search"
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White)
        )

        // Top-right floating icons
        Column(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 80.dp, end = 16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            FloatingActionButton(
                onClick = { /* TODO: Home */ },
                containerColor = Color.White
            ) {
                Icon(Icons.Filled.Home, contentDescription = "Home", tint = Color(0xFFFF7A00))
            }

            FloatingActionButton(
                onClick = { /* TODO: Filter */ },
                containerColor = Color.White
            ) {
                Icon(Icons.Filled.Tune, contentDescription = "Filter", tint = Color(0xFFFF7A00))
            }

            FloatingActionButton(
                onClick = { /* TODO: Menu */ },
                containerColor = Color.White
            ) {
                Icon(Icons.Filled.Settings, contentDescription = "Menu", tint = Color(0xFFFF7A00))
            }
        }

        // Center location button (bottom right)
        FloatingActionButton(
            onClick = { /* TODO: Recenter map */ },
            containerColor = Color.White,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_menu_mylocation),
                contentDescription = "My Location",
                tint = Color(0xFFFF7A00)
            )
        }

        // Bottom Cards
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            teachers.take(2).forEachIndexed { index, teacher ->
                TeacherMapCard (
                    teacher = teacher,
                    modifier = Modifier
                        .padding(horizontal = 6.dp)
                        .weight(1f)
                )
            }
        }
    }
}
@Composable
fun TeacherMapCard(teacher: Teacher, modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = modifier
            .height(90.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(12.dp)
        ) {
            AsyncImage(
                model = teacher.imageUrl,
                contentDescription = teacher.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(teacher.name, fontWeight = FontWeight.Bold, fontSize = MaterialTheme.typography.titleMedium.fontSize)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.btn_star_big_on),
                        contentDescription = "Rating",
                        tint = Color(0xFFFF7A00),
                        modifier = Modifier.size(16.dp)
                    )
                    Text("${teacher.rating}", color = Color.Gray, modifier = Modifier.padding(start = 4.dp))
                }

                //Hard coded distance
                Text("4.5 Mile", color = Color.Gray, fontSize = MaterialTheme.typography.bodySmall.fontSize)
                Text("Nearby", color = Color.Gray, fontSize = MaterialTheme.typography.bodySmall.fontSize)

            }
        }
    }
}

//@Preview(showSystemUi = true)
//@Composable
//fun PreviewMapScreen() {
//    MapScreen(
////        navigator =
//    )
//}
