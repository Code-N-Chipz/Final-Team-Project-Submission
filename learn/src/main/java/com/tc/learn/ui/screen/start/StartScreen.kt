package com.tc.learn.ui.screen.start

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun StartScreen(
    modifier: Modifier = Modifier,
    name: String
) {
    Column(modifier = modifier.fillMaxSize()) {
        Text("Start Screen Preview")
        Spacer(modifier = Modifier.height(14.dp))
        Text("Hello, $name")
    }
}

//@Preview(showBackground = true)
//@Composable
//fun StartScreenPreview() {
//    MaterialTheme {
//        StartScreen()
//    }
//
//}

@Preview(showBackground = true)
@Composable
fun StartScreenPreview() {
    StartScreen(
        Modifier,
        "Test")
}

