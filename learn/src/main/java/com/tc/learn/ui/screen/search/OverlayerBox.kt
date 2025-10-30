package com.tc.learn.ui.screen.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import com.tc.learn.data.model.Teacher
import com.tc.learn.ui.navigation.AppNavigator
import com.tc.learn.R
import com.tc.learn.data.model.Level
import com.tc.learn.data.model.Subject
import com.tc.learn.ui.common.HorizontalSpacerGrayLine
import com.tc.learn.ui.screen.filter.DropdownMenuDemo
import com.tc.learn.ui.viewmodel.TeacherViewModel
import theme.backgroundColor
import java.time.LocalDate


@Composable
fun OverlayerBox(
    modifier: Modifier = Modifier,
    navigator: AppNavigator,
    onTeacherClick: (Teacher) -> Unit,
    onMapClick: (Teacher) -> Unit,
    viewModel: TeacherViewModel = hiltViewModel(),
    teachers: List<Teacher>,
    imageLoader: ImageLoader,
) {
    var nameQuery by remember { mutableStateOf("") }
    var selectedSubject by remember { mutableStateOf<String?>(null) }
    var selectedLevel by remember { mutableStateOf<String?>(null) }
    var selectedDate by remember { mutableStateOf<String?>(null) }

    val levels by viewModel.levels.collectAsState()
    val subjects by viewModel.subjects.collectAsState()

    Surface(
        modifier = modifier.fillMaxWidth(),
        tonalElevation = 8.dp,
        shadowElevation = 8.dp,
        shape = RoundedCornerShape(16.dp),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp)
        ) {
            // --- Location Header ---
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Johannesburg, 1 Road Ubuntu",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Icon(
                    painter = painterResource(R.drawable.location_crosshair_icon),
                    contentDescription = null,
                    modifier = Modifier.clickable { navigator.navigateTo("map") }
                )
            }

            HorizontalSpacerGrayLine()
            Spacer(modifier = Modifier.height(12.dp))

            // --- 3 Dropdown Row ---
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                // Column 1: Choose Date
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "CHOOSE DATE",
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    DropdownMenuDemo(
                        modifier = Modifier.fillMaxWidth(),
                        options = listOf("2025-03-20", "2025-03-21", "2025-03-22"),
                        selectedOption = selectedDate ?: "Select Date",
                        onSelect = { selectedDate = it }
                    )
                }

                // Column 2: Lesson
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Lesson",
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    // Lesson Dropdown
                    DropdownMenuDemo(
                        modifier = Modifier.fillMaxWidth(),
                        options = subjects.map { it.name },
                        selectedOption = selectedSubject ?: "Select Lesson",
                        onSelect = { selected ->
                            selectedSubject = selected
                            viewModel.filterTeachersByLevelSubjectAndName(
                                selectedLevelName = selectedLevel,
                                selectedSubjectName = selectedSubject,
                                nameQuery = nameQuery
                            )
                        }
                    )
                }

                // Column 3: Level
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Level",
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    // Level Dropdown
                    DropdownMenuDemo(
                        modifier = Modifier.fillMaxWidth(),
                        options = levels.map { it.name },
                        selectedOption = selectedLevel ?: "Select Level",
                        onSelect = { selected ->
                            selectedLevel = selected
                            viewModel.filterTeachersByLevelSubjectAndName(
                                selectedLevelName = selectedLevel,
                                selectedSubjectName = selectedSubject,
                                nameQuery = nameQuery
                            )
                        }
                    )
                }
            }
            //Search bar on text change, update the TeacherList
            SearchTextBox(
                value = nameQuery,
                onValueChange = { query ->
                    nameQuery = query
                    viewModel.filterTeachersByLevelSubjectAndName(
                        selectedLevelName = selectedLevel,
                        selectedSubjectName = selectedSubject,
                        nameQuery = nameQuery
                    )
                },
                placeholder = "Search teacher or lesson"
            )
        }
    }
}


//@Composable
//private fun TeacherInfo(
//    modifier: Modifier = Modifier,
//    navController: NavController,
//) {
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.SpaceBetween,
//        modifier = modifier
//            .fillMaxWidth()
//            .height(63.dp)
//            .clickable(
//                onClick = {
//                    navController.navigate("your_learn")
//                }
//            )
//    ) {
//        Info(
//            text = stringResource(R.string.learn_choose_dates_home_page_overlayer_box)
//        )
//
//        VerticalSpacerGrayLine()
//
//        Info(
//            text = stringResource(R.string.learn_KG_home_page_overlayer_box)
//        )
//
//        VerticalSpacerGrayLine()
//
//        Info(
//            text = stringResource(R.string.learn_dry_home_page_overlayer_box)
//        )
//
//        VerticalSpacerGrayLine()
//
//        Info(
//            text = stringResource(R.string.learn_ironing_home_page_overlayer_box)
//        )
//    }
//}

@Composable
fun Info(
    modifier: Modifier = Modifier,
    text: String = "",
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = Color.LightGray
        )

        Text(
            text = "02-10",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun Search(
    nameQuery: String,
    onNameQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    onClickSearchIcon: () -> Unit,
) {
    OutlinedTextField(
        value = nameQuery,
        onValueChange = onNameQueryChange,
        singleLine = true,
        placeholder = {
            Text(
                text = stringResource(R.string.learn_search_home_page_overlayer_box),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray
            )
        },
        trailingIcon = {
            Icon(
                painter = painterResource(R.drawable.magnifying_glass_grey_icon),
                contentDescription = "Search Icon",
                tint = Color.Gray
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(12.dp)
    )
}

@Composable
fun VerticalSpacerGrayLine(
    modifier: Modifier = Modifier,
) {
    Spacer(
        modifier = modifier
            .fillMaxHeight()
            .width(1.dp)
            .background(Color.Gray)
    )
}


@Composable
fun HorizontalSpacerGrayLine(
    modifier: Modifier = Modifier,
    thickness: Float = 1f,        // height of the line in dp
    color: Color = Color.LightGray,
    paddingHorizontal: Float = 0f, // optional horizontal padding
) {
    Spacer(
        modifier = modifier
            .fillMaxWidth()
            .height(thickness.dp)
            .padding(horizontal = paddingHorizontal.dp)
            .background(color)
    )
}

@Composable
fun SearchTextBox(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Search",
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        placeholder = {
            Text(
                text = placeholder,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray
            )
        },
        trailingIcon = {
            Icon(
                painter = painterResource(R.drawable.magnifying_glass_grey_icon),
                contentDescription = "Search Icon",
                tint = Color.Gray
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp),
    )
}
