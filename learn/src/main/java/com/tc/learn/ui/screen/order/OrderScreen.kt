package com.tc.learn.ui.screen.order

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import com.tc.learn.data.model.Location
import com.tc.learn.data.model.Teacher
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import com.tc.learn.ui.navigation.AppNavigator

// Example data class for an order (adjust if you have a real one)
data class Order(
    val id: String,
    val price: Double,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val teacher: Teacher? = null,
    val location: Location? = null,
)

@Composable
fun OrderScreen(
    navigator: AppNavigator,
    order: Order? = null,
    imageLoader: ImageLoader,
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .width(32.dp)
            ) {
                Button(onClick = { navigator.goBack() }) {
                    Text(text = "<")
                }
            }
            Text(
                text = "Order Details",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(
                modifier = Modifier
                    .height(16.dp)
            )

            if (order == null) {
                Text(
                    text = "No order selected",
                    style = MaterialTheme.typography.bodyLarge
                )
            } else {
                Text(
                    text = "Order ID: ${order.id}",
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Price: $${order.price}",
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.height(8.dp))

                val formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm")
                val start = order.startTime.format(formatter) ?: "N/A"
                val end = order.endTime.format(formatter) ?: "N/A"

                Text(
                    text = "Time: $start - $end",
                    style = MaterialTheme.typography.bodyLarge
                )
                order.teacher?.let { teacher ->
                    AsyncImage(
                        model = teacher.imageUrl,
                        contentDescription = teacher.name,
                        imageLoader = imageLoader,
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Teacher: ${teacher.name}",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Levels: ${teacher.levels.joinToString()}",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Subjects: ${teacher.subjects.joinToString()}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                order.location.let { loc ->
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Location: (${loc?.latitude}, ${loc?.longitude})",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}
