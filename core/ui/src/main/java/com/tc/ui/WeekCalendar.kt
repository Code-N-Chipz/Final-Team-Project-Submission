package com.tc.ui

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import theme.primaryColor
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import com.tc.design.R as Design

// we set default to current date and time
data class CalendarSelection(
    // NOTE: LocalDate = YYYY-MM-DD
    // NOTE: LocalTime = Hour-Min-Sec-NanoS
    val date: LocalDate,
    val time: LocalTime?
) {
    val year: Int get() = date.year
    val month: Int get() = date.monthValue
    val day: Int get() = date.dayOfMonth
    val dayName: String get() = date.dayOfWeek.name.take(3) // not sure this is needed
    val formattedTime: String?
        get() = time?.format(DateTimeFormatter.ofPattern("hh:mm a"))
}


@Composable
fun WeekCalendar(
    modifier: Modifier = Modifier,
    initialDate: LocalDate = LocalDate.now(),
    initialTime: LocalTime? = null,
    workingHours: ClosedRange<LocalTime> = LocalTime.of(8, 0)..LocalTime.of(20, 0),
    stepMinutes: Long = 30,

    // TODO: coming soon -> no time to finish this
//    loadUnavailableTimesOfMonth: suspend (ClosedRange<LocalDate>) -> Map<LocalDate, Set<LocalTime>>,
    onSelectionChange: (CalendarSelection) -> Unit,
    // TODO: this is for the top image and name
    imageUrl: String,
    name: String,
    onBackClick: () -> Unit = {}
    business: Any,
    onBackClick: () -> Unit?
) {

    fun timeSlots(range: ClosedRange<LocalTime>, step: Long): List<LocalTime> {
        return generateSequence(range.start) { prev ->
            val next = prev.plusMinutes(step)
            if (next <= range.endInclusive) next else null
        }.toList()
    }
    // get working hours once
    val slots = remember(workingHours, stepMinutes) {
        timeSlots(workingHours, stepMinutes)
    }

    var selectedDate by rememberSaveable { mutableStateOf(initialDate) }
    var selectedTime by rememberSaveable { mutableStateOf(initialTime) }

    fun emitSelection(date: LocalDate, time: LocalTime?) {
        onSelectionChange(CalendarSelection(date = date, time = time))
    }
    var isToday = (initialDate.dayOfMonth == selectedDate.dayOfMonth)

    Column {
        TopBar(onBackClick, name, imageUrl)
        TopBar(onBackClick, business.toString())
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        MonthYearUi(
            initialDate,
            selectedDate,
            onMonthChange = { newDate ->
                selectedDate = newDate
                selectedTime = null
                emitSelection(selectedDate, selectedTime)
            }
        )
        Spacer(modifier = Modifier.padding(vertical = 5.dp))
        DayOfMonthRow(
            initialDate,
            selectedDate,
            onDayChange = { newDate ->
                selectedDate = newDate
                selectedTime = null
                emitSelection(selectedDate, selectedTime)
            }
        )
        TimeSlotList(
            isToday = isToday,
            initialTime = LocalTime.now(),
            selectedTime = selectedTime,
            slots = slots,
            onTimeChange = { newTime ->
                selectedTime = newTime
                emitSelection(selectedDate, selectedTime)
            }
        )
    }

}


@Composable
private fun TopBar(
    onBackClick: () -> Unit? = {},
    topBarText: String = "",
    topBarImage: String? = null
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { onBackClick }) {
            Icon(
                painter = painterResource(Design.drawable.arrow_left_orange_icon),
                contentDescription = "Back arrow"
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CircleShapeImage(topBarImage, size = 30.dp)
            Text(
                text = topBarText,
                style = theme.typography.titleLarge
            )
        }
        Spacer(Modifier.padding(horizontal = 40.dp))
    }
}

@Composable
private fun MonthYearUi(today: LocalDate, selected: LocalDate, onMonthChange: (LocalDate) -> Unit) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        // this hides/disables the arrow to go into the past
        if (today.month == selected.month && today.year == selected.year) {
            Box(modifier = Modifier.size(24.dp))
        } else {
            IconButton(onClick = { onMonthChange(selected.minusMonths(1)) }) {
                Icon(
                    painter = painterResource(Design.drawable.arrow_left_orange_icon),
                    tint = Color.Unspecified,
                    contentDescription = "Previous Month"
                )
            }
        }
        Text(
            text = "${selected.month} ${selected.year}",
            style = theme.typography.titleMedium
        )
        IconButton(onClick = { onMonthChange(selected.plusMonths(1)) }) {
            Icon(
                painter = painterResource(Design.drawable.arrow_left_orange_icon),
                tint = Color.Unspecified,
                contentDescription = "Next Month"
            )
        }
    }
}

