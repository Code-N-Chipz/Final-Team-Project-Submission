package com.tc.laundry.ui.filterspage

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.tc.laundry.ui.comon.LineInfo
import com.tc.laundry.ui.comon.PrimaryButtonColour
import com.tc.laundry.ui.comon.TopBar
import theme.primaryColor

@Composable
fun FiltersPage(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    var selectedSort by remember { mutableStateOf("Recommend") }
    var rating by remember { mutableStateOf(3) }
    var range by remember { mutableStateOf(0f..60f) }

    fun resetFilters() {
        selectedSort = "Recommend"
        rating = 3
        range = 0f..60f
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        TopBar(
            title = stringResource(R.string.laundry_header_filters_page),
            icon = com.tc.design.R.drawable.arrow_left_orange_icon,
            endText = stringResource(R.string.laundry_header_action_clear_filters_page),
            onClick = {
                navController.popBackStack()
            },
            onEndClick = {
                resetFilters()
            }
        )

        DropDownSection(
            selected = selectedSort,
            onSelect = { selectedSort = it }
        )

        Spacer(modifier = Modifier.height(24.dp))

        LineInfo(
            text = stringResource(R.string.laundry_price_kg_filters_page)
        )

        RangeSliderCompose(
            range = range,
            onRangeChange = { range = it }
        )

        Spacer(modifier = Modifier.height(24.dp))

        LineInfo(
            text = stringResource(R.string.laundry_Rate_filters_page)
        )

        Rating(
            initialRating = rating,
            onRatingChanged = { rating = it }
        )

        Spacer(modifier = Modifier.height(220.dp))

        PrimaryButtonColour(
            text = R.string.laundry_apply_button_filters_page,
            onClick = { navController.navigate("home") }
        )
    }
}

@Composable
private fun Rating(
    modifier: Modifier = Modifier,
    initialRating: Int = 3,
    maxStars: Int = 5,
    onRatingChanged: (Int) -> Unit = {},
) {
    var rating by remember { mutableStateOf(initialRating) }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 1..maxStars) {
            Icon(
                painter = painterResource(
                    if (i <= rating) R.drawable.filled_star else R.drawable.star
                ),
                contentDescription = null,
                tint = if (i <= rating) primaryColor else Color.LightGray,
                modifier = Modifier
                    .size(32.dp)
                    .clickable {
                        rating = i
                        onRatingChanged(i)
                    }
            )
        }
    }
}

@Composable
private fun RangeSliderCompose(
    modifier: Modifier = Modifier,
    range: ClosedFloatingPointRange<Float>,
    onRangeChange: (ClosedFloatingPointRange<Float>) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        // Label row above slider (0 — 30 — 60)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("0", fontSize = 14.sp, fontWeight = FontWeight.Medium)
            Text("30", fontSize = 14.sp, fontWeight = FontWeight.Bold)
            Text("60", fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color.LightGray)
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(36.dp)
        ) {
            // orange start dot
            Canvas(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 8.dp)
                    .size(12.dp)
            ) {
                drawCircle(color = primaryColor)
            }

            // actual slider
            RangeSlider(
                value = range,
                onValueChange = { onRangeChange(it) },
                valueRange = 0f..60f,
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

@Composable
private fun DropDownSection(
    modifier: Modifier = Modifier,
    selected: String,
    onSelect: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

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
                )
            },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = primaryColor,
                unfocusedBorderColor = Color.LightGray,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            ),
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
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
            listOf(
                stringResource(R.string.laundry_sort_by_drop_down_recommend_filters_page),
                "Newest",
                "Top Rated"
            ).forEach {
                DropdownMenuItem(
                    text = { Text(it) },
                    onClick = {
                        onSelect(it)
                        expanded = false
                    }
                )
            }
        }
    }
}
