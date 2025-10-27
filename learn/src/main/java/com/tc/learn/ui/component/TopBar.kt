//package com.tc.learn.ui.component
//
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.graphics.vector.ImageVector
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun TopBar(
//    title: String,
//    isSearchScreen: Boolean,
//    onHomeClick: () -> Unit,
//    onBackClick: () -> Unit
//) {
//    TopAppBar(
////        //Apply theme
////        colors = ,
//        //Do I apply theme on each composable, or is there a place in the root i can set it?
//
//        title = {
//            Text(
//                text = title,
//                style = MaterialTheme.typography.titleLarge,
//                modifier = Modifier.fillMaxWidth(),
//                textAlign = TextAlign.Center
//            )
//        },
//        navigationIcon = {
//            IconButton(onClick = {
//                if (isSearchScreen) onBackClick() else onHomeClick()
//            }) {
//                Icon(
//                    imageVector = if (isSearchScreen) Icons.Filled.ArrowBack else Icons.Filled.Home,
//                    contentDescription = if (isSearchScreen) "Back" else "Home"
//                )
//            }
//        }
//    )
//}
