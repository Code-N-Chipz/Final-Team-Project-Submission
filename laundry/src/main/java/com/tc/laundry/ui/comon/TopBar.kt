package com.tc.laundry.ui.comon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import theme.primaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    title: String = "",
    icon: Int,
    onClick: () -> Unit = {},
    endText: String = ""
){
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold
            )
                },
        navigationIcon = {
            IconButton(
                onClick = onClick
            ) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = null,
                    tint = primaryColor,
                    modifier = modifier
                        .size(42.dp)
                        .background(
                            color = Color.White, // circular background color
                            shape = CircleShape
                        )
                )
            }
        },
        actions = {
            if(endText.isNotEmpty()){
                Text(
                    text = endText,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = primaryColor
                )
            }
        },
        modifier = Modifier
            .background(Color.Transparent),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        )
    )
}