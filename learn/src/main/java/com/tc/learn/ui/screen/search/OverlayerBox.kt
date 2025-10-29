package com.tc.learn.ui.screen.search

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import com.tc.learn.data.model.Teacher
import com.tc.learn.ui.navigation.AppNavigator
import com.tc.learn.R
import com.tc.learn.data.model.Level
import com.tc.learn.data.model.Subject
import com.tc.learn.ui.component.ButtonWithTextOnly
import com.tc.learn.ui.screen.filter.DropdownMenuDemo
import com.tc.learn.ui.viewmodel.TeacherViewModel
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
    val teachers: List<Teacher> = teachers
    var nameQuery by remember { mutableStateOf("") }
    var selectedLevels by remember { mutableStateOf<List<Level>>(emptyList()) }
    var selectedSubjects by remember { mutableStateOf<List<Subject>>(emptyList()) }
    var locationQuery by remember { mutableStateOf("") }
    val imageLoader: ImageLoader = viewModel.imageLoader

    // Dropdown states
    var expandedsubject by remember { mutableStateOf(false) }
    var expandedLevel by remember { mutableStateOf(false) }
    var selectedsubject by remember { mutableStateOf<String?>("Select subject") }
    var selectedLevel by remember { mutableStateOf<String?>("Select Education") }
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }

    // Sample data (replace with data from repo, via viewmodel)
//    val subjects = listOf("Math", "English", "Physics")
//    val levels = listOf("Beginner", "Intermediate", "Advanced")
    val levels by viewModel.levels.collectAsState()
    val subjects by viewModel.subjects.collectAsState()

//    Surface (modifier = Modifier) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(RoundedCornerShape(16.dp))  // <-- rounded corners
                .background(Color.White)           // background after clipping
                .zIndex(1f)
                .padding(horizontal = 16.dp)

        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Johannesburg, 1 Road Ubuntu",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Icon(
                        painter = painterResource(R.drawable.location_crosshair_icon),
                        contentDescription = null,
                        modifier = Modifier
                            .clickable(
                                onClick = { }
                            )
                    )
                }

                HorizontalSpacerGrayLine()

//            TeacherCard(
//                modifier = Modifier,
//                teacher = teacher,
//                imageLoader = imageLoader,
//                onTeacherClick = onTeacherClick
//            ) {
//
//            }
                HorizontalSpacerGrayLine()
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                ) {

                    DropdownMenuDemo(
                        modifier = Modifier,
                        options = levels.map { it.name },
                        selectedOption = selectedLevel ?: "Select Level",
                        onSelect = { name ->
                            selectedLevel = levels.firstOrNull { it.name == name } as String?
                        }
                    )

                    DropdownMenuDemo(
                        modifier = Modifier.background(theme.backgroundColor),
                        options = subjects.map { it.name },
                        selectedOption = selectedsubject ?: "Select Subject",
                        onSelect = { name ->
                            selectedsubject = subjects.firstOrNull { it.name == name } as String?
                        },
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

                Search(onClickSearchIcon = { }, nameQuery = nameQuery)
            }
        }
//    }
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

@Suppress("UnusedPrivateMember")
@Composable
private fun Info(
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
private fun Search(
    modifier: Modifier = Modifier,
    onClickSearchIcon: () -> Unit = {},
    nameQuery: String,
) {
    var nameQuery by remember { mutableStateOf("") }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                onClick = onClickSearchIcon
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .align(Alignment.Center)
        ) {
//            Text(
//                text = stringResource(R.string.learn_search_home_page_overlayer_box),
//                fontSize = 14.sp,
//                fontWeight = FontWeight.Medium,
//                color = Color.Gray
//            )

            SearchTextBox(
                value = nameQuery,
                onValueChange = { nameQuery = it },
                placeholder = stringResource(R.string.learn_search_home_page_overlayer_box)
            )

            Icon(
                painter = painterResource(R.drawable.magnifying_glass_grey_icon),
                contentDescription = null,
                tint = Color.Gray
            )
        }
    }
}

@Suppress("UnusedPrivateMember")
@Composable
private fun VerticalSpacerGrayLine(
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
        shape = RoundedCornerShape(12.dp)
    )
}
