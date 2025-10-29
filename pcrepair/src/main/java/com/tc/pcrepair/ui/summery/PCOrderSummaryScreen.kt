package com.tc.pcrepair.ui.summery

import androidx.compose.foundation.Image
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
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tc.pcrepair.R

//@Composable
//fun OrderSummaryScreen(
//    viewModel: OrderSummaryViewModel = viewModel(),
//    profileResId: Int = R.drawable.jenny_johns_round,
//    onPlaceOrder: () -> Unit = {}
//) {
//    val state by viewModel.uiState.collectAsState()
//
//    Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFFFF7A00)) {
//        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
//            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
//                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
//                Text("Order", color = Color.White, fontWeight = FontWeight.Bold)
//                Text("Cancel", color = Color.White)
//            }
//
//            Spacer(modifier = Modifier.height(24.dp))
//            Row(verticalAlignment = Alignment.CenterVertically) {
//                Image(
//                    painter = painterResource(id = profileResId),
//                    contentDescription = "Jenny Jones",
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier
//                        .size(48.dp)
//                        .clip(CircleShape)
//                        .background(Color.Gray)
//                )
//                Spacer(modifier = Modifier.width(12.dp))
//                Column {
//                    Text(state.mechanicName, color = Color.White, fontWeight = FontWeight.Bold)
//                    Text(state.date, color = Color.White)
//                }
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//            Row(verticalAlignment = Alignment.CenterVertically) {
//                Icon(Icons.Default.LocationOn, contentDescription = null, tint = Color.White)
//                Spacer(modifier = Modifier.width(8.dp))
//                Text(state.location, color = Color.White)
//            }
//
//            Spacer(modifier = Modifier.height(56.dp))
//            Card (
//                modifier = Modifier.fillMaxWidth(),
//                shape = RoundedCornerShape(12.dp)
//            ) {
//                Column(modifier = Modifier.padding(16.dp)) {
//                    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
//                        Text("Mechanic", fontWeight = FontWeight.Bold)
//                        Text("$${state.ratePerHour}/h", fontWeight = FontWeight.Bold)
//                    }
//                    Spacer(modifier = Modifier.height(8.dp))
//                    Text("Remove x${state.hours}", color = Color.Red)
//
//                    Spacer(modifier = Modifier.height(12.dp))
//                    Text("Subtotal: $${"%.2f".format(state.deliveryFee)}", fontWeight = FontWeight.Medium)
//
//                    Spacer(modifier = Modifier.height(12.dp))
//                    Text("Total amount", fontWeight = FontWeight.Bold)
//                    Text(
//                        "$${"%.2f".format(state.total)}",
//                        fontSize = 24.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = Color(0xFFFF7A00)
//                    )
//                }
//            }
//
//            Spacer(modifier = Modifier.weight(1f))
//
//            // 🧾 Place order button
//            Button(
//                onClick = { viewModel.placeOrder(onPlaceOrder) },
//                modifier = Modifier.fillMaxWidth().height(52.dp),
//                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
//            ) {
//                Text("Place order", color = Color(0xFFFF7A00), fontWeight = FontWeight.Bold)
//            }
//        }
//    }
//}


@Composable
fun PCOrderSummaryScreen(
    viewModel: PCOrderSummaryViewModel = viewModel (),
    profileImageRes: Int = R.drawable.jenny,
    onPlaceOrder: () -> Unit = {}
) {
    val state by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        // 🔶 Top Orange Section (Fixed Height)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .background(Color(0xFFFF7A00))
        ) {
            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    Text("Order", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Text("Cancel", color = Color.White, fontSize = 14.sp)
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = profileImageRes),
                        contentDescription = "Profile",
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(Color.Gray),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(state.role, color = Color.White, fontSize = 12.sp)
                        Text(state.mechanicName, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))
                Text(state.date, color = Color.White, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.LocationOn, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(state.location, color = Color.White, fontSize = 14.sp)
                }
            }
        }

        // ⚪ Bottom White Section (Fills Remaining Space)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(horizontal = 16.dp, vertical = 20.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Mechanic", fontWeight = FontWeight.Bold)
                Text("$${state.ratePerHour}/h", fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Remove", color = Color.Red)
                Text("x${state.hours}", color = Color.Gray)
            }

            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Subtotal", color = Color.Black)
                Text("$${"%.2f".format(state.subtotal)}", fontWeight = FontWeight.Medium)
            }

            Spacer(modifier = Modifier.height(4.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Delivery fees", color = Color.Gray)
                Text("$${"%.2f".format(state.deliveryFee)}", fontWeight = FontWeight.Medium)
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text("Total amount", color = Color.Gray)
            Text(
                "$${"%.2f".format(state.total)}",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFF7A00)
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { viewModel.placeOrder(onPlaceOrder) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7A00))
            ) {
                Text("Place order", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}




@Preview
@Composable
fun PCOderSummeryPreview(){
    Surface {
        PCOrderSummaryScreen()
    }

}