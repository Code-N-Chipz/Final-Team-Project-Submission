package com.tc.babysitter.babysitterlist

import BookingViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.tc.babysitter.addchild.ChildViewModel
import com.tc.ui.R

// Colors & Fonts
val PrimaryOrange = Color(0xFFFF7A1A)
val DarkText = Color(0xFF2C2C2C)
val GrayText = Color(0xFF888888)
val PoppinsFamily = FontFamily.SansSerif

data class Babysitter(
    val name: String,
    val experience: String,
    val image: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectBabysitterScreen(
    navController: NavHostController,
    viewModel: ChildViewModel = viewModel(),
    bookingViewModel: BookingViewModel = viewModel()
) {
    val childData = viewModel.childData

    // Default child image if ViewModel photoUri is null
    val childImagePainter = childData.photoUri?.let {
        rememberAsyncImagePainter(it)
    } ?: painterResource(R.drawable.babysitter_homepage_image)

    var currentLocation by remember { mutableStateOf("New York, USA") }
    var dateTime  by remember { mutableStateOf(bookingViewModel.getFormattedDateTime()) }
    var numChildren by remember { mutableStateOf("1") }
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    val babysitters = listOf(
        Babysitter("Emma Johnson", "5 yrs experience", R.drawable.babysitter_homepage_image),
        Babysitter("Olivia Brown", "3 yrs experience", R.drawable.babysitter_homepage_image),
        Babysitter("Ava Davis", "4 yrs experience", R.drawable.babysitter_homepage_image),
        Babysitter("Sophia Miller", "2 yrs experience", R.drawable.babysitter_homepage_image),
        Babysitter("Isabella Wilson", "6 yrs experience", R.drawable.babysitter_homepage_image)
    )

    Scaffold(containerColor = Color.White) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize()) {
            // --- Background layout (top: child, bottom: babysitters) ---
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                // --- Child photo + info ---
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .background(Color(0xFFF9F9F9)),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = childImagePainter,
                            contentDescription = "Child Photo",
                            modifier = Modifier
                                .size(220.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .border(2.dp, PrimaryOrange, RoundedCornerShape(16.dp)),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = childData.name.ifBlank { "Unknown Child" },
                            fontFamily = PoppinsFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp,
                            color = DarkText
                        )
                        Text(
                            text = "Age: ${childData.age.ifBlank { "N/A" }} | ${childData.sex.ifBlank { "N/A" }}",
                            fontFamily = PoppinsFamily,
                            color = GrayText,
                            fontSize = 14.sp
                        )
                    }

                    // --- Home button ---
                    IconButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(16.dp)
                            .size(48.dp)
                            .background(Color.White, CircleShape)
                            .border(1.5.dp, Color.White, CircleShape)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Home,
                            contentDescription = "Home",
                            tint = PrimaryOrange
                        )
                    }
                }

                // --- Babysitter list ---
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(babysitters) { sitter ->
                        BabysitterCard(sitter) // Save selected babysitter in BookingViewModel
                        bookingViewModel.setBookingData(sitter)
                        // Navigate to OrderScreen
                        navController.navigate("orderdetails")
                    }
                }
            }

            // --- Floating overlay ---
            Surface(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth(0.9f),
                shape = RoundedCornerShape(16.dp),
                tonalElevation = 8.dp,
                shadowElevation = 8.dp,
                color = Color.White.copy(alpha = 0.96f)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Current location
                    OutlinedTextField(
                        value = currentLocation,
                        onValueChange = { currentLocation = it },
                        label = { Text("Current Location") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.LocationOn,
                                contentDescription = null,
                                tint = PrimaryOrange
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        readOnly = true
                    )

                    // Row: Date & Children count
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = dateTime,
                            onValueChange = { dateTime = it },
                            label = { Text("Choose Date & Time") },
                            modifier = Modifier.weight(1f).clickable {
                                navController.navigate("choosedate")
                            }
                        )

                        OutlinedTextField(
                            value = numChildren,
                            onValueChange = { numChildren = it },
                            label = { Text("No. of Children") },
                            modifier = Modifier.weight(1f)
                        )
                    }

                    // Search field
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        label = { Text("Search by location or name") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Button(
                        onClick = { /* TODO: Implement search */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryOrange)
                    ) {
                        Text("Search", color = Color.White, fontSize = 18.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun BabysitterCard(sitter: Babysitter) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, GrayText.copy(alpha = 0.3f), RoundedCornerShape(12.dp))
            .background(Color.White)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(sitter.image),
            contentDescription = sitter.name,
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = sitter.name,
                fontFamily = PoppinsFamily,
                fontWeight = FontWeight.SemiBold,
                color = DarkText,
                fontSize = 16.sp
            )
            Text(
                text = sitter.experience,
                fontFamily = PoppinsFamily,
                color = GrayText,
                fontSize = 13.sp
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 800)
@Composable
fun PreviewSelectBabysitterScreen() {
    SelectBabysitterScreen(navController = rememberNavController())
}
