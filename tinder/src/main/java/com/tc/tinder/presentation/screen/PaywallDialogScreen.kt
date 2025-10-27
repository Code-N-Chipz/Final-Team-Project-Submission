package com.tc.tinder.presentation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.tc.tinder.R
import com.tc.tinder.presentation.viewmodel.PaywallType
import theme.ICLICKIPAYTheme
import theme.primaryColor

enum class PaywallType { BOOST, LIKE, SUPER_LIKE }

@Composable
fun SimplePaywallDialog(
    type: PaywallType,
    onDismiss: () -> Unit,
    onPrimaryClick: () -> Unit
) {
    // Straightforward mapping (no helpers)
    val title: String
    val message: String
    val ctaText: String
    val imageRes: Int
    when (type) {
        PaywallType.BOOST -> {
            title = "Boost your profile"
            message = "Be the top profile in your area for\n30 minutes to get more matches"
            ctaText = "Boost"
            imageRes = R.drawable.boostpaymentbackground
        }
        PaywallType.LIKE -> {
            title = "Like a lot!"
            message = "If it's mutual,\nyou can talk together"
            ctaText = "Like"
            imageRes = R.drawable.liketpaymentbackground
        }
        PaywallType.SUPER_LIKE -> {
            title = "Superlike me!"
            message = "Indicate visually\nthat you are interested"
            ctaText = "Superlike"
            imageRes = R.drawable.superlikepaymentbackground
        }
    }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(24.dp),
            tonalElevation = 8.dp,
            modifier = Modifier
                .fillMaxWidth(0.90f)   // wider like the Figma
                .heightIn(min = 480.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                        .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.height(12.dp))
                    Text(
                        text = message,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.height(32.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth().padding(top = 64.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        TextButton(
                            onClick = onDismiss,
                            modifier = Modifier.weight(1f)
                        ) { Text("Not now") }

                        Spacer(Modifier.width(12.dp))

                        TextButton (
                            onClick = onPrimaryClick,
                            modifier = Modifier.weight(1f),
                        ) {
                            Text(
                                text = ctaText,
                                color = primaryColor, // only text is colored
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }
    }
}





