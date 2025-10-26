package com.tc.laundry.ui.comon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import theme.primaryColor

@Composable
fun LineInfo(
    modifier: Modifier = Modifier,
    text: String = ""
){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 18.dp)
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color.LightGray
        )

        Spacer(modifier = Modifier.width(8.dp))

        HorizontalSpacerGrayLine()
    }
}
@Composable
fun HorizontalSpacerGrayLine(
    modifier: Modifier = Modifier
){
    Spacer(
        modifier = modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color.Gray)
    )
}

@Composable
fun PrimaryButtonColour(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    text: Int = 0
){
    Button(
        onClick = {
            onClick()
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = primaryColor
        ),
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
            .size(width = 258.dp, height = 56.dp)
    ) {
        Text(
            text = stringResource(text)
        )
    }
}