package com.tc.auth.signup.signupscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import kotlinx.coroutines.flow.MutableStateFlow


@Composable
fun SignupFormScreen(viewModel: SignUpScreenViewModel, modifier: Modifier = Modifier) {
    val firstName by viewModel.firstName.collectAsState()
    val lastName by viewModel.lastName.collectAsState()
    val gender by viewModel.gender.collectAsState()
    val email by viewModel.email.collectAsState()
    val phone by viewModel.phone.collectAsState()

    Column (modifier = modifier.padding(16.dp)) {
        Text("Complete the form", style = MaterialTheme.typography.h6)

        Spacer(modifier = Modifier.height(8.dp))

        // Top image Todo: need to replace with the image and need to figure out where to keep it, core-Ui or someother
        Image(
            painter = rememberAsyncImagePainter("https://via.placeholder.com/150"),
            contentDescription = "Signup Illustration",
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            contentScale = ContentScale.Crop
        )

        OutlinedTextField(
            value = firstName,
            onValueChange = { viewModel.firstName.value = it },
            label = { Text("First Name") }
        )
        OutlinedTextField(
            value = lastName,
            onValueChange = { viewModel.lastName.value = it },
            label = { Text("Last Name") }
        )
        OutlinedTextField(
            value = gender,
            onValueChange = { viewModel.gender.value = it },
            label = { Text("Gender") }
        )
        OutlinedTextField(
            value = email,
            onValueChange = { viewModel.email.value = it },
            label = { Text("Email") }
        )
        OutlinedTextField(
            value = phone,
            onValueChange = { viewModel.phone.value = it },
            label = { Text("Phone Number") }
        )

        Spacer(modifier = Modifier.height(16.dp))

//       todo: need to make the button from commonUI
        Button (
            onClick = { /* wait for navigation changes or need to add internal */ },
            modifier = Modifier.fillMaxWidth()) {
            Text("Confirm")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSignupFormScreen() {
    val mockViewModel = object : SignUpScreenViewModel() {
        override val firstName = MutableStateFlow("John")
        override val lastName = MutableStateFlow("Doe")
        override val gender = MutableStateFlow("Male")
        override val email = MutableStateFlow("john@example.com")
        override val phone = MutableStateFlow("+33 634292088")
    }

    SignupFormScreen(viewModel = mockViewModel)
}