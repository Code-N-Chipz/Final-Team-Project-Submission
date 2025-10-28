package com.tc.eat.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.tc.eat.domain.util.FoodIconCategories
import theme.primaryIconColor

@Composable
fun FoodIconButton(food: FoodIconCategories) {
    IconButton(
        onClick = {},
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .shadow(
                elevation = 8.dp, // Adjust the elevation for shadow intensity
                shape = CircleShape, // Match the shape of the IconButton
                ambientColor = Color.Black.copy(alpha = 0.2f), // Customize ambient shadow color and opacity
                spotColor = Color.Black.copy(alpha = 0.4f) // Customize spot shadow color and opacity
            )
            .size(70.dp)
            .background(color = primaryIconColor, shape = CircleShape)

    ) {
        Icon(
            modifier = Modifier.size(36.dp),
            painter = painterResource(food.icon),
            contentDescription = "",
            tint = Color.Unspecified
        )
    }

}