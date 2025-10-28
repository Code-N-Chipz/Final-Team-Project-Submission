package com.tc.babysitter.babysitterlist

import BookingViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tc.babysitter.addchild.ChildViewModel
import com.tc.babysitter.addchild.DarkText
import com.tc.ui.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderScreen(
    childViewModel: ChildViewModel = viewModel(),
    bookingViewModel: BookingViewModel = viewModel()
) {
    val child = childViewModel.childData
    val babysitter = bookingViewModel.selectedBabysitter
    val dateTime = bookingViewModel.getFormattedDateTime()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Order", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { /* TODO: Back */ }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = DarkText
                        )
                    }
                },
                actions = {
                    TextButton(onClick = { /* TODO: Cancel */ }) {
                        Text("Cancel", color = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFFF7A00)
                )
            )
        },
        bottomBar = {
            Button(
                onClick = { /* TODO: Place order */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF7A00)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Place order", fontSize = 18.sp, color = Color.White)
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Orange Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFFF7A00))
                    .padding(16.dp)
            ) {
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        // Babysitter Section
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                painter = painterResource(
                                    id = babysitter?.image ?: R.drawable.ic_profile
                                ),
                                contentDescription = babysitter?.name ?: "Babysitter",
                                modifier = Modifier
                                    .size(60.dp)
                                    .clip(CircleShape)
                            )
                            Text("Babysitter", color = Color.White, fontSize = 12.sp)
                            Text(babysitter?.name ?: "Jenny Jones", color = Color.White, fontWeight = FontWeight.Bold)
                        }

                        // Child Section
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                painter = painterResource(
                                    id = child.photoUri?.let { R.drawable.ic_child } ?: R.drawable.ic_child
                                ),
                                contentDescription = child.name,
                                modifier = Modifier
                                    .size(60.dp)
                                    .clip(CircleShape)
                            )
                            Text("Child", color = Color.White, fontSize = 12.sp)
                            Text(child.name.ifBlank { "Maya" }, color = Color.White, fontWeight = FontWeight.Bold)
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Date",
                        color = Color.White.copy(alpha = 0.8f),
                        fontSize = 13.sp
                    )
                    Text(
                        text = if (dateTime.isNotBlank()) dateTime else "20 March, Thu – 10h/13h",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_location),
                            contentDescription = "Location",
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Column {
                            Text("28 Broad Street", color = Color.White)
                            Text("Johannesburg", color = Color.White)
                        }
                    }
                }
            }

            // White Body
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text("Babysitting", fontWeight = FontWeight.Medium, fontSize = 16.sp)
                        TextButton(onClick = { /* TODO */ }) {
                            Text("Remove", color = Color(0xFFFF7A00))
                        }
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        Text("$15/h", fontSize = 16.sp, fontWeight = FontWeight.Medium)
                        Text("x3", color = Color.Gray)
                    }
                }

                Divider(modifier = Modifier.padding(vertical = 8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Subtotal", fontSize = 16.sp)
                    Text("$45.00", fontSize = 16.sp, color = Color(0xFFFF7A00))
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Delivery fees", fontSize = 16.sp, color = Color.Gray)
                    Text("$0.00", fontSize = 16.sp, color = Color.Gray)
                }

                Divider(modifier = Modifier.padding(vertical = 8.dp))

                Text(
                    text = "Total amount",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "$45.00",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFF7A00),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewOrderScreen() {
    OrderScreen()
}
