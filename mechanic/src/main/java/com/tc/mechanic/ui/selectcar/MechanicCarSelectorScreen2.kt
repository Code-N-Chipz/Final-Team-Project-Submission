package com.tc.mechanic.ui.selectcar

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tc.mechanic.data.MechanicFormState
import kotlin.math.max
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DropdownField(
    label: String,
    selected: String,
    options: List<String>,
    onSelect: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        OutlinedTextField(
            value = selected,
            onValueChange = {},
            label = { Text(label) },
            readOnly = true,
            trailingIcon = {
                IconButton (onClick = { expanded = !expanded }) {
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Dropdown")
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onSelect(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun MechanicFormScreenWithDropdowns(
    viewModel: MechanicCarSelectorViewModel,
    typeOptions: List<String>,
    modelOptions: List<String>,
    yearOptions: List<String>,
    motorOptions: List<String>,
    onNext: (MechanicFormState) -> Unit,
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsState()

    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Your mechanic", textAlign = TextAlign.Center ,fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF0B3A66))
        Spacer(modifier = Modifier.height(12.dp))

        DropdownField(
            label = "Type",
            selected = state.type,
            options = typeOptions,
            onSelect = viewModel::updateType,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        DropdownField(
            label = "Model",
            selected = if (state.model.isNotEmpty()) state.model else "Select model",
            options = modelOptions,
            onSelect = viewModel::updateModel,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        DropdownField(
            label = "Year",
            selected = if (state.year.isNotEmpty()) state.year else "Select year",
            options = yearOptions,
            onSelect = viewModel::updateYear,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        DropdownField(
            label = "Motor",
            selected = if (state.motor.isNotEmpty()) state.motor else "Select motor",
            options = motorOptions,
            onSelect = viewModel::updateMotor,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Availability", color = Color.Gray)
        Spacer(modifier = Modifier.height(12.dp))

        DiscreteTimeSlider(
            times = state.availableTimes,
            selectedIndex = state.selectedTimeIndex,
            modifier = Modifier.fillMaxWidth(),
            onIndexChange = { viewModel.selectTimeIndex(it) }
        )

        /**
        // Slider: positions correspond to indices in availableTimes
        val times = state.availableTimes
        val count = (times.size - 1).coerceAtLeast(1)
        var sliderPosition by remember { mutableStateOf(state.selectedTimeIndex.toFloat()) }

        // sync initial position from state (use LaunchedEffect to avoid overwriting during drag)
        LaunchedEffect(state.selectedTimeIndex) { sliderPosition = state.selectedTimeIndex.toFloat() }

        Column(modifier = Modifier.fillMaxWidth()) {
            // Slider with custom colors
            Slider(
                value = sliderPosition,
                onValueChange = { sliderPosition = it },
                onValueChangeFinished = {
                    // snap to nearest index
                    val nearest = sliderPosition.roundToInt().coerceIn(0, count)
                    sliderPosition = nearest.toFloat()
                    viewModel.selectTimeIndex(nearest)
                },
                valueRange = 0f..count.toFloat(),
                steps = count - 1,
                modifier = Modifier.fillMaxWidth(),
                colors = SliderDefaults.colors(thumbColor = Color(0xFFFF7A00), activeTrackColor = Color(0xFFFFC59A))
            )

            Spacer(modifier = Modifier.height(8.dp))

            // labels below slider (evenly spaced)
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                times.forEachIndexed { index, t ->
                    Text(text = "${t}h", color = if (index == state.selectedTimeIndex) Color(0xFFFF7A00) else Color.Gray, fontSize = 12.sp)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Current selected time badge
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFFF7A00))
                    .padding(horizontal = 12.dp, vertical = 6.dp)
                    .align(Alignment.Start)
            ) {
                Text(text = "Selected: ${times[state.selectedTimeIndex]}h", color = Color.White, fontSize = 14.sp)
            }
        }

        Spacer(modifier = Modifier.weight(1f))
**/
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

        Spacer(modifier = Modifier.weight(1f))
        state.error?.let { Text(text = it, color = Color.Red) }

        Button (
            onClick = { viewModel.submit(onSuccess = { onNext(it) }) },
            modifier = Modifier.fillMaxWidth().height(52.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7A00))
        ) {
            Text(text = "Next", color = Color.White)
        }
    }
}

@Composable
fun DiscreteTimeSlider(
    times: List<Int>,
    selectedIndex: Int,
    modifier: Modifier = Modifier,
    trackHeight: Dp = 4.dp,
    thumbSize: Dp = 28.dp,
    activeColor: Color = Color(0xFFFF7A00),
    inactiveColor: Color = Color(0xFFE6E6E6),
    onIndexChange: (Int) -> Unit
) {
    val density = LocalDensity.current
    var widthPx by remember { mutableStateOf(0f) }
    val count = max(1, times.size - 1)

    // compute fraction from index
    val fraction = remember(selectedIndex, count) {
        if (count == 0) 0f else selectedIndex.toFloat() / count.toFloat()
    }

    Box(modifier = modifier.height(thumbSize + 32.dp)) {
        // Track + active portion + ticks
        Canvas (modifier = Modifier
            .fillMaxWidth()
            .height(trackHeight)
            .align(Alignment.Center)
            .onGloballyPositioned { coords ->
                widthPx = coords.size.width.toFloat()
            }
        ) {
            val startX = 0f
            val endX = size.width
            val centerY = size.height / 2f

            // inactive track
            drawLine(
                color = inactiveColor,
                start = Offset(startX, centerY),
                end = Offset(endX, centerY),
                strokeWidth = with(density) { trackHeight.toPx() },
                cap = Stroke.DefaultCap
            )

            // active track
            val activeEndX = if (count == 0) startX else startX + fraction * (endX - startX)
            drawLine(
                color = activeColor,
                start = Offset(startX, centerY),
                end = Offset(activeEndX, centerY),
                strokeWidth = with(density) { trackHeight.toPx() },
                cap = Stroke.DefaultCap
            )

            // ticks
            for (i in times.indices) {
                val x = if (count == 0) startX else startX + (i.toFloat() / count.toFloat()) * (endX - startX)
                val tickRadius = with(density) { 4.dp.toPx() }
                drawCircle(
                    color = if (i <= selectedIndex) activeColor else inactiveColor,
                    radius = tickRadius,
                    center = Offset(x, centerY)
                )
            }
        }

        // Thumb (draggable)
        var dragging by remember { mutableStateOf(false) }
        val thumbX by remember {
            derivedStateOf {
                // position thumb according to fraction and widthPx
                val px = if (count == 0) 0f else fraction * (widthPx)
                px
            }
        }

        // interactive area for drag/tap
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(thumbSize + 20.dp)
                .align(Alignment.Center)
                .pointerInput(times, selectedIndex, widthPx) {
                    detectDragGestures (
                        onDragStart = { dragging = true },
                        onDragEnd = { dragging = false },
                        onDragCancel = { dragging = false },
                        onDrag = { change, dragAmount ->
                            change.consume()
                            if (widthPx <= 0f) return@detectDragGestures
                            val newPx = (thumbX + dragAmount.x).coerceIn(0f, widthPx)
                            val ratio = if (count == 0) 0f else newPx / widthPx
                            val nearest = (ratio * count).roundToInt().coerceIn(0, count)
                            onIndexChange(nearest)
                        }
                    )
                }
                .clickable {
                    // handle taps: compute index from tap position
                    // clickable provides no coordinates here; use pointerInput for taps if needed
                }
        ) {
            // Place thumb Box using offset
            Box(
                modifier = Modifier
                    .offset {
                        // center the thumb at thumbX
                        val x = (thumbX - with(density) { thumbSize.toPx() / 2f }).roundToInt().coerceAtLeast(0)
                        IntOffset(x, 0)
                    }
                    .size(thumbSize)
                    .align(Alignment.CenterStart)
                    .clip(CircleShape)
                    .background(activeColor)
            )
        }

//        Spacer(modifier = Modifier.weight(1f))
//        Spacer(modifier = Modifier.height(20.dp).fillMaxWidth())
        // labels row

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = thumbSize + 20.dp)
                .align(Alignment.TopStart),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            times.forEachIndexed { idx, t ->
                val selected = idx == selectedIndex
                Text(
                    text = "${t}h",
                    fontSize = 12.sp,
                    color = if (selected) activeColor else Color.Gray,
                    fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal
                )
            }
        }

        // make the ticks tappable by overlaying a Row of clickable areas
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(thumbSize + 20.dp)
            .align(Alignment.Center)
        ) {
            times.forEachIndexed { idx, _ ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .pointerInput(idx, widthPx) {
                            detectDragGestures(
                                onDragStart = {},
                                onDragEnd = {},
                                onDragCancel = {},
                                onDrag = { change, _ -> change.consume() } // swallow drag so outer detects thumb drags
                            )
                        }
                        .clickable { onIndexChange(idx) }
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Mechanic Form Dropdowns Preview")
@Composable
fun MechanicFormDropdownsPreview() {
    val vm = remember { MechanicCarSelectorViewModel() }

    // preview options
    val typeOptions = listOf("Car", "Motorbike", "Van", "Truck")
    val modelOptions = listOf("Lexus", "Toyota", "Honda", "Ford", "BMW")
    val yearOptions = (2000..2025).map { it.toString() }.reversed().take(10) // recent 10 years
    val motorOptions = listOf("Gasoil", "Petrol", "Electric", "Hybrid")

    // prefill some values for preview
    LaunchedEffect (Unit) {
        vm.updateModel("Lexus")
        vm.updateYear("2016")
        vm.updateMotor("Gasoil")
    }

    Surface {
        MechanicFormScreenWithDropdowns(
            viewModel = vm,
            typeOptions = typeOptions,
            modelOptions = modelOptions,
            yearOptions = yearOptions,
            motorOptions = motorOptions,
            onNext = {}
        )
    }
}