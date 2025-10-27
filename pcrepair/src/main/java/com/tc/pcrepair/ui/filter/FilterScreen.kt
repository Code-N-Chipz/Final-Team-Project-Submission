package com.tc.pcrepair.ui.filter

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tc.pcrepair.data.FiltersUiState
import kotlin.math.max
import kotlin.math.roundToInt

@Composable
fun FiltersScreen(
    viewModel: FilterViewModel,
    modifier: Modifier = Modifier,
    onApply: (FiltersUiState) -> Unit = {}
) {
    val state by viewModel.uiState.collectAsState()
    Column (modifier = modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Filters", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF0B3A66))
        Spacer(modifier = Modifier.height(12.dp))

        // Sort by dropdown (simple)
        var expanded by remember { mutableStateOf(false) }
        val options = listOf("Recommend", "Price: Low to High", "Price: High to Low", "Rating")
        Box {
            OutlinedTextField(
                value = state.sortBy,
                onValueChange = {},
                label = { Text("Sort by") },
                readOnly = true,
                trailingIcon = {
                    IconButton (onClick = { expanded = !expanded }) {
                        Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Sort")
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
            DropdownMenu (expanded = expanded, onDismissRequest = { expanded = false }) {
                options.forEach { opt ->
                    DropdownMenuItem(text = { Text(opt) }, onClick = { viewModel.setSort(opt); expanded = false })
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Price/hour slider
//        Text(text = "Price / hour", color = Color.Gray)
//        Spacer(modifier = Modifier.height(8.dp))
//        var price by remember { mutableStateOf(state.pricePerHour) }
//        Slider(
//            value = price,
//            onValueChange = { price = it },
//            onValueChangeFinished = { viewModel.setPrice(price) },
//            valueRange = 0f..60f,
//            steps = 5,
//            colors = SliderDefaults.colors(thumbColor = Color(0xFFFF7A00), activeTrackColor = Color(0xFFFFC59A)),
//            modifier = Modifier.fillMaxWidth()
//        )
//        Text(text = "£${price.toInt()}/h", modifier = Modifier.padding(top = 6.dp))
//
        DiscretePricePerHourSlider (
            hourRate = state.availablePricePerHour,
            selectedIndex = state.selectedPricePerHourIndex,
            modifier = Modifier.fillMaxWidth(),
            onIndexChange = { viewModel.selectPricePerHour(it) }
        )


        Spacer(modifier = Modifier.height(16.dp))

        // Rating selector (stars)
        Text(text = "Rating", color = Color.Gray)
        Spacer(modifier = Modifier.height(8.dp))
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            for (i in 1..5) {
                val filled = i <= state.rating
                IconToggleButton (
                    checked = filled,
                    onCheckedChange = { checked -> if (checked) viewModel.setRating(i) else if (state.rating == i) viewModel.setRating(i - 1) },
                    modifier = Modifier.size(36.dp)
                ) {
                    Icon(
                        imageVector = if (filled) Icons.Default.Star else Icons.Default.Star,
                        contentDescription = null,
                        tint = if (filled) Color(0xFFFF7A00) else Color.Gray
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button (
            onClick = { viewModel.apply(onApplied = { onApply(it) }) },
            modifier = Modifier.fillMaxWidth().height(52.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7A00))
        ) {
            Text(text = "Apply", color = Color.White)
        }
    }
}


@Composable
fun DiscretePricePerHourSlider(
    hourRate: List<Int>,
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
    val count = max(1, hourRate.size - 1)

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
            for (i in hourRate.indices) {
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
                .pointerInput(hourRate, selectedIndex, widthPx) {
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
            hourRate.forEachIndexed { idx, t ->
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
            hourRate.forEachIndexed { idx, _ ->
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

@Preview(showBackground = true, name = "Filters Screen Preview")
@Composable
fun FiltersScreenPreview() {
    val vm = remember { FilterViewModel(null) }
    Surface {
        FiltersScreen(viewModel = vm, onApply = {})
    }
}