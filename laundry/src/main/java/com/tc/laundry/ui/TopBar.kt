package com.tc.laundry.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import theme.primaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String = "",
    icon: Int,
    modifier: Modifier = Modifier
){
    TopAppBar(
        title = {
            Text( text = title )
                },
        navigationIcon = {
            IconButton(
                onClick = {}
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
        modifier = Modifier
            .background(Color.Transparent),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        )
    )
}