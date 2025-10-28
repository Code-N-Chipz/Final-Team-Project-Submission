package com.tc.tinder.presentation.ui.profiledetail

import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun LabeledField(
    label: String,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    singleLine: Boolean = true,
    minLines: Int = 1,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
            )
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = singleLine,
            minLines = minLines,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            keyboardOptions = keyboardOptions
        )
    }
}

@Composable
 fun PhotoRow(
    firstPhotoUri: Uri?,
    onAddPhotoClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        FirstPhotoCell(photoUri = firstPhotoUri, onAddPhotoClick = onAddPhotoClick)
        AddPhotoCell(onClick = onAddPhotoClick)
        AddPhotoCell(onClick = onAddPhotoClick)
    }
}

/** First tile: shows a plus button if empty; if filled, shows the image area and is clickable to replace. */
@Composable
 fun FirstPhotoCell(
    photoUri: Uri?,
    onAddPhotoClick: () -> Unit
) {
    val base = Modifier
        .size(width = 92.dp, height = 92.dp)
        .clip(RoundedCornerShape(10.dp))
        .border(
            BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)),
            RoundedCornerShape(10.dp)
        )

    if (photoUri == null) {
        // Empty with centered "+"
        Box(
            base
                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.2f))
                .clickable { onAddPhotoClick() },
            contentAlignment = Alignment.Center
        ) {
            Text("+", style = MaterialTheme.typography.titleLarge, textAlign = TextAlign.Center)
        }
    } else {
        // Show image (plug in your image loader here), still clickable to replace
        Box(
            base
                .clickable { onAddPhotoClick() }
        ) {
            // Example if you add Coil:
            // AsyncImage(model = photoUri, contentDescription = null,
            //            contentScale = ContentScale.Crop, modifier = Modifier.matchParentSize())
        }
    }
}

@Composable
fun AddPhotoCell(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(width = 92.dp, height = 92.dp)
            .clip(RoundedCornerShape(10.dp))
            .border(
                BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)),
                RoundedCornerShape(10.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text("+", style = MaterialTheme.typography.titleLarge, textAlign = TextAlign.Center)
    }
}