package com.tc.learn.ui.screen.appointment

import androidx.compose.runtime.Composable
import com.tc.learn.ui.navigation.AppNavigator
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import com.tc.learn.data.model.shortDescription
import com.tc.learn.ui.component.TeacherImage
import com.tc.learn.ui.viewmodel.TeacherViewModel

//Need to populate with data from repository

@Composable
fun AppointmentScreen(
    navigator: AppNavigator,
    teacherId: String,
    imageLoader: ImageLoader,
    viewModel: TeacherViewModel = hiltViewModel()
    ) {
    val selectedTeacher = viewModel.getTeacherById(id = teacherId)

    // Root Column
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // TODO: Map placeholder
//        Map()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.Gray),
            contentAlignment = Alignment.Center
        ) {
            Text("Map goes here", color = Color.White)
        }
        Spacer(modifier = Modifier.height(16.dp))

        TeacherImage(
            teacher = viewModel.getTeacherById(teacherId),
            imageLoader = imageLoader
        )


//        // Teacher Image in Circle
//        Box(
//            modifier = Modifier.fillMaxWidth(),
//            contentAlignment = Alignment.Center
//        ) {
//            if (selectedTeacher != null) {
//                val painter = rememberAsyncImagePainter(
//                    model = selectedTeacher.imageUrl,
//                    imageLoader = imageLoader
//                )
//                Image(
//                    painter = painter,
//                    contentDescription = "Teacher Image",
//                    modifier = Modifier
//                        .size(120.dp)
//                        .clip(CircleShape)
//                )
//            } else {
//                Box(
//                    modifier = Modifier
//                        .size(120.dp)
//                        .clip(CircleShape)
//                        .background(Color.LightGray),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Text("No Image")
//                }
//            }
//
//        }

        Spacer(modifier = Modifier.height(16.dp))

        // Ratings and Price Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("⭐ {${selectedTeacher?.rating}}", fontSize = 16.sp)
            Text("${selectedTeacher?.price}/hr", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Teacher Description
        selectedTeacher?.shortDescription()?.let {
            Text(
                text = it,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Address
        //Extension function for address

        selectedTeacher?.address?.let {
            Text(
                text = it,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lessons and Levels Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Math, Science") // Subjects
            Text("Beginner, Advanced") // Levels


        }

        Spacer(modifier = Modifier.height(24.dp))

        // Take Appointment Button
        Button(
            onClick = { /* TODO: Navigate or book appointment */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Take Appointment")
        }
    }
}
