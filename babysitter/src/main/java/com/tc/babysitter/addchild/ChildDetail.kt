package com.tc.babysitter.addchild


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tc.ui.R

// Colors
val PrimaryOrange = Color(0xFFFF7A1A)
val DarkText = Color(0xFF2C2C2C)
val GrayText = Color(0xFF888888)
val PoppinsFamily = FontFamily.SansSerif


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YourChildScreen(navController: NavHostController) {
    var name by remember { mutableStateOf("") }
    var selectedSex by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var selectedAge by remember { mutableStateOf("Select Age") }

    // List of ages 1–13
    val ages = (1..13).map { "$it years" }

    val viewModel: ChildViewModel = viewModel()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
//                    Text(
//                        "Your child",
//                        fontFamily = PoppinsFamily,
//                        fontWeight = FontWeight.SemiBold,
//                        color = DarkText,
//                        textAlign = TextAlign.Center
//                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 48.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Your child",
                            fontFamily = PoppinsFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = DarkText,
                            textAlign = TextAlign.Center
                        )
                    }
                }, navigationIcon = {
                    IconButton(onClick = {  }) {
                        Icon(
                            imageVector = Icons.Outlined.Home,
                            contentDescription = "Home",
                            tint = colorResource(R.color.brand_orange) // Orange color
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        bottomBar = {
            Button(
                onClick = {
                    viewModel.updateName(name)
                    viewModel.updateSex(selectedSex)
                    viewModel.updateAge(selectedAge)
                    navController.navigate("childphoto") },
                enabled = name.isNotBlank() && selectedSex.isNotBlank() && selectedAge.isNotBlank(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.brand_orange),
                    disabledContainerColor = colorResource(R.color.brand_orange),
                    contentColor = Color.White,
                    disabledContentColor = Color.White),
                shape = RoundedCornerShape(6.dp)
            ) {
                Text("Next", color = Color.White, fontSize = 18.sp)
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Name
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorResource(R.color.brand_orange),
                    unfocusedBorderColor = colorResource(R.color.brand_grey)
                )
            )

            // Sex
            Text(
                "Sex",
                fontFamily = PoppinsFamily,
                fontWeight = FontWeight.SemiBold,
                color = DarkText
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                SexOption(label = "Male", selected = selectedSex == "Male") { selectedSex = "Male" }
                SexOption(label = "Female", selected = selectedSex == "Female") {
                    selectedSex = "Female"
                }
            }

            // Age dropdown
            Text(
                "Age",
                fontFamily = PoppinsFamily,
                fontWeight = FontWeight.SemiBold,
                color = DarkText
            )
//            Box(modifier = Modifier.fillMaxWidth()) {
//                OutlinedTextField(
//                    value = selectedAge,
//                    onValueChange = {},
//                    label = { Text("Age") },
//                    readOnly = true,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .clickable { expandedAge = true },
//                    colors = OutlinedTextFieldDefaults.colors(
//                        focusedBorderColor = PrimaryOrange,
//                        unfocusedBorderColor = GrayText
//                    )
//                )
//
//                DropdownMenu(
//                    expanded = expandedAge,
//                    onDismissRequest = { expandedAge = false },
//                    modifier = Modifier.background(Color.White)
//                ) {
//                    for (i in 1..13) {
//                        DropdownMenuItem(
//                            text = { Text("$i Years") },
//                            onClick = {
//                                selectedAge = "$i Years"
//                                expandedAge = false
//                            }
//                        )
//                    }
//                }
//            }
//        }


            Box(modifier = Modifier.fillMaxWidth()) {
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    OutlinedTextField(
                        value = selectedAge,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Age") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        colors = ExposedDropdownMenuDefaults.textFieldColors(
                            focusedIndicatorColor = colorResource(R.color.brand_orange),
                            unfocusedIndicatorColor =colorResource(R.color.brand_grey),
                            cursorColor =colorResource(R.color.brand_orange),
                            focusedLabelColor = colorResource(R.color.brand_orange),
                            unfocusedLabelColor =colorResource(R.color.brand_grey)
                        ),
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier.background(colorResource(R.color.white))
                    ) {
                        ages.forEach { age ->
                            DropdownMenuItem(
                                text = { Text(age) },
                                onClick = {
                                    selectedAge = age
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }
        }

    }
}

@Composable
fun SexOption(label: String, selected: Boolean, onSelect: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { onSelect() }
    ) {
        Text(
            label,
            color = if (selected) DarkText else GrayText,
            fontFamily = PoppinsFamily,
            fontWeight = FontWeight.Medium
        )
        Box(
            modifier = Modifier
                .padding(start = 8.dp)
                .size(20.dp)
                .border(2.dp, if (selected) PrimaryOrange else GrayText, shape = CircleShape)
                .background(
                    if (selected) PrimaryOrange.copy(alpha = 0.2f) else Color.Transparent,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            if (selected) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .background(PrimaryOrange, shape = CircleShape)
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewYourChildScreen() {
    YourChildScreen(navController = rememberNavController())
}


