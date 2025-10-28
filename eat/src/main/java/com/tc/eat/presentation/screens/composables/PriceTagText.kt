package com.tc.eat.presentation.screens.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import java.util.Locale

@Composable
fun PriceTagText(
    modifier: Modifier = Modifier,
    price: Float,
    style: TextStyle = MaterialTheme.typography.bodyMedium
) {
    val formattedPrice = String.format(Locale.US, "$%.2f", price)
    Text(
        modifier = modifier,
        text = formattedPrice,
        style = style,
        fontWeight = FontWeight.SemiBold
    )
}