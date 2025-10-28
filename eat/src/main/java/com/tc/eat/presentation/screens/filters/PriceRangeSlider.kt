package com.tc.eat.presentation.screens.filters

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import theme.primaryColor
import theme.primaryIconColor
import theme.textTertiary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PriceRangeSlider() {
    var priceRange by remember { mutableStateOf(0f..100f) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        PriceRangeTitle()
        PriceRangeLabels()
        RangeSlider(
            value = priceRange,
            steps = 1,
            onValueChange = { range -> priceRange = range },
            valueRange = 0f..100f,
            onValueChangeFinished = {},
            colors = SliderDefaults.colors(
                thumbColor = primaryColor,
                activeTrackColor = primaryColor
            ),
            startThumb = {
                SliderThumb()
            },
            endThumb = {
                SliderThumb()
            },
            track = { rangeSliderState ->
                SliderDefaults.Track(
                    rangeSliderState = rangeSliderState,
                    modifier = Modifier.scale(
                        scaleY = 0.25f,
                        scaleX = 1.0f
                    ), // Example: reduce track height
                    colors = SliderDefaults.colors(
                        activeTrackColor = primaryColor,
                        inactiveTrackColor = textTertiary
                    )
                )
            }
        )
    }
}

@Composable
private fun PriceRangeTitle() {
    Row(
        modifier = Modifier.padding(vertical = 32.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(com.tc.eat.R.string.price),
            color = textTertiary
        )
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
            thickness = 1.dp,
            color = textTertiary
        )
    }
}

@Composable
private fun PriceRangeLabels() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(com.tc.eat.R.string.low_price),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = stringResource(com.tc.eat.R.string.mid_price),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = stringResource(com.tc.eat.R.string.hi_price),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun SliderThumb() {
    Box(
        modifier = Modifier
            .size(32.dp)
            .background(color = primaryColor, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(16.dp)
                .background(color = primaryIconColor, shape = CircleShape)
                .padding(2.dp)
        )
    }
}
