package com.tc.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.tc.design.R as Design

@Composable
fun CircleShapeImage(
    imageUrl: String? = null,
    size: Dp = 64.dp,
    contentDescription: String? = "Personal Image",
    modifier: Modifier = Modifier
) {
    val avatarModifier = modifier
        .size(size)
        .clip(CircleShape)

    if (imageUrl.isNullOrBlank()) {
        Box(avatarModifier.background(theme.primaryColor))
        return
    }
    AsyncImage(
        model = imageUrl,
        contentDescription = contentDescription,
        modifier = avatarModifier,
        contentScale = ContentScale.Crop,
        placeholder = painterResource(Design.drawable.image_icon),
        error = painterResource(Design.drawable.image_icon)
    )

}


@Preview(showBackground = true, backgroundColor = 0xFFFFFFF)
@Composable
private fun CircleShapeImagePreview(){
    CircleShapeImage()
}