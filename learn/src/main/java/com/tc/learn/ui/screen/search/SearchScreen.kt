package com.tc.learn.ui.screen.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import coil.compose.AsyncImage
import com.tc.learn.R
import com.tc.learn.data.model.Level
import com.tc.learn.data.model.Subject
import com.tc.learn.data.model.Teacher
import com.tc.learn.ui.component.TeacherCard
import com.tc.learn.ui.viewmodel.TeacherViewModel

@Composable
fun SearchScreen(
    viewModel: TeacherViewModel = hiltViewModel(),
    onTeacherClick: (Teacher) -> Unit,
    onMapClick: (Teacher) -> Unit,
) {
    // Collect StateFlow as Compose State
    val teachers by viewModel.teachers.collectAsState()

    // Local search/filter states
    var nameQuery by remember { mutableStateOf("") }
    var selectedLevels by remember { mutableStateOf<List<Level>>(emptyList()) }
    var selectedSubjects by remember { mutableStateOf<List<Subject>>(emptyList()) }
    var locationQuery by remember { mutableStateOf("") }
    val imageLoader: ImageLoader = viewModel.imageLoader as ImageLoader

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        AsyncImage(
            //Draw image of search_screen_bg.png
            model = R.drawable.search_screen_bg, // make sure this drawable exists
            contentDescription = "Search Background",
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp), // adjust height
            contentScale = ContentScale.Crop
        )

        Spacer(
            modifier = Modifier
                .height(4.dp)
        )
//       3 Rows
        Row(
            modifier = Modifier,
        ) {
            //Choose date
            Column {
                Text(text = "Choose Date")
                //Date picker goes here



            }

            //Choose lesson
            Column {
                Text(text = "Lesson")
                //Lesson drop down goes here

            }

            //Choose level
            Column {
                Text(text = "Level")
                //Level drop down goes here

            }

        }


        // --- Name search ---
        OutlinedTextField(
            value = nameQuery,
            onValueChange = { nameQuery = it },
            label = { Text("Search by name") },
            modifier = Modifier.fillMaxWidth()
        )

        // --- Location search ---
        OutlinedTextField(
            value = locationQuery,
            onValueChange = { locationQuery = it },
            label = { Text("Location") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // --- Search button ---
        Button(
            onClick = {
                viewModel.filterTeachers(
                    levels = selectedLevels,
                    subjects = selectedSubjects,
                    nameQuery = nameQuery,
                    locationQuery = locationQuery
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Search")
        }

        // --- Row with Search & Filter ---
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    viewModel.filterTeachers(
                        levels = selectedLevels,
                        subjects = selectedSubjects,
                        nameQuery = nameQuery,
                        locationQuery = locationQuery
                    )
                }
            ) {
                Text("Search")
            }

            // Small filter button
            Button(
                onClick = { /* Open filter dialog/sheet */ },
                modifier = Modifier.height(48.dp) // keeps height consistent with search
            ) {
                Text("Filter")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        //Filter button needs to go here


        // --- LazyColumn list of SearchCards ---
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(teachers) { teacher ->
                TeacherCard(
                    modifier = Modifier.fillMaxWidth(),
                    teacher = teacher,
                    imageLoader = imageLoader,
                    onTeacherClick = { onTeacherClick(teacher) },
                    onMapClick = { onMapClick(teacher) }
                )
            }
        }
    }
}
