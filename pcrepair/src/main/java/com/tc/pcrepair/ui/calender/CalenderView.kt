package com.tc.pcrepair.ui.calender

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun CalenderView(
    viewModel: CalenderViewModel,
    modifier: Modifier = Modifier,
//    onConfirmed: (LocalDate, String) -> Unit = { _, _ -> }
    onConfirmed: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()
    val isPreview = LocalInspectionMode.current
    val dateFmt = remember { DateTimeFormatter.ofPattern("EEE\ndd", Locale.getDefault()) }

    Column (modifier = modifier.fillMaxSize().padding(16.dp)) {
        // Header
        Text(text = "Jenny Jones", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF0B3A66))
        Spacer(modifier = Modifier.height(12.dp))

        // Horizontal date picker
        LazyRow (
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            itemsIndexed(state.dates) { index, date ->
                val selected = index == state.selectedDateIndex
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .width(64.dp)
                        .clickable { viewModel.selectDate(index) }
                ) {
                    Text(
                        text = date.format(DateTimeFormatter.ofPattern("EEE", Locale.getDefault())),
                        fontSize = 12.sp,
                        color = if (selected) Color(0xFFFF7A00) else Color.Gray
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Box(
                        modifier = Modifier
                            .size(42.dp)
                            .clip(CircleShape)
                            .background(if (selected) Color(0xFFFF7A00) else Color(0xFFF0F0F0)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = date.format(DateTimeFormatter.ofPattern("d", Locale.getDefault())), color = if (selected) Color.White else Color.Black, fontWeight = FontWeight.SemiBold)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Times list (vertical, scrollable)
        Text(text = "Available times", fontWeight = FontWeight.Medium, color = Color.Gray)
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn (
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth().weight(1f)
        ) {
            itemsIndexed(state.timesForSelectedDate) { idx, time ->
                val selected = state.selectedTimeIndex == idx
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(if (selected) Color(0xFFFFF0E8) else Color(0xFFF8F8F8))
                        .clickable { viewModel.selectTime(idx) }
                        .padding(horizontal = 16.dp, vertical = 14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = time, fontSize = 16.sp, color = if (selected) Color(0xFFFF7A00) else Color.Black, modifier = Modifier.weight(1f))
                    if (selected) {
                        Text(text = "Selected", color = Color(0xFFFF7A00), fontSize = 12.sp)
                    }
                }
            }
        }

        state.error?.let { Text(text = it, color = Color.Red, modifier = Modifier.padding(vertical = 8.dp)) }

        Button (
            onClick =  onConfirmed,
            modifier = Modifier.fillMaxWidth().height(52.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7A00))
        ) {
            Text(text = if (state.loading) "Confirming..." else "Confirm", color = Color.White)
        }
    }
}


@Preview(showBackground = true, name = "Schedule Screen Preview")
@Composable
fun CalenderPreview() {
    val vm = remember { CalenderViewModel(null) }
    Surface {
//        CalenderView(viewModel = vm, onConfirmed = { d, t -> /* preview */ })
        CalenderView(vm, onConfirmed = {})
    }
}

