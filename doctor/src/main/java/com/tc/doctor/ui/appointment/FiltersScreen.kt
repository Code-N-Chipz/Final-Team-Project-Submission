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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tc.doctor.ui.DoctorDest
import com.tc.ui.CommonButton
import theme.primaryColor
import theme.textPenternary
import kotlin.math.roundToInt
import com.tc.design.R as CoreDraw

@Composable
fun FiltersScreen(
    navController: NavController? = null,
    viewModelAppointment: ViewModelAppointment? = null
) {

    var city by rememberSaveable { mutableStateOf("") }
    var distance by rememberSaveable { mutableStateOf(50f) }
    var specialistSet by rememberSaveable { mutableStateOf(setOf<Int>()) }
    var language by rememberSaveable { mutableStateOf("English") }

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(15.dp)
            .fillMaxSize()
    ) {
        TopBar(onBackClick = {
            navController?.popBackStack()
        })
        CitySelect(
            city = city,
            onCityChange = { city = it }
        )
        DistanceSet(
            distance = distance,
            onDistanceChange = { distance = it }
        )
        SpecialtiesSet(
            selected = specialistSet,
            onSelectedChange = { specialistSet = it },
            options = setOf(
                "All", "Allergist", "Anesthesiologist",
                "Cardiologist",
                "Dermatologist",
                "Endocrinologist", "ENT (Otolaryngologist)",
                "Family Physician",
                "Gastroenterologist", "Geriatrician", "Gynecologist",
                "Hematologist",
                "Infectious Disease Specialist", "Internist",
                "Nephrologist", "Neurologist", "Neurosurgeon",
                "Obstetrician", "Oncologist"
            )
        )
        LanguageDropDown(
            language = language,
            onLanguageChange = { language = it },
            options = setOf("English", "Spanish", "French", "German")
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            // TODO: need to pass information. view model is big here
            CommonButton("Save", onClick = { navController?.navigate(DoctorDest.Search.route) })
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}


@Composable
private fun TopBar(onBackClick: () -> Unit = {}) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                painter = painterResource(CoreDraw.drawable.arrow_left_orange_icon),
                contentDescription = "Back Button",
                tint = Color.Unspecified
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
    city: String,
    onCityChange: (String) -> Unit = {},
    findCurrentLocation: () -> Unit = {}
) {
    OutlinedTextField(
        value = city,
        onValueChange = { onCityChange(it) },
        label = { Text("City") },
        singleLine = true,
        trailingIcon = {
            IconButton(onClick = { findCurrentLocation }) {
                Icon(
                    painter = painterResource(CoreDraw.drawable.location_crosshair_icon_gray),
                    contentDescription = "My Location",
                    tint = Color.Unspecified
                )
            }
        },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DistanceSet(distance: Float = 50f, onDistanceChange: (Float) -> Unit) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text("Distance", style = theme.typography.bodyMedium.copy(color = textPenternary))
            HorizontalDivider(Modifier, DividerDefaults.Thickness, textPenternary)
        }
        Spacer(modifier = Modifier.height(5.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text("1 km", style = theme.typography.bodyMedium.copy(color = textPenternary))
            Text("$distance km", style = theme.typography.bodyMedium)
            Text("+100 km", style = theme.typography.bodyMedium.copy(color = textPenternary))
        }


        Slider(
            value = distance,
            onValueChange = { onDistanceChange(it.roundToInt().toFloat()) },
            valueRange = 1f..100f,
            steps = 98, // step by 1
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
                        activeTickColor = theme.textTertiary,
                        inactiveTickColor = theme.textTertiary
                    ),
                    modifier = Modifier
                        .height(2.dp)
                )
            }
        )
    }
}

@Composable
private fun SpecialtiesSet(selected: Set<Int>, onSelectedChange: (Set<Int>) -> Unit, options: Set<String>) {

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text("Specialties", style = theme.typography.bodyMedium.copy(color = textPenternary))
            HorizontalDivider(Modifier, DividerDefaults.Thickness, textPenternary)
        }
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
            itemsIndexed(options.toList()) { index, specialty ->
                val isSelected = index in selected
                if (isSelected) {
                    Button(
                        onClick = {
                            val newSet = if (isSelected) selected - index else selected + index
                            onSelectedChange(newSet)
                        },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(theme.buttonPrimary)
                    ) {
                        Text(specialty)
                    }
                } else {
                    Box() {

                    }
                    Button(
                        onClick = {
                            val newSet = if (isSelected) selected - index else selected + index
                            onSelectedChange(newSet)
                        },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(theme.backgroundColor),
                        modifier = Modifier
                            .shadow(4.dp, RoundedCornerShape(8.dp
                            ))

                    ) {
                        Text(specialty, color = theme.textQuaternary)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LanguageDropDown(
    onLanguageChange: (String) -> Unit = {},
    options: Set<String> = emptySet(),
    language: String = "English"
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = language,
            onValueChange = {}, /* keep readOnly behavior; selection via menu */
            readOnly = true,
            enabled = true,
            label = { Text("Language") },
            singleLine = true,
            trailingIcon = {
                if (expanded) CoreDraw.drawable.down_arrow_grey_icon
                else CoreDraw.drawable.arrow_right_icon
            },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(
                    type = MenuAnchorType.PrimaryNotEditable,
                    enabled = true
                ),

            shape = RoundedCornerShape(8.dp),
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onLanguageChange(option)
                        expanded = false
                    }
                )
            }
        }
    }
}



@Preview(showBackground = true, backgroundColor = 0xFFFFFFFFL)
@Composable
private fun FiltersScreenPreview() {
    FiltersScreen()
}