package com.tc.learn.ui.screen.search

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import coil.compose.AsyncImage
import com.tc.learn.R
import com.tc.learn.data.model.Level
import com.tc.learn.data.model.Subject
import com.tc.learn.data.model.Teacher
import com.tc.learn.ui.component.ButtonWithTextAndIcon
import com.tc.learn.ui.component.ButtonWithTextOnly
import com.tc.learn.ui.component.TeacherCard
import com.tc.learn.ui.navigation.AppNavigator
import com.tc.learn.ui.screen.filter.DropdownMenuDemo
import com.tc.learn.ui.viewmodel.TeacherViewModel
import theme.backgroundColor
import theme.primaryColor
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun SearchScreen(
    viewModel: TeacherViewModel = hiltViewModel(),
    onTeacherClick: (Teacher) -> Unit,
    onMapClick: (Teacher) -> Unit,
    onCalendarClick: (Teacher) -> Unit,
    navigator: AppNavigator,
) {

    val teachers by viewModel.teachers.collectAsState()
    val imageLoader: ImageLoader = viewModel.imageLoader


    // Wrap entire content in the background + overlay structure
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {


        //Background Image
        AsyncImage(
            //Draw image of search_screen_bg.png
            model = R.drawable.search_screen_bg, // make sure this drawable exists
            contentDescription = "Search Background",
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp) // adjust height
                .zIndex(0f),
            contentScale = ContentScale.Crop
        )

        // --- Overlay content box (above background) ---
        OverlayerBox(
            modifier = Modifier
                .padding(top = 100.dp) // adjust as needed
            .zIndex (2f), // always above background and list
        navigator = navigator,
        onTeacherClick = onTeacherClick,
        onMapClick = onMapClick,
        teachers = teachers,
        imageLoader = imageLoader,
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Spacer(
                modifier = Modifier
                    .height(80.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        //Filter button needs to go here
        // --- LazyColumn list of TeacherCards ---
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize()
                .zIndex(-2f)
        ) {
            items(teachers) { teacher ->
                TeacherCard(
                    modifier = Modifier.fillMaxWidth(),
                    teacher = teacher,
                    imageLoader = imageLoader,
                    onTeacherClick = { onTeacherClick(teacher) },
                    onMapClick = { onMapClick(teacher) },
                    onCalendarClick = { onCalendarClick(teacher) }
                )
            }
        }

    }
}


