package com.tc.babysitter.addchild

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.tc.ui.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YourChildListScreen(
    navController: NavHostController,
) {
    val viewModel: ChildViewModel = viewModel()

    // Mock/static children
    val mockChildren = listOf(
        ChildData("Sophia", "Female", "5 years"),
        ChildData("Liam", "Male", "3 years")
    )

    // Combine ViewModel child with mock data if ViewModel child has data
    val children = remember(viewModel.childData) {
        val list = mutableListOf<ChildData>()
        list.addAll(mockChildren)
        if (viewModel.childData.name.isNotBlank()) {
            list.add(viewModel.childData)
        }
        list
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Your Child",
                            fontWeight = FontWeight.SemiBold,
                            color = DarkText,
                            modifier = Modifier.align(Alignment.Center),
                            textAlign = TextAlign.Center
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Outlined.Home,
                            contentDescription = "Home",
                            tint = PrimaryOrange
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("childdetail") // Add Child Screen
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Add,
                            contentDescription = "Add Child",
                            tint = PrimaryOrange
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        bottomBar = {
            Button(
                onClick = {
                    if (children.isNotEmpty()) {
                        navController.navigate("selectbabysitter")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryOrange),
                shape = RoundedCornerShape(6.dp)
            ) {
                Text("Next", color = Color.White, fontSize = 18.sp)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (children.isEmpty()) {
                Text("No children added yet.", color = GrayText)
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(children) { child ->
                        ChildCard(
                            childData = child,
                            onChildClick = {
                                navController.navigate("selectbabysitter")
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ChildCard(childData: ChildData, onChildClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, GrayText.copy(alpha = 0.3f), RoundedCornerShape(12.dp))
            .background(Color.White)
            .clickable { onChildClick() }
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            // Show child photo if available
            val painter = if (childData.photoUri != null) {
                rememberAsyncImagePainter(childData.photoUri)
            } else {
                painterResource(R.drawable.ic_child) // placeholder image
            }
            Image(
                painter = painter,
                contentDescription = childData.name,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )

            Column {
                Text(
                    text = childData.name,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = DarkText
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Age: ${childData.age.ifBlank { "N/A" }}",
                    color = GrayText,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Sex: ${childData.sex.ifBlank { "N/A" }}",
                    color = GrayText,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewYourChildListScreen() {
    YourChildListScreen(navController = rememberNavController())
}
