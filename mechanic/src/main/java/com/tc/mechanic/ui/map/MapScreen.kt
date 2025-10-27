package com.tc.mechanic.ui.map

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
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
import coil.compose.AsyncImage
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.tc.mechanic.R
import com.tc.mechanic.data.MechanicLocationData


@Composable
fun MapScreen() {
    val context = LocalContext.current
    val userLocation = LatLng(-26.2041, 28.0473) // Johannesburg

    val mechanics = listOf(
        MechanicLocationData(
            name = "Jenny Jones",
            imageUrl = R.drawable.jenny.toString(),
            rating = 4.8f,
            latitude = -26.2030,
            longitude = 28.0465
        ),
        MechanicLocationData(
            name = "Jean Down",
            imageUrl = R.drawable.jean.toString(),
            rating = 4.8f,
            latitude = -26.2060,
            longitude = 28.0500
        ),
        MechanicLocationData(
            name = "Person 3",
            imageUrl = R.drawable.person_3.toString(),
            rating = 4.7f,
            latitude = -26.2050,
            longitude = 28.0430
        ),
        MechanicLocationData(
            name = "Person 4",
            imageUrl = R.drawable.person_4.toString(),
            rating = 4.7f,
            latitude = -26.2050,
            longitude = 28.0430
        )
    )

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(userLocation, 15f)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // Google Map
        GoogleMap (
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            uiSettings = MapUiSettings(zoomControlsEnabled = false)
        ) {
            mechanics.forEach { mechanic ->
                val location = LatLng(mechanic.latitude, mechanic.longitude)
                Marker (
                    state = MarkerState(position = location),
                    title = mechanic.name
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
                .padding(16.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White)
        )

        // Top-right floating icons
        Column (
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 80.dp, end = 16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            FloatingActionButton (
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
                painter = painterResource(id = android.R.drawable.ic_menu_mylocation),
                contentDescription = "My Location",
                tint = Color(0xFFFF7A00)
            )
        }

        // Bottom Cards
        Row (
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            mechanics.take(2).forEachIndexed { index, mechanic ->
                MechanicCardView(
                    mechanic = mechanic,
                    modifier = Modifier
                        .padding(horizontal = 6.dp)
                        .weight(1f)
                )
            }
        }
    }
}

@Composable
fun MechanicCardView(mechanic: MechanicLocationData, modifier: Modifier = Modifier) {
    Card (
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
                model = mechanic.imageUrl,
                contentDescription = mechanic.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(mechanic.name, fontWeight = FontWeight.Bold, fontSize = MaterialTheme.typography.titleMedium.fontSize)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.btn_star_big_on),
                        contentDescription = "Rating",
                        tint = Color(0xFFFF7A00),
                        modifier = Modifier.size(16.dp)
                    )
                    Text("${mechanic.rating}", color = Color.Gray, modifier = Modifier.padding(start = 4.dp))
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
    MapScreen()
}
