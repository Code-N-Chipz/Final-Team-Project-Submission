//package com.tc.mechanic.ui.selectcar
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.tc.mechanic.data.MechanicFormState
//
//
//@Composable
//fun MechanicCarSelectorScreen(
//    viewModel: MechanicCarSelectorViewModel,
//    onNext: (MechanicFormState) -> Unit,
//    modifier: Modifier = Modifier
//) {
//    val state by viewModel.uiState.collectAsState()
//
//    Column (
//        modifier = modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    ) {
//        // Top label / title
//        Text(text = "Your mechanic", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF0B3A66))
//        Spacer(modifier = Modifier.height(12.dp))
//
//        // Form fields
//        OutlinedTextField(
//            value = state.type,
//            onValueChange = viewModel::updateType,
//            label = { Text("Type") },
//            modifier = Modifier.fillMaxWidth()
//        )
//        Spacer(modifier = Modifier.height(8.dp))
//
//        OutlinedTextField(
//            value = state.model,
//            onValueChange = viewModel::updateModel,
//            label = { Text("Model") },
//            modifier = Modifier.fillMaxWidth()
//        )
//        Spacer(modifier = Modifier.height(8.dp))
//
//        OutlinedTextField(
//            value = state.year,
//            onValueChange = viewModel::updateYear,
//            label = { Text("Year") },
//            modifier = Modifier.fillMaxWidth()
//        )
//        Spacer(modifier = Modifier.height(8.dp))
//
//        OutlinedTextField(
//            value = state.motor,
//            onValueChange = viewModel::updateMotor,
//            label = { Text("Motor") },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(20.dp))
//
//        // Availability header
//        Text(text = "Availability", fontWeight = FontWeight.Medium, color = Color.Gray)
//        Spacer(modifier = Modifier.height(12.dp))
//
//        // Time slots row (selectable chips)
//        Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
//            state.availableTimes.forEach { time ->
//                val selected = time == state.selectedTime
//                Box(
//                    modifier = Modifier
//                        .clip(RoundedCornerShape(20.dp))
//                        .background(if (selected) Color(0xFFFF7A00) else Color(0xFFF0F0F0))
//                        .clickable { viewModel.selectTime(time) }
//                        .padding(horizontal = 14.dp, vertical = 10.dp)
//                ) {
//                    Text(text = "${time}h", color = if (selected) Color.White else Color.DarkGray)
//                }
//            }
//        }
//
//        Spacer(modifier = Modifier.height(20.dp))
//        state.error?.let { Text(text = it, color = Color.Red) }
//
//        Spacer(modifier = Modifier.weight(1f))
//
//        Button (
//            onClick = { viewModel.submit(onSuccess = { onNext(it) }) },
//            modifier = Modifier.fillMaxWidth().height(52.dp),
//            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7A00))
//        ) {
//            Text(text = "Next", color = Color.White)
//        }
//    }
//}
//
//@Preview(showBackground = true, name = "Mechanic Form Preview")
//@Composable
//fun MechanicFormPreview() {
//    // simple viewmodel instance for preview
//    val vm = remember { MechanicCarSelectorViewModel() }
//    // prefill some fields for preview
//    LaunchedEffect (Unit) {
//        vm.updateModel("Lexus")
//        vm.updateYear("2016")
//        vm.updateMotor("Gasoil")
//        vm.selectTime(14)
//    }
//    MechanicCarSelectorScreen(viewModel = vm, onNext = {})
//}