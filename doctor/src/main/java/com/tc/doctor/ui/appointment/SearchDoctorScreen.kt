package com.tc.doctor.ui.appointment

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.time.LocalTime
import com.tc.design.R as CoreDraw
import com.tc.doctor.R
import theme.textPenternary
import theme.textTertiary


// TODO: replace textTertiary with Pent

data class Specialist(
    val name: String,
    val address: String,
    val isAvailable: Boolean,
    val workHours: ClosedRange<LocalTime> = LocalTime.of(8, 0)..LocalTime.of(20, 0),
    val stars: Float,
    @DrawableRes val imageRes: Int
)

private val specialistList: List<Specialist> = listOf(
    Specialist(
        name = "Dr. Alice Smith",
        address = "123 Main St",
        isAvailable = true,
        workHours = LocalTime.of(9, 0)..LocalTime.of(17, 0),
        stars = 4.6f,
        imageRes = R.drawable.img_jenny_jones
    ),
    Specialist(
        name = "Dr. Bob Jones",
        address = "456 Elm St",
        isAvailable = false,
        workHours = LocalTime.of(10, 0)..LocalTime.of(18, 0),
        stars = 4.2f,
        imageRes = R.drawable.img_dispensary
    ),
    Specialist(
        name = "Dr. Carol Lee",
        address = "789 Oak Ave",
        isAvailable = true,
        workHours = LocalTime.of(8, 30)..LocalTime.of(15, 30),
        stars = 5.0f,
        imageRes = R.drawable.img_jenny_jones
    ),
    Specialist(
        name = "Dr. James Hoflof",
        address = "999 Ukon Ave",
        isAvailable = true,
        workHours = LocalTime.of(8, 30)..LocalTime.of(15, 30),
        stars = 3.0f,
        imageRes = R.drawable.img_jenny_jones
    ),
    Specialist(
        name = "Dr. Alice Smith",
        address = "123 Main St",
        isAvailable = true,
        workHours = LocalTime.of(9, 0)..LocalTime.of(17, 0),
        stars = 4.6f,
        imageRes = R.drawable.img_jenny_jones
    ),
    Specialist(
        name = "Dr. Bob Jones",
        address = "456 Elm St",
        isAvailable = false,
        workHours = LocalTime.of(10, 0)..LocalTime.of(18, 0),
        stars = 4.2f,
        imageRes = R.drawable.img_dispensary
    ),
    Specialist(
        name = "Dr. Carol Lee",
        address = "789 Oak Ave",
        isAvailable = true,
        workHours = LocalTime.of(8, 30)..LocalTime.of(15, 30),
        stars = 5.0f,
        imageRes = R.drawable.img_jenny_jones
    ),
    Specialist(
        name = "Dr. James Hoflof",
        address = "999 Ukon Ave",
        isAvailable = true,
        workHours = LocalTime.of(8, 30)..LocalTime.of(15, 30),
        stars = 3.0f,
        imageRes = R.drawable.img_jenny_jones
    )
)


@Composable
fun SearchDoctorScreen(
    onHomeClick: () -> Unit = {},
    onDoctorClick: () -> Unit = {} // TODO: need to pass information -> figure it out
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(15.dp)
            .fillMaxSize()
    ) {
        TopBar(onHomeClick)
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        SearchBar()
        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        SpecialistList(specialistList)
    }
}

@Composable
private fun TopBar(onHomeClick: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row {
            IconButton(onClick = onHomeClick) {
                Icon(
                    painter = painterResource(CoreDraw.drawable.home_icon),
                    contentDescription = "Home Icon"
                )
            }
        }
        Row {
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(CoreDraw.drawable.options_gray_icon),
                    contentDescription = "Home Icon"
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(CoreDraw.drawable.pin_gray_icon),
                    contentDescription = "Home Icon"
                )
            }
        }
    }
}

@Composable
private fun SearchBar(
    searchQuery: String = "",
    onSearchQueryChange: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ){
        Box(
            modifier = Modifier
                .matchParentSize()
                .offset(x = 4.dp, y = 4.dp)
                .shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(8.dp),
                    clip = false
                )
        )
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchQueryChange,
            modifier = modifier
                .fillMaxWidth(),
            placeholder = { Text("Search doctors, specialties...") },
            trailingIcon = {
                Row {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { onSearchQueryChange("") }) {
                            Icon(Icons.Default.Close, contentDescription = "Clear")
                        }
                    }
                    Icon(
                        painter = painterResource(CoreDraw.drawable.magnifying_glass_grey_icon),
                        contentDescription = "Search Icon"
                    )
                }
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { /* trigger search action if needed */ }),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = theme.backgroundColor,
                unfocusedContainerColor = theme.backgroundColor
            )
        )
    }
}

@Composable
private fun SpecialistList(specialists: List<Specialist>) {

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        items(specialists.size) { index ->
            HorizontalDivider(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                Image(
                    painterResource(specialists[index].imageRes),
                    contentDescription = "${specialists[index].name} Image",
                    modifier = Modifier.size(80.dp)
                )
                Column {
                    Text(text = specialists[index].name,
                        style = theme.typography.bodyLarge)
                    Text(text = specialists[index].address,
                        style = theme.typography.bodyMedium.copy(color = textPenternary))
                }
                Spacer(modifier = Modifier.padding(10.dp))
                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = if (specialists[index].isAvailable) "Available" else "Unavailable",
                        style = theme.typography.bodyMedium.copy(color = textPenternary))
                    Text(text = specialists[index].workHours.toString())
                    Row {
                        val stars = specialists[index].stars
                        val starRes = when {
                            stars >= 4 -> CoreDraw.drawable.full_star_icon
                            stars <= 1 -> CoreDraw.drawable.star_white_icon
                            else -> CoreDraw.drawable.half_star_icon
                        }
                        Icon(
                            painter = painterResource(starRes),
                            contentDescription = "Star rating",
                            tint = Color.Unspecified,
                            modifier = Modifier
                                .size(15.dp)
                                .padding(end = 5.dp)
                        )
                        Text(text = specialists[index].stars.toString())
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFFL)
@Composable
private fun SearchDoctorScreenPreview() {
    SearchDoctorScreen()
}