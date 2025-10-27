package com.tc.eat.presentation.screens.restaurant

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tc.design.R
import com.tc.eat.domain.entities.MenuItem
import com.tc.eat.presentation.screens.composables.PriceTagText
import theme.textTertiary

@Composable
fun MenuItemRow(menuItem: MenuItem) {
    var iconAvailability: Int
    var textColor: Color
    if (menuItem.isAvailable) {
        iconAvailability = R.drawable.dispo_green_icon
        textColor = Color.Black
    } else {
        iconAvailability = R.drawable.dispo_red_icon
        textColor = textTertiary
    }
    Column(modifier = Modifier.height(60.dp)) {
        MenuItemHeader(menuItem, textColor, iconAvailability)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.padding(start = 28.dp),
                text = menuItem.ingredientList(),
                style = MaterialTheme.typography.bodyMedium,
                color = textTertiary
            )
            if (menuItem.isAvailable) {
                IconButton(modifier = Modifier.size(32.dp), onClick = {}) {
                    Icon(
                        modifier = Modifier.size(32.dp),
                        painter = painterResource(R.drawable.circle_plus_oranage_icon),
                        contentDescription = "",
                        tint = Color.Unspecified
                    )
                }
            }
        }
    }
}

@Composable
fun MenuItemHeader(menuItem: MenuItem, textColor: Color, iconAvailability: Int){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .padding(top = 16.dp)
        ) {
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResource(iconAvailability),
                contentDescription = "",
                tint = Color.Unspecified
            )
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = menuItem.name,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                color = textColor,
                fontSize = 20.sp
            )
        }
        PriceTagText(
            modifier = Modifier.padding(top = 20.dp),
            price = menuItem.price,
            style = TextStyle(color = textColor, fontSize = 16.sp)
        )
    }
}