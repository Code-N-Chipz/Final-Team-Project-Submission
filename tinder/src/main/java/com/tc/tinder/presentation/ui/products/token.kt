package com.tc.tinder.presentation.ui.products

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tc.tinder.domain.model.tokens.PaymentOption
import com.tc.tinder.presentation.ui.button.ButtonWithTextOnly
import theme.primaryColor
import theme.textPrimary


@Composable
fun TokenPayment(
    paymentPainter: Painter,
    contentDescription: String,
    paymentTitle: String,
    paymentMessage: String,
    options: List<PaymentOption>,
    selectedId: String? = null,                // which card is selected
    onOptionSelected: (PaymentOption) -> Unit = {},
    onNext: () -> Unit = {},
    onNoThanks: () -> Unit = {}
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            // Top hero image + copy (upper half)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.50f)
            ) {
                Image(
                    painter = paymentPainter,
                    contentDescription = contentDescription,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 120.dp, start = 24.dp, end = 24.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = paymentTitle,
                        style = MaterialTheme.typography.bodyMedium,
                        color = textPrimary,
                        fontSize = 26.sp
                    )
                    Text(
                        text = paymentMessage,
                        style = MaterialTheme.typography.bodyMedium,
                        color = textPrimary,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
            }

            // Options row (lower half, top section)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                options.forEach { opt ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clickable { onOptionSelected(opt) }
                    ) {
                        TokenQuantityBox(
                            quantity = opt.quantity,
                            itemName = opt.itemName,
                            price = opt.price,
                            onSale = opt.onSale,
                            discountLabel = opt.discountLabel,
                            selected = (opt.id == selectedId)   // ← darken when selected
                        )
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            ButtonWithTextOnly("Next", onNext)

            Text(
                text = "No, thanks",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                modifier = Modifier
                    .padding(top = 16.dp)
                    .align(Alignment.CenterHorizontally)
                    .clickable { onNoThanks() }
            )
        }
    }
}

@Composable
fun TokenQuantityBox(
    quantity: String,
    itemName: String,
    price: String,
    onSale: Boolean,
    discountLabel: String = "SAVE 25%",
    selected: Boolean = false
) {
    val corner = RoundedCornerShape(16.dp)

    // Stronger visual for selected
    val targetBg = if (selected)
        MaterialTheme.colorScheme.primary.copy(alpha = 0.10f)   // noticeable tint
    else
        MaterialTheme.colorScheme.surface

    val bg by animateColorAsState(targetBg, label = "optionBg")

    // Keep primary border if onSale, otherwise show primary when selected
    val targetBorder = when {
        onSale    -> primaryColor
        selected  -> MaterialTheme.colorScheme.primary
        else      -> Color.Transparent
    }
    val border by animateColorAsState(targetBorder, label = "optionBorder")

    // Slight scale bump on selection
    val scale by animateFloatAsState(targetValue = if (selected) 1.02f else 1f, label = "optionScale")

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopCenter
    ) {
        Surface(
            color = bg,
            shape = corner,
            tonalElevation = if (selected) 2.dp else 1.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
                .border(
                    width = if (onSale || selected) 3.dp else 2.dp,
                    color = border,
                    shape = corner
                )
                .aspectRatio(1f)               // square
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 20.dp, horizontal = 24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val textColor = if (onSale || selected) primaryColor
                else MaterialTheme.colorScheme.onSurface

                Text(quantity, style = MaterialTheme.typography.bodyMedium, fontSize = 28.sp, color = textColor)
                Text(itemName, style = MaterialTheme.typography.bodyMedium, fontSize = 16.sp, color = textColor)
                Text("\$$price", style = MaterialTheme.typography.bodyMedium, fontSize = 12.sp, color = textColor)
            }
        }

        if (onSale) {
            Surface(
                color = primaryColor,
                shape = RoundedCornerShape(8.dp),
                shadowElevation = 0.dp,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
            ) {
                Text(
                    text = discountLabel,
                    color = Color.White,
                    style = MaterialTheme.typography.labelMedium,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                )
            }
        }
    }
}