package com.tc.learn.ui.screen.teacher

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tc.learn.data.model.Level
import com.tc.learn.data.model.Subject
import com.tc.learn.data.model.Teacher
import com.tc.learn.ui.navigation.AppNavigator
import com.tc.learn.utils.navigation.NavRoute

//This is the page where you have clicked on a teacher in the list, and now you have to
// choose between the options
//(Level & Subject)
// And then when you click then you will be taken to the place order page

@Composable
fun TeacherScreen(
    navigator: AppNavigator,
    teacherId: String = "", // Pass the selected teacher here
    viewModel: TeacherDetailViewModel = hiltViewModel(),
    onButtonClick: () -> Unit,
    ) {
    var selectedSubject by remember { mutableStateOf<Subject?>(null) }
    var selectedLevel by remember { mutableStateOf<Level?>(null) }

    // Collect the teacher from the ViewModel
    val selectedTeacher by viewModel.teacher.collectAsState()

    // Load teacher once when composable enters composition
    LaunchedEffect(teacherId) {
        viewModel.loadTeacher(teacherId)
    }

    selectedTeacher?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                text = "Select Lesson & Level for ${it.name}",
                style = MaterialTheme.typography.headlineSmall
            )

            // --- Subject Selection ---
            Column {
                Text("Select Subject:", style = MaterialTheme.typography.titleMedium)
                it.subjects.forEach { subject ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = selectedSubject == subject,
                                onClick = { selectedSubject = subject }
                            )
                            .padding(8.dp)
                    ) {
                        RadioButton(
                            selected = selectedSubject == subject,
                            onClick = { selectedSubject = subject }
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(subject.name)
                    }
                }
            }

            // --- Level Selection ---
            Column {
                Text("Select Level:", style = MaterialTheme.typography.titleMedium)
                it.levels.forEach { level ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = selectedLevel == level,
                                onClick = { selectedLevel = level }
                            )
                            .padding(8.dp)
                    ) {
                        RadioButton(
                            selected = selectedLevel == level,
                            onClick = { selectedLevel = level }
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(level.name.replace("_", " "))
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // --- Next Button ---
            Button(
                onClick = {
                    if (selectedSubject != null && selectedLevel != null) {
                        navigator.navigateTo(
                            NavRoute.Booking.passParams(
                                teacherId = it.id
                            )
                        )
                    }
                },
                enabled = selectedSubject != null && selectedLevel != null,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Next")
            }
        }
    }



}
