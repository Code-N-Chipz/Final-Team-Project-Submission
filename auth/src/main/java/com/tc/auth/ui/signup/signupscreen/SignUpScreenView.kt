package com.tc.auth.ui.signup.signupscreen

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.tc.auth.R



@Composable
fun SignupFormScreen(
    viewModel: SignUpScreenViewModel = hiltViewModel(),
    onSignUpSuccess: () -> Unit,
    modifier: Modifier = Modifier
) {
    val state = viewModel.uiState.collectAsState().value

    Column (modifier = modifier.padding(16.dp)) {
        Text("Complete the form", style = MaterialTheme.typography.h6)

        Spacer(modifier = Modifier.height(8.dp))

        // Top image: need to replace with the image and need to figure out where to keep it, core-Ui or someother
        Image(
            painter = rememberAsyncImagePainter(R.drawable.signup_form),
            contentDescription = "Signup Illustration",
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            contentScale = ContentScale.Crop
        )

        OutlinedTextField(
            value = state.firstName,
            onValueChange = viewModel::updateFirstName,
            label = { Text("First Name") }
        )
        OutlinedTextField(
            value = state.lastName,
            onValueChange = viewModel::updateLastName,
            label = { Text("Last Name") }
        )
        OutlinedTextField(
            value = state.gender,
            onValueChange = viewModel::updateGender,
            label = { Text("Gender") }
        )
        OutlinedTextField(
            value = state.email,
            onValueChange = viewModel::updateEmail,
            label = { Text("Email") },
            isError = state.error != null
        )
        OutlinedTextField(
            value = state.phone,
            onValueChange = viewModel::updatePhoneNumber,
            label = { Text("Phone Number") },
            isError = state.error != null
        )

        Spacer(modifier = Modifier.height(16.dp))
        state.error?.let { Text(it, color = Color.Red) }
//        need to make the button from commonUI
        Button (
            onClick = { viewModel.submit(onSignUpSuccess, {}) },
            enabled = !state.isLoading,
            modifier = Modifier.fillMaxWidth()) {
            Text("Confirm")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSignupFormScreen() {


    SignupFormScreen(
        onSignUpSuccess = {}
    )
}

