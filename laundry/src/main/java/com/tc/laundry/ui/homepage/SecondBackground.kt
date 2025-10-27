package com.tc.laundry.ui.homepage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tc.laundry.R
import theme.primaryColor

@Composable
fun SecondBackground(
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(96.dp)
            .background(primaryColor)
    ){
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .align(Alignment.BottomCenter)
        ){
            ButtonInOrangeBackground(
                icon = com.tc.design.R.drawable.heart_icon,
                text = R.string.laundry_favorites_home_page_second_background,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 40.dp)
            )

            ButtonInOrangeBackground(
                icon = com.tc.design.R.drawable.receipt_icon,
                text = R.string.laundry_orders_home_page_second_background,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 40.dp)
            )
        }
    }
}

@Composable
private fun ButtonInOrangeBackground(
    modifier: Modifier = Modifier,
    icon: Int = 0,
    text: Int = 0
){
    Box(
        modifier = modifier
            .background(color = primaryColor)
            .clickable(
                onClick = {}
            )
            .padding(bottom = 16.dp)
    ){
        Row {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                tint = Color.White
            )

            Text(
                text = stringResource(text),
                color = Color.White,
                modifier = Modifier
                    .padding(start = 8.dp),
                fontSize = 17.sp
            )
        }
    }
}
