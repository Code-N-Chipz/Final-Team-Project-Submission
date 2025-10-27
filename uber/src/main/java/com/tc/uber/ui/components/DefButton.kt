package com.tc.uber.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import theme.primaryColor
import theme.textPrimary
import theme.typography

@Composable
fun DefButton(modifier : Modifier, btnText : String, onBtnClick : () -> Unit){
    Button(onClick = onBtnClick,
        shape = RoundedCornerShape(16),
        colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
        modifier = modifier.dropShadow(
            shape = RoundedCornerShape(20.dp),
            shadow = Shadow(
                radius = 10.dp,
                spread = 6.dp,
                color = Color(0xFFfae1cf),
                offset = DpOffset(x = 4.dp, 4.dp)
            )
        )
    ) {
        Text(btnText, color = textPrimary, style = typography.bodyLarge,
            modifier = Modifier.padding(8.dp))
    }
}