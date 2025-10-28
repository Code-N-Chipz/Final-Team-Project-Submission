package com.tc.babysitter.babysitterlist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

data class Caregiver(
    val fullName: String,
    val avatarUrl: String,
    val rating: Float,
    val latitude: Double,
    val longitude: Double
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CaregiverMapScreen() {
    val context = LocalContext.current
    val userLocation = LatLng(-26.2041, 28.0473)

    val caregivers = listOf(
        Caregiver(
            fullName = "Jenny Jones",
            avatarUrl = "https://randomuser.me/api/portraits/women/65.jpg",
            rating = 4.8f,
            latitude = -26.2030,
            longitude = 28.0465
        ),
        Caregiver(
            fullName = "Sacha Down",
            avatarUrl = "https://randomuser.me/api/portraits/women/22.jpg",
            rating = 4.8f,
            latitude = -26.2060,
            longitude = 28.0500
        ),
        Caregiver(
            fullName = "Ella Brown",
            avatarUrl = "https://randomuser.me/api/portraits/women/10.jpg",
            rating = 4.7f,
            latitude = -26.2050,
            longitude = 28.0430
        )
    )

    val cameraState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(userLocation, 15f)
    }

    var selectedCaregiver by remember { mutableStateOf<Caregiver?>(null) }
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Box(modifier = Modifier.fillMaxSize()) {

        // Google Map
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraState,
            uiSettings = MapUiSettings(zoomControlsEnabled = false)
        ) {
            caregivers.forEach { caregiver ->
                val location = LatLng(caregiver.latitude, caregiver.longitude)
                Marker(
                    state = MarkerState(position = location),
                    title = caregiver.fullName,
                    onClick = {
                        selectedCaregiver = caregiver
                        true
                    }
                )
            }

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
                painter = painterResource(id = android.R.drawable.ic_menu_mylocation),
                contentDescription = "My Location",
                tint = Color(0xFFFF7A00)
            )
        }

        // Bottom Cards Row
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            caregivers.take(2).forEach { caregiver ->
                CaregiverInfoCard(
                    caregiver = caregiver,
                    modifier = Modifier
                        .padding(horizontal = 6.dp)
                        .weight(1f)
                        .clickable { selectedCaregiver = caregiver }
                )
            }
        }

        // Bottom Sheet (appears when caregiver is selected)
        if (selectedCaregiver != null) {
            ModalBottomSheet(
                onDismissRequest = { selectedCaregiver = null },
                sheetState = bottomSheetState,
                containerColor = Color.White,
                shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)
            ) {
                CaregiverDetailSheet(caregiver = selectedCaregiver!!)
            }
        }
    }
}

@Composable
fun CaregiverInfoCard(caregiver: Caregiver, modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = modifier.height(90.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(12.dp)
        ) {
            AsyncImage(
                model = caregiver.avatarUrl,
                contentDescription = caregiver.fullName,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    caregiver.fullName,
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.btn_star_big_on),
                        contentDescription = "Rating",
                        tint = Color(0xFFFF7A00),
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        "${caregiver.rating}",
                        color = Color.Gray,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
                Text("4.5 Mile Nearby", color = Color.Gray, fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun CaregiverDetailSheet(caregiver: Caregiver) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = caregiver.avatarUrl,
            contentDescription = caregiver.fullName,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            caregiver.fullName,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = android.R.drawable.btn_star_big_on),
                    contentDescription = null,
                    tint = Color(0xFFFF7A00),
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    caregiver.rating.toString(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }

            Text(
                "$15/h",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis lobortis amet odio in egestas. Pellentesque ultricies justo.",
            fontSize = 14.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { /* Take appointment */ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7A00)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Take appointment", color = Color.White, fontSize = 16.sp)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewCaregiverMapScreen() {
    CaregiverMapScreen()
}