@Composable
private fun DayOfMonthRow(thisMonth: LocalDate, selectedMonth: LocalDate, onDayChange: (LocalDate) -> Unit) {
    val daysOfMonth: List<Int> = (1..selectedMonth.lengthOfMonth()).toList()
    if (thisMonth.month == selectedMonth.month) {
        LazyRow {
            items(daysOfMonth) { day ->
                val date = selectedMonth.withDayOfMonth(day)
                val shortDay = date.dayOfWeek.name.take(3)

                if (day < thisMonth.dayOfMonth) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = shortDay,
                            style = theme.typography.bodyLarge
                        )
                        TextButton(onClick = {}, enabled = false) {
                            Text(
                                text = day.toString(),
                                color = theme.textTertiary,
                                style = theme.typography.bodyLarge
                            )
                        }
                    }
                } else if (day == selectedMonth.dayOfMonth) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = shortDay,
                            style = theme.typography.bodyLarge
                        )
                        TextButton(onClick = {}, enabled = false) {
                            Card(
                                shape = CircleShape,
                                modifier = Modifier
                                    .size(36.dp),
                                colors = CardDefaults.cardColors(containerColor = primaryColor),
                                elevation = CardDefaults.cardElevation(4.dp)
                            ) {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = selectedMonth.dayOfMonth.toString(),
                                        style = theme.typography.bodyLarge,
                                        color = theme.textPrimary
                                    )
                                }
                            }
                        }
                    }
                } else {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = shortDay,
                            style = theme.typography.bodyLarge
                        )
                        TextButton(onClick = {
                            onDayChange(selectedMonth.withDayOfMonth(day))
                        }) {
                            Text(text = day.toString())
                        }
                    }
                }
            }
        }
    } else {
        LazyRow {
            items(daysOfMonth) { day ->
                val date = selectedMonth.withDayOfMonth(day)
                val shortDay = date.dayOfWeek.name.take(3)
                if (day == selectedMonth.dayOfMonth) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = shortDay,
                        style = theme.typography.bodyLarge
                    )
                    TextButton(onClick = {}, enabled = false) {
                        Card(
                            shape = CircleShape,
                            modifier = Modifier
                                .size(36.dp),
                            colors = CardDefaults.cardColors(containerColor = primaryColor),
                            elevation = CardDefaults.cardElevation(4.dp)
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = selectedMonth.dayOfMonth.toString(),
                                    style = theme.typography.bodyLarge,
                                    color = theme.textPrimary
                                )
                            }
                        }
                    }
                }
            } else {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = shortDay,
                        style = theme.typography.bodyLarge
                    )
                    TextButton(onClick = {
                        onDayChange(selectedMonth.withDayOfMonth(day))
                    }) {
                        Text(
                            text = day.toString(),
                            style = theme.typography.bodyLarge
                        )
                    }
                }
            }
            }
        }
    }
}

@Composable
private fun TimeSlotList(isToday: Boolean, initialTime: LocalTime, selectedTime: LocalTime?, slots: List<LocalTime>, onTimeChange: (LocalTime) -> Unit) {

    // this is used to set times we should not allow them to select, because it is in the past
    fun roundUpToNextHalfHour(time: LocalTime): LocalTime {
        val min = time.minute
        return when {
            // don't want them selecting a current time, set it 30 minutes out.
            min == 0 || min == 30 -> time.plusMinutes(30).withSecond(0).withNano(0)
            min < 30 -> time.withMinute(30).withSecond(0).withNano(0)
            else -> time.plusHours(1).withMinute(0).withSecond(0).withNano(0)
        }
    }

    // set string format for time 8am = "08:00", 4pm = "04:00"
    val formatter = DateTimeFormatter.ofPattern("hh:mm a") // "HH:mm" for military time

    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(slots) { time ->
            val formatted = time.format(formatter)
            val isPast = if (isToday) time.isBefore(initialTime) else false
            val isSelected = time == selectedTime

            HorizontalDivider(
                color = theme.dividerGray,
                thickness = 1.dp,
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            ) {
                TextButton(
                    onClick = { if (!isPast) onTimeChange(time) },
                    enabled = !isPast,
                ) {
                    Row {
                        Box(
                            modifier = Modifier.width(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            if (isSelected) {
                                Text(
                                    text = "\u2022",
                                    style = theme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                                    color = theme.textQuaternary
                                )
                            }
                        }
                        Text(
                            text = formatted,
                            color = when {
                                isPast -> theme.textTertiary
                                isSelected -> theme.textQuaternary
                                else -> theme.textPenternary
                            },
                            style = when {
                                isSelected -> theme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                                else -> theme.typography.bodyLarge
                            },
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
        }
    }
}




@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun WeekCalendarPreview(){
    WeekCalendar(
        modifier = Modifier,
        initialDate = LocalDate.now(),
        initialTime = LocalTime.now(),
        onSelectionChange = {},
        onBackClick = {},
        name = "Jenny",
        imageUrl = ""
        business = "Preview Business",
        onBackClick = {}
    )
}