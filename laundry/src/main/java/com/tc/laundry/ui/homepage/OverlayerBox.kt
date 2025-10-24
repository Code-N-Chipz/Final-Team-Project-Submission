package com.tc.laundry.ui.homepage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun OverlayerBox(
    modifier: Modifier = Modifier
){

    Box(
        modifier = modifier
            .width(350.dp)
            .height(250.dp)
            .background(Color.White)
            .zIndex(1f) // ensure overlay is above both backgrounds
            .padding(horizontal = 8.dp)
    ){
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row {  }
        }
    }
}