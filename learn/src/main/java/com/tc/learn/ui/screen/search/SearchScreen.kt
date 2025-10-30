package com.tc.learn.ui.screen.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import com.tc.learn.R
import com.tc.learn.data.model.Teacher
import com.tc.learn.ui.component.TeacherCard
import com.tc.learn.ui.navigation.AppNavigator
import com.tc.learn.ui.viewmodel.TeacherViewModel
import theme.backgroundColor
import theme.primaryColor
import theme.textTertiary

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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        // 🔹 Background Image
        Image(
            painter = painterResource(id = R.drawable.search_screen_bg),
            contentDescription = "Search Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxHeight(.5f)
        )

        // 🔹 Foreground Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Text(
                    text = "03:00",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Gray
                )
            }
            Spacer(modifier = Modifier.height(120.dp))
            OverlayerBox(
                navigator = navigator,
                onTeacherClick = onTeacherClick,
                onMapClick = onMapClick,
                teachers = teachers,
                imageLoader = imageLoader
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Teacher List
            TeacherList(
                teachers = teachers,
                imageLoader = imageLoader,
                onTeacherClick = onTeacherClick,
                onMapClick = onMapClick,
                onCalendarClick = onCalendarClick
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Bottom Navigation
            BottomNavBar()
        }
    }
}


@Composable
private fun TeacherList(
    teachers: List<Teacher>,
    imageLoader: ImageLoader,
    onTeacherClick: (Teacher) -> Unit,
    onMapClick: (Teacher) -> Unit,
    onCalendarClick: (Teacher) -> Unit,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 4.dp)
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

@Composable
private fun BottomNavBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(primaryColor)
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomNavItem(
            icon = Icons.Filled.Favorite,
            label = "Favorites",
            color = Color.Gray
        )
        BottomNavItem(
            icon = Icons.Filled.Bookmark,
            label = "Orders",
            color = textTertiary
        )
    }
}

@Composable
private fun BottomNavItem(
    icon: ImageVector,
    label: String,
    color: Color
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = color,
            modifier = Modifier.size(28.dp)
        )
        Text(
            text = label,
            color = color,
            style = MaterialTheme.typography.bodySmall
        )
    }
}
