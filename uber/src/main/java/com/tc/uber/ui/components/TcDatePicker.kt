package com.tc.uber.ui.components


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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tc.uber.R
import theme.primaryColor
import theme.typography
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TcDatePicker(modifier: Modifier = Modifier) {

    var selectedDate by rememberSaveable {
        mutableStateOf(-1)
    }

    var monthIncBy by rememberSaveable {
        mutableStateOf(0)
    }


    val datePickerState = rememberDatePickerState()

    val currentMonth = LocalDate.now().month
    val currentYear = LocalDate.now().year
    val dayOfMonth = LocalDate.now().dayOfMonth

    val date = LocalDate.of(currentYear, currentMonth.value + monthIncBy, 1)
    val lastDayOfMonth = LocalDate.of(
        currentYear,
        currentMonth.value + monthIncBy,
        date.lengthOfMonth()
    ).dayOfWeek.value
    val firstDayOfMonth = date.dayOfWeek.value




    Column(
        modifier
            .heightIn(min = 412.dp)
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(horizontal = 20.dp)
    ) {
        Spacer(Modifier.height(12.dp))

        val days = arrayOf("S", "M", "T", "W", "T", "F", "S")

        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                monthIncBy -= 1
            }) {
                Icon(
                    painter = painterResource(R.drawable.arrow_back),
                    contentDescription = "",
                    modifier = Modifier.weight(0.2f)
                )
            }

            Text(
                if (monthIncBy == 0) currentMonth.name else date.month.name,
                style = typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )

            IconButton(onClick = {
                monthIncBy += 1
            }) {
                Icon(
                    painter = painterResource(R.drawable.arrow_fwd),
                    contentDescription = "",
                    modifier = Modifier.weight(0.2f)
                )
            }

        }

        Spacer(Modifier.height(12.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(7),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {

            items(days) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(it, style = typography.bodyLarge, fontWeight = FontWeight.SemiBold)
                }

            }

            items(firstDayOfMonth) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {


                    Text("_", style = typography.bodyLarge, color = Color.Transparent)
                }
            }

            items(date.lengthOfMonth()) {
                val dt = it + 1

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                        .drawBehind {
                            drawCircle(color = if (selectedDate == dt) primaryColor else Color.Transparent)
                        }
                        .clickable {
                            if (dt >= dayOfMonth) {
                                selectedDate = dt
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        dt.toString(),
                        style = typography.bodyLarge,
                        color = if (selectedDate == dt) Color.White else if (dt < dayOfMonth) Color.Gray else Color.Black,
                        modifier = Modifier.padding(8.dp)
                    )

                }

            }

            if (lastDayOfMonth < 6)
                items((6 - lastDayOfMonth)) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {

                        Text("_", style = typography.bodyLarge, color = Color.Transparent)
                    }
                }

        }

        Spacer(Modifier.height(8.dp))

    }

}