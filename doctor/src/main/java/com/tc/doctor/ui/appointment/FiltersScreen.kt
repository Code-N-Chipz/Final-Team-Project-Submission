package com.tc.doctor.ui.appointment

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import theme.primaryColor
import theme.textTertiary
import com.tc.design.R as CoreDraw

@Composable
fun FiltersScreen() {
    Column(
        verticalArrangement = Arrangement.spacedBy(15.dp),
        modifier = Modifier
            .padding(15.dp)
            .fillMaxSize()
    ) {
        TopBar()
        CitySelect()
        DistanceSet()
        SpecialtiesSet()
        LanguageSelect()
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Button(onClick = {}) { Text("Save") }
        }
    }
}


@Composable
private fun TopBar() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        IconButton(onClick = {}) {
            Icon(
                painter = painterResource(CoreDraw.drawable.arrow_left_orange_icon),
                contentDescription = "Back Button"
            )
        }
        Text(
            text = "Filters",
            style = theme.typography.titleLarge
        )
        Spacer(modifier = Modifier.padding(24.dp))
    }
}

@Composable
private fun CitySelect(
    initial: String = "",
    onCityChange: (String) -> Unit = {}
) {
    var city by remember { mutableStateOf(initial) }

    OutlinedTextField(
        value = city,
        onValueChange = {
            city = it
            onCityChange(it)
        },
        label = { Text("City") },
        singleLine = true,
        trailingIcon = {
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(CoreDraw.drawable.location_crosshair_icon_gray),
                    contentDescription = "My Location"
                )
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DistanceSet() {
    var distance by remember { mutableStateOf(50f) }

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text("Distance", style = theme.typography.bodyMedium.copy(color = textTertiary))
            HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)
        }
        Spacer(modifier = Modifier.padding(vertical = 5.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text("1 km", style = theme.typography.bodyMedium.copy(color = textTertiary))
            Text("$distance km", style = theme.typography.bodyMedium)
            Text("+100 km", style = theme.typography.bodyMedium.copy(color = textTertiary))
        }


        Slider(
            value = distance,
            onValueChange = { distance = it },
            valueRange = 1f..100f,
            steps = 0,
            modifier = Modifier
                .fillMaxWidth(),
            thumb = { sliderState ->
                Box(
                    modifier = Modifier
                        .size(25.dp)
                        .background(color = theme.primaryIconColor, shape = CircleShape)
                        .border(width = 5.dp, color = primaryColor, shape = CircleShape)
                )
            },
            track = { sliderState ->
                SliderDefaults.Track(
                    sliderState = sliderState,
                    colors = SliderDefaults.colors(
                        activeTrackColor = theme.textTertiary,
                        inactiveTrackColor = theme.textTertiary
                    ),
                    modifier = Modifier
                        .height(2.dp)
                )
            }
        )
    }
}

@Composable
private fun SpecialtiesSet() {
    val specialties: List<String> = listOf(
        "All", "Allergist", "Anesthesiologist",
        "Cardiologist",
        "Dermatologist",
        "Endocrinologist", "ENT (Otolaryngologist)",
        "Family Physician",
        "Gastroenterologist", "Geriatrician", "Gynecologist",
        "Hematologist",
        "Infectious Disease Specialist", "Internist",
        "Nephrologist", "Neurologist", "Neurosurgeon",
        "Obstetrician", "Oncologist")
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text("Specialties", style = theme.typography.bodyMedium.copy(color = textTertiary))
            HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)
        }
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
            items(specialties.size) { index ->
                Button(onClick = {}) {
                    Text(specialties[index])
                }
            }
        }
    }
}

@Composable
private fun LanguageSelect(
    initial: String = "",
    onLanguageChange: (String) -> Unit = {}
) {
    var language by remember { mutableStateOf(initial) }

    OutlinedTextField(
        value = language,
        onValueChange = {
            language = it
            onLanguageChange(it)
        },
        label = { Text("Language") },
        singleLine = true,
        trailingIcon = {
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(CoreDraw.drawable.down_arrow_grey_icon),
                    contentDescription = "My Location"
                )
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}



@Preview(showBackground = true, backgroundColor = 0xFFFFFFFFL)
@Composable
private fun FiltersScreenPreview() {
    FiltersScreen()
}