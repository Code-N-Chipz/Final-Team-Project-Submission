package com.tc.learn.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import com.tc.learn.data.model.Level
import com.tc.learn.data.model.Subject
import com.tc.learn.data.model.Teacher
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.zIndex
import com.tc.learn.R


@Composable
fun TeacherCard(
    modifier: Modifier,
    teacher: Teacher,
    imageLoader: ImageLoader,
    onTeacherClick: (Teacher) -> Unit,
    onMapClick: (Teacher) -> Unit,
    onCalendarClick: (Teacher) -> Unit,

) {

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .border(1.dp, color = theme.primaryColor)
            .clickable { onTeacherClick(teacher) },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = teacher.name, style = MaterialTheme.typography.bodyMedium)
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .clickable { onTeacherClick(teacher) },
        ) {

        }
        AsyncImage(
            model = teacher.imageUrl,
            contentDescription = teacher.name,
            imageLoader = imageLoader,
            modifier = Modifier
                .size(100.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = teacher.subjectNames, style = MaterialTheme.typography.bodySmall)
        // --- Map Icon Button ---
        IconButton(
            onClick = { onMapClick(teacher) }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_location),
                contentDescription = "View on Map",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun TeacherListCard(
    modifier: Modifier,
    teachers: List<Teacher>,
    onTeacherClick: (Teacher) -> Unit,
) {
    LazyColumn(modifier = Modifier
        .fillMaxSize().zIndex(0f), // always above background and list
    ) {
        items(teachers) { teacher ->
            SearchCard(
                teacher = teacher,
                onClick = onTeacherClick,
                modifier = Modifier
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TeacherListPreview() {
    val sampleTeachers = listOf(
        Teacher(
            id = "1",
            name = "Alice Johnson",
            levels = listOf(Level.ELEMENTARY, Level.MIDDLE_SCHOOL),
            subjects = listOf(Subject.MATHS, Subject.SCIENCE),
//            location = null,
            price = 25.0,
            _rating = 4.8
        ),
        Teacher(
            id = "2",
            name = "Bob Smith",
            levels = listOf(Level.HIGH_SCHOOL),
            subjects = listOf(Subject.ENGLISH, Subject.HISTORY),
//            location = null,
            price = 30.0,
            _rating = 4.6
        ),
        Teacher(
            id = "3",
            name = "Carol Lee",
            levels = listOf(Level.COLLEGE),
            subjects = listOf(Subject.ART, Subject.PHYSICAL_EDUCATION),
//            location = null,
            price = 35.0,
            _rating = 4.9
        )
    )

    TeacherListCard(
        teachers = sampleTeachers,
        onTeacherClick = { /* For preview, do nothing */ },
        modifier = Modifier
    )
}

@Composable
fun TeacherImage(
    teacher: Teacher?,
    imageLoader: ImageLoader,
) {
    AsyncImage(
        model = teacher?.imageUrl,
        contentDescription = teacher?.name,
        imageLoader = imageLoader,
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
            .background(theme.buttonPrimary)
    )
}


