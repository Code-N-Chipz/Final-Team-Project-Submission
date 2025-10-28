package com.tc.babysitter.babysitterlist

import BookingViewModel
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecoveryTimeScreen(
    navController: NavHostController,
    @DrawableRes childImage: Int = com.tc.ui.R.drawable.babysitter_homepage_image,
    bookingViewModel: BookingViewModel = viewModel()
) {
    var selectedTime by remember { mutableStateOf<String?>(null) }

    // ✅ Generate 30-min time slots safely
    val timeSlots = remember { generateSafeTimeSlotsforRecoveryTimeScreen() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        // Back arrow on left
                        IconButton(
                            onClick = { navController.popBackStack() },
                            modifier = Modifier.align(Alignment.CenterStart)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = DarkText
                            )
                        }

                        // Center title
                        Text(
                            text = "Recovery Time",
                            fontFamily = PoppinsFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp,
                            color = PrimaryOrange,
                            modifier = Modifier.align(Alignment.Center)
                        )

                        // Right-side child circular image
                        Image(
                            painter = painterResource(id = childImage),
                            contentDescription = "Child",
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .align(Alignment.CenterEnd)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        containerColor = Color.White
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Time list
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 80.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(timeSlots) { time ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .clickable { selectedTime = time },
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (selectedTime == time) PrimaryOrange else Color(0xFFF5F5F5)
                        )
                    ) {
                        Text(
                            text = time,
                            fontFamily = PoppinsFamily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp,
                            color = if (selectedTime == time) Color.White else DarkText,
                            modifier = Modifier
                                .padding(vertical = 16.dp)
                                .fillMaxWidth()
                                .wrapContentWidth(Alignment.CenterHorizontally)
                        )
                    }
                }
            }

            // Floating bottom "Next" button
            Button(
                onClick = {
                    bookingViewModel.recoveryTime = selectedTime
                    navController.popBackStack("selectbabysitter", inclusive = false)
                },
                enabled = selectedTime != null,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryOrange),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Next",
                    color = Color.White,
                    fontFamily = PoppinsFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
            }
        }
    }
}


// Safely generates 30-minute interval time slots starting from the next half-hour
// and stops when the day changes (no infinite loop).

fun generateSafeTimeSlotsforRecoveryTimeScreen(): List<String> {
    val slots = mutableListOf<String>()
    val calendar = Calendar.getInstance()
    val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())

    val startDay = calendar.get(Calendar.DAY_OF_YEAR)

    // Round up to the next half hour
    val minute = calendar.get(Calendar.MINUTE)
    if (minute < 30) {
        calendar.set(Calendar.MINUTE, 30)
    } else {
        calendar.add(Calendar.HOUR_OF_DAY, 1)
        calendar.set(Calendar.MINUTE, 0)
    }

    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)

    // Stop once the day changes
    while (calendar.get(Calendar.DAY_OF_YEAR) == startDay) {
        slots.add(sdf.format(calendar.time))
        calendar.add(Calendar.MINUTE, 30)
    }

    return slots
}

@Preview(showBackground = true)
@Composable
fun PreviewRecoveryTimeScreen() {
    RecoveryTimeScreen(navController = rememberNavController())
}
