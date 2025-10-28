package com.tc.babysitter.babysitterlist

import BookingViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tc.ui.R
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

// Colors and Fonts

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChooseDateEnhancedScreen(
    navController: NavHostController,
    childImage: Int = R.drawable.babysitter_homepage_image,
    bookingViewModel: BookingViewModel = viewModel()
) {
    val today = LocalDate.now()
    var selectedDate by remember { mutableStateOf(today) }
    var currentMonth by remember { mutableStateOf(YearMonth.now()) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        // Back arrow on the left
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

                        // Centered title
                        Text(
                            text = "Choose Date",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp,
                            color = DarkText,
                            modifier = Modifier.align(Alignment.Center)
                        )

                        // Child image on the right
                        Image(
                            painter = painterResource(childImage),
                            contentDescription = "Child Photo",
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
        bottomBar = {
            Button(
                onClick = {
                    bookingViewModel.selectedDate = selectedDate
                    navController.navigate("deposittime")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryOrange),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Next", color = Color.White, fontSize = 18.sp)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Selected date display
            Text(
                text = selectedDate.format(DateTimeFormatter.ofPattern("dd MMMM, EEE")),
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = DarkText
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Month selector
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = { currentMonth = currentMonth.minusMonths(1) }) {
                    Text("<", fontSize = 24.sp, color = DarkText)
                }
                Text(
                    text = currentMonth.format(DateTimeFormatter.ofPattern("MMMM yyyy")),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = DarkText
                )
                IconButton(onClick = { currentMonth = currentMonth.plusMonths(1) }) {
                    Text(">", fontSize = 24.sp, color = DarkText)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Weekday headers
            val weekdays = listOf("S","M","T","W","T","F","S")
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                weekdays.forEach { day ->
                    Text(
                        text = day,
                        fontWeight = FontWeight.SemiBold,
                        color = DarkText,
                        modifier = Modifier.width(40.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Calendar days
            val firstDayOfMonth = currentMonth.atDay(1)
            val lastDayOfMonth = currentMonth.atEndOfMonth()
            val days = mutableListOf<LocalDate>()

            // Add blanks for first week offset
            val firstWeekdayIndex = firstDayOfMonth.dayOfWeek.value % 7 // Sunday = 0
            repeat(firstWeekdayIndex) { days.add(LocalDate.MIN) }

            for (day in 1..lastDayOfMonth.dayOfMonth) {
                days.add(currentMonth.atDay(day))
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(7),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth().heightIn(max = 400.dp)
            ) {
                items(days) { date ->
                    if (date == LocalDate.MIN) {
                        Box(modifier = Modifier.size(40.dp))
                    } else {
                        val isPast = date.isBefore(today)
                        val isSelected = date == selectedDate

                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(if (isSelected) Color(0xFF4CAF50) else Color.Transparent)
                                .clickable(enabled = !isPast) { selectedDate = date }
                        ) {
                            Text(
                                text = date.dayOfMonth.toString(),
                                color = when {
                                    isSelected -> Color.White
                                    isPast -> Color.LightGray
                                    else -> DarkText
                                },
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewChooseDateEnhanced() {
    ChooseDateEnhancedScreen(navController = rememberNavController())
}
