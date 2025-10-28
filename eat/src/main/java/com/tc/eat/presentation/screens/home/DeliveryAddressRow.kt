package com.tc.eat.presentation.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tc.design.R
import theme.primaryIconColor

@Composable
fun DeliveryAddressRow(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
        ) {
            Text(
                text = stringResource(com.tc.eat.R.string.delivery_location),
                style = MaterialTheme.typography.headlineMedium,
                color = primaryIconColor,
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(com.tc.eat.R.string.address),
                    style = MaterialTheme.typography.bodyLarge,
                    color = primaryIconColor,
                    fontSize = 20.sp,
                )
                IconButton(onClick = {}) {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(R.drawable.pencil_icon),
                        contentDescription = "",
                        tint = Color.Unspecified
                    )
                }
            }
        }
    }
}