package com.tc.pcrepair.ui.map

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.tc.pcrepair.R
import com.tc.pcrepair.data.PcRepairLocationData


@Composable
fun PCMapScreen(
    viewModel: PCMapScreenViewModel = viewModel(),
    onSelection: () -> Unit
) {
    val context = LocalContext.current
    val userLocation = LatLng(-26.2041, 28.0473) // Johannesburg

    val pc_repaire = listOf(
        PcRepairLocationData(
            name = "Jenny Jones",
            imageUrl = R.drawable.jenny,
            rating = 4.8f,
            latitude = -26.2030,
            longitude = 28.0465
        ),
        PcRepairLocationData(
            name = "Jean Down",
            imageUrl = R.drawable.jean,
            rating = 4.8f,
            latitude = -26.2060,
            longitude = 28.0500
        ),
        PcRepairLocationData(
            name = "Person 3",
            imageUrl = R.drawable.person_3,
            rating = 4.7f,
            latitude = -26.2050,
            longitude = 28.0430
        ),
        PcRepairLocationData(
            name = "Person 4",
            imageUrl = R.drawable.person_4,
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
            pc_repaire.forEach { repair ->
                val location = LatLng(repair.latitude, repair.longitude)
                Marker (
                    state = MarkerState(position = location),
                    title = repair.name
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
            pc_repaire.take(2).forEachIndexed { index, pcRepair ->
                PcRepairCard(
                    pcRepair = pcRepair,
                    modifier = Modifier
                        .padding(horizontal = 6.dp)
                        .weight(1f),
                    onSelection
                )
            }
        }
    }
}

@Composable
fun PcRepairCard(
    pcRepair: PcRepairLocationData,
    modifier: Modifier = Modifier,
    onSelection: () -> Unit) {
    Card (
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = modifier
            .height(90.dp)
            .clickable( onClick = onSelection)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(12.dp)
        ) {
            AsyncImage(
                model = pcRepair.imageUrl,
                contentDescription = pcRepair.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(pcRepair.name, fontWeight = FontWeight.Bold, fontSize = MaterialTheme.typography.titleMedium.fontSize)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.btn_star_big_on),
                        contentDescription = "Rating",
                        tint = Color(0xFFFF7A00),
                        modifier = Modifier.size(16.dp)
                    )
                    Text("${pcRepair.rating}", color = Color.Gray, modifier = Modifier.padding(start = 4.dp))
                }
                Text("4.5 Mile", color = Color.Gray, fontSize = MaterialTheme.typography.bodySmall.fontSize)
                Text("Nearby", color = Color.Gray, fontSize = MaterialTheme.typography.bodySmall.fontSize)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PCPreviewMapScreen() {
    PCMapScreen(onSelection = {})
}
