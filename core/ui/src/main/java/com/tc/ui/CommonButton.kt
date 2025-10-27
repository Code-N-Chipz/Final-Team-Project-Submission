package com.tc.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
private fun ShowButton() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        CommonButton()
    }
}

@Composable
fun CommonButton(
    text: String = "Hello",
    onClick: () -> Unit = {},
    color: ButtonColors = ButtonDefaults.buttonColors(containerColor = theme.primaryColor),
    width: Dp = 300.dp,
    height: Dp = 60.dp,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .width(width)
            .height(height)
    ) {
        // Drop layer behind the button (only this is offset)
        Box(
            modifier = Modifier
                .matchParentSize()
                .offset(x = 4.dp, y = 4.dp)
                .shadow(elevation = 10.dp, shape = RoundedCornerShape(8.dp), clip = false)
                .background(Color(0xFFfae1cf), RoundedCornerShape(8.dp))
        )
        Button(
            shape = RoundedCornerShape(8.dp),
            colors = color,
            onClick = onClick,
            modifier = Modifier.matchParentSize()
        ) {
            Text(
                text = text,
                style = theme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFFL)
@Composable
private fun CommonButtonPreview() {
    ShowButton()
}
