package com.tc.tinder.presentation.ui.tutorial

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TutorialCard(
    iconPainter: Painter,
    contentDescription: String? = null,
    text: String
) {

    Row()
    {

        Icon(
            painter = iconPainter,
            contentDescription = contentDescription,
            modifier = Modifier.padding(end = 16.dp),
            tint = Color.Unspecified
        )

        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Start,
            fontSize = 14.sp
        )

    }

}
