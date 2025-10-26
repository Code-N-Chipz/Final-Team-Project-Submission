package com.tc.auth.ui.signup.createcode

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.tc.auth.R

@Composable
fun CreateCodeScreen(
    viewModel: CreateCodeViewModel = hiltViewModel(),
    onSuccess: () -> Unit,
    modifier: Modifier = Modifier
) {
    val code by viewModel.createdCode.collectAsState()

    Column (modifier = modifier.padding(16.dp)) {

        // Top image Todo: need to replace with the image and need to figure out where to keep it, core-Ui or someother
        Image(
            painter = rememberAsyncImagePainter(R.drawable.generate_code),
            contentDescription = "Create your code Illustration",
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            contentScale = ContentScale.Crop
        )

        Text("Create your code", style = MaterialTheme.typography.h6)
        Text("Enter a 6-digit code to authenticate")

        Spacer(modifier = Modifier.height(8.dp))

        Row (horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            code.forEachIndexed { index, digit ->
                OutlinedTextField(
                    value = digit,
                    onValueChange = { viewModel.updateCreatedCode(index, it.take(1)) },
                    modifier = Modifier.width(40.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button (onClick = { viewModel.onSubmit(onSuccess, {} ) }, modifier = Modifier.fillMaxWidth()) {
            Text("Confirm")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCreateCodeScreen() {
//    val mockViewModel = CreateCodeViewModel().apply {
//        createdCode.value = listOf("", "", "", "", "", "")
//    }
    CreateCodeScreen(
        onSuccess = {}
        )
}