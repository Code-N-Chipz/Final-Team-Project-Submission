package com.tc.laundry.ui.yourlaundrypage

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tc.laundry.R
import com.tc.laundry.ui.comon.TopBar
import com.tc.laundry.ui.comon.LineInfo
import com.tc.laundry.ui.comon.PrimaryButtonColour
import theme.primaryColor
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun YourLaundryPage(
    modifier: Modifier = Modifier,
    navController: NavController
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        TopBar(
            icon = com.tc.design.R.drawable.home_icon,
            title = stringResource(R.string.laundry_header_your_laundry_page),
            onClick = {
                navController.popBackStack()
            }
        )

        LineInfo(
            text = stringResource(R.string.laundry_laundry_kg_your_laundry_page)
        )

        RangeSliderCompose()

        Spacer(modifier = Modifier.height(24.dp))

        DropDownSection()

        Spacer(modifier = Modifier.height(24.dp))

        LineInfo(
            text = stringResource(R.string.laundry_ironing_your_laundry_page)
        )

        IroningSection()

        Spacer(modifier = Modifier.height(24.dp))

        LineInfo(
            text = stringResource(R.string.laundry_availability_your_laundry_page)
        )

        Availability()

        Spacer(modifier = Modifier.height(40.dp))


        PrimaryButtonColour(
            onClick = {navController.navigate("filters")},
            text = R.string.laundry_next_button_your_laundry_page
        )
    }
}

@Composable
fun Availability(
    modifier: Modifier = Modifier,
    onHourSelected: (Int) -> Unit = {}
) {
    val hours = listOf(8, 11, 14, 17, 20)
    var selectedHour by remember { mutableStateOf(14) } // default at 14h

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Hour labels above the slider
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            hours.forEach {
                val isSelected = it == selectedHour
                Text(
                    text = "${it}h",
                    color = if (isSelected) Color.Black else Color.Gray,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                    fontSize = if (isSelected) 16.sp else 14.sp
                )
            }
        }

        // Slider that snaps to these five hours
        Slider(
            value = hours.indexOf(selectedHour).toFloat(),
            onValueChange = { value ->
                val index = value.toInt().coerceIn(0, hours.lastIndex)
                selectedHour = hours[index]
                onHourSelected(hours[index])
            },
            valueRange = 0f..(hours.size - 1).toFloat(),
            steps = hours.size - 2, // 5 points = 3 steps between
            colors = SliderDefaults.colors(
                thumbColor = primaryColor,
                activeTrackColor = Color.LightGray,
                inactiveTrackColor = Color.LightGray
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun IroningSection(
    modifier: Modifier = Modifier
){
    var selected by remember{ mutableStateOf(true) }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .selectable(
                    selected = selected,
                    onClick = {
                        selected = true
                    }
                )
        ){
            Text(
                text = stringResource(R.string.laundry_ironing_yes_your_laundry_page),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )

            Checkbox(
                checked = selected,
                onCheckedChange = {
                    selected = true
                },
                colors = CheckboxDefaults.colors(
                    checkmarkColor = primaryColor,
                    checkedColor = Color.Transparent,
                    uncheckedColor = Color.Gray,
                    disabledUncheckedColor = Color.LightGray,
                    disabledCheckedColor = Color.LightGray
                )
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .selectable(
                    selected = !selected,
                    onClick = {
                        selected = false
                    }
                )
        ) {
            Text(
                text = stringResource(R.string.laundry_ironing_no_your_laundry_page),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )

            Checkbox(
                checked = !selected,
                onCheckedChange = {
                    selected = false
                },
                colors = CheckboxDefaults.colors(
                    checkmarkColor = primaryColor,
                    checkedColor = Color.Transparent,
                    uncheckedColor = Color.Gray,
                    disabledUncheckedColor = Color.LightGray,
                    disabledCheckedColor = Color.LightGray
                )
            )
        }
    }
}

@Composable
private fun DropDownSection(
    modifier: Modifier = Modifier
){
    var expanded by remember { mutableStateOf(false) }
    var selected by remember { mutableStateOf("2") }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        OutlinedTextField(
            value = selected,
            onValueChange = {},
            readOnly = true,
            label = {
                Text(
                    text = stringResource(R.string.laundry_dry_cleaning_your_laundry_page),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.LightGray
                ) },
            modifier = Modifier
                .fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = primaryColor,
                unfocusedBorderColor = Color.LightGray,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            ),
            trailingIcon = {
                IconButton(
                    onClick = { expanded = !expanded }
                ) {
                    Icon(
                        painter = painterResource(com.tc.design.R.drawable.down_arrow_grey_icon),
                        contentDescription = null,
                        tint = primaryColor
                    )
                }
            }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            listOf("1", "2", "3", "4").forEach {
                DropdownMenuItem(
                    text = { Text(it) },
                    onClick = {
                        selected = it
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
private fun RangeSliderCompose(
    modifier: Modifier = Modifier
) {
    var range by remember { mutableStateOf(0f..5f) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        // Label row above slider (0 — 5 — 10)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "0",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
            Text(
                text = "5",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = "10",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.LightGray
            )
        }

        // Wrap slider + start dot
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(36.dp) // gives space for the slider and dot
        ) {
            // fixed orange start dot
            androidx.compose.foundation.Canvas(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 8.dp)
                    .width(12.dp)
                    .height(12.dp)
            ) {
                drawCircle(color = primaryColor)
            }

            // actual slider
            RangeSlider(
                value = range,
                onValueChange = { range = it },
                valueRange = 0f..10f,
                steps = 9,
                colors = SliderDefaults.colors(
                    thumbColor = primaryColor,
                    activeTrackColor = primaryColor,
                    inactiveTrackColor = Color.LightGray,
                    activeTickColor = Color.Transparent,
                    inactiveTickColor = Color.Transparent
                ),
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
            )
        }
    }
}
