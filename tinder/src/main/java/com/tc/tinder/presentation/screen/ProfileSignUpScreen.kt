package com.tc.tinder.presentation.screen
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tc.tinder.presentation.ui.button.ButtonWithTextOnly
import com.tc.tinder.presentation.ui.profiledetail.LabeledField
import com.tc.tinder.presentation.ui.profiledetail.PhotoRow
import com.tc.tinder.presentation.ui.topbar.ProfileSignUpTopAppBar

@Composable
fun ProfileSignUpScreen(
    onBackClick: () -> Unit = {},
    onAddPhotoClick: () -> Unit = {},
    onNextClick: () -> Unit = {},
    firstPhotoUri: Uri? = null
) {
    var description by remember { mutableStateOf(TextFieldValue("")) }
    var city by remember { mutableStateOf(TextFieldValue("")) }
    var dob by remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { ProfileSignUpTopAppBar(onBackClick) },
        bottomBar = {
            Surface(color = MaterialTheme.colorScheme.surface) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 16.dp)
                ) {
                    ButtonWithTextOnly("Next",onNextClick)
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                LabeledField(
                    label = "Description",
                    value = description,
                    onValueChange = { description = it },
                    singleLine = false,
                    minLines = 3
                )
            }

            item {
                LabeledField(
                    label = "City",
                    value = city,
                    onValueChange = { city = it }
                )
            }

            item {
                LabeledField(
                    label = "Date of birth",
                    value = dob,
                    onValueChange = { dob = it },
                    keyboardOptions = KeyboardOptions.Default
                )
            }

            item {
                Text(
                    text = "Add more photos",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
                )
                Spacer(Modifier.height(8.dp))
                PhotoRow(
                    firstPhotoUri = firstPhotoUri,
                    onAddPhotoClick = onAddPhotoClick
                )
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}



@Preview(showBackground = true)
@Composable
private fun ProfileSignUpScreenPreview() {
    ProfileSignUpScreen(
        onBackClick = {},
        onAddPhotoClick = {},
        onNextClick = {},
        firstPhotoUri = null
    )
}