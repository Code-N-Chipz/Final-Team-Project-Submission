package com.tc.laundry.ui.map

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

data class LaundryLocation(
    val name: String,
    val imageUrl: String,
    val rating: Float,
    val latitude: Double,
    val longitude: Double
)

@Composable
fun MapScreen(
    navController: NavController
) {
    val context = LocalContext.current
    val userLocation = LatLng(-26.2041, 28.0473) // Johannesburg

    val laundry = listOf(
        LaundryLocation(
            name = "Jenny Jones",
            imageUrl = "https://randomuser.me/api/portraits/women/65.jpg",
            rating = 4.8f,
            latitude = -26.2030,
            longitude = 28.0465
        ),
        LaundryLocation(
            name = "Sacha Down",
            imageUrl = "https://randomuser.me/api/portraits/women/22.jpg",
            rating = 4.8f,
            latitude = -26.2060,
            longitude = 28.0500
        ),
        LaundryLocation(
            name = "Ella Brown",
            imageUrl = "https://randomuser.me/api/portraits/women/10.jpg",
            rating = 4.7f,
            latitude = -26.2050,
            longitude = 28.0430
        )
    )

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(userLocation, 15f)
    }

    Box(
        modifier = Modifier
        .fillMaxSize()
            .padding()
    ) {
        // Google Map
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            uiSettings = MapUiSettings(zoomControlsEnabled = false)
        ) {
            laundry.forEach { person ->
                val location = LatLng(person.latitude, person.longitude)
                Marker(
                    state = MarkerState(position = location),
                    title = person.name
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
                    painter = painterResource(id = android.R.drawable.ic_menu_search),
                    contentDescription = "Search"
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
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
                onClick = {
                    navController.navigate("home")
                },
                containerColor = Color.White
            ) {
                Icon(Icons.Filled.Home, contentDescription = "Home", tint = Color(0xFFFF7A00))
            }

            FloatingActionButton(
                onClick = {
                    navController.navigate("filters")
                },
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
                .padding(bottom = 150.dp, end = 10.dp)
        ) {
            Icon(
                painter = painterResource(id = android.R.drawable.ic_menu_mylocation),
                contentDescription = "My Location",
                tint = Color.Blue
            )
        }

        // Bottom Cards
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp, start = 27.dp)
                .align(Alignment.BottomEnd)
        ) {
            items(laundry){ person ->
                LaundryCard(
                    sitter = person
                )
            }
        }
    }
}

@Composable
fun LaundryCard(sitter: LaundryLocation, modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = modifier
            .height(90.dp)
            .padding(end = 12.dp  )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(12.dp)
        ) {
            AsyncImage(
                model = sitter.imageUrl,
                contentDescription = sitter.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(sitter.name, fontWeight = FontWeight.Bold, fontSize = MaterialTheme.typography.titleMedium.fontSize)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.btn_star_big_on),
                        contentDescription = "Rating",
                        tint = Color(0xFFFF7A00),
                        modifier = Modifier.size(16.dp)
                    )
                    Text("${sitter.rating}", color = Color.Gray, modifier = Modifier.padding(start = 4.dp))
                }
                Text("4.5 Mile", color = Color.Gray, fontSize = MaterialTheme.typography.bodySmall.fontSize)
                Text("Nearby", color = Color.Gray, fontSize = MaterialTheme.typography.bodySmall.fontSize)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewMapScreen() {
    MapScreen(rememberNavController())
}
