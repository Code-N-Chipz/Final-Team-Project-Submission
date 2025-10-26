package com.tc.laundry.ui.filterspage

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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RangeSlider
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
import com.tc.laundry.ui.comon.LineInfo
import com.tc.laundry.ui.comon.PrimaryButtonColour
import com.tc.laundry.ui.comon.TopBar
import theme.primaryColor

@Composable
fun FiltersPage(
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
            title = stringResource(R.string.laundry_header_filters_page),
            icon = com.tc.design.R.drawable.arrow_left_orange_icon,
            endText = stringResource(R.string.laundry_header_action_clear_filters_page),
            onClick = {
                navController.popBackStack()
            }
        )

        DropDownSection()

        Spacer(modifier = Modifier.height(24.dp))

        LineInfo(
            text = stringResource(R.string.laundry_price_kg_filters_page)
        )

        RangeSliderCompose()

        Spacer(modifier = Modifier.height(24.dp))

        LineInfo(
            text = stringResource(R.string.laundry_Rate_filters_page)
        )

        Rating()

        Spacer(
            modifier = Modifier.height(220.dp)
        )

        PrimaryButtonColour(
            text = R.string.laundry_apply_button_filters_page
        )
    }
}

@Composable
private fun Rating(
    modifier: Modifier = Modifier,
    initialRating: Int = 3,
    maxStars: Int = 5,
    onRatingChanged: (Int) -> Unit = {},
){
    var rating by remember {mutableStateOf(initialRating)}

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 1..maxStars) {
            Icon(
                painter = painterResource(
                    if(i <= rating) R.drawable.filled_star else R.drawable.star
                ),
                contentDescription = null,
                tint = if (i <= rating) primaryColor else Color.LightGray,
                modifier = Modifier
                    .size(32.dp)
                    .clickable{
                        rating = i
                        onRatingChanged(i)
                    }
            )
        }
    }

}

@Composable
private fun RangeSliderCompose(
    modifier: Modifier = Modifier
) {
    var range by remember { mutableStateOf(0f..60f) }

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
            Text(
                text = "0",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
            Text(
                text = "30",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = "60",
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
    modifier: Modifier = Modifier
){
    var expanded by remember { mutableStateOf(false) }
    var selected by remember { mutableStateOf("Recommend") }

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
            listOf(
                stringResource(R.string.laundry_sort_by_drop_down_recommend_filters_page)
            ).forEach {
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
