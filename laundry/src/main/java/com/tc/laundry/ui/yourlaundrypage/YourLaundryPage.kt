package com.tc.laundry.ui.yourlaundrypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tc.laundry.R
import com.tc.laundry.ui.TopBar
import theme.ICLICKIPAYTheme

@Composable
fun YourLaundryPage(
    modifier: Modifier = Modifier
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        TopBar(
            icon = com.tc.design.R.drawable.home_icon,
            title = "Your Laundry"//stringResource(R.string.laundry_header_your_laundry_page)
        )

       LineInfo(
           text = "laundry/kg"//stringResource(R.string.laundry_laundry_kg_your_laundry_page)
       )
    }
}

@Composable
private fun LineInfo(
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
private fun HorizontalSpacerGrayLine(
    modifier: Modifier = Modifier
){
    Spacer(
        modifier = modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color.Gray)
    )
}

@Preview(showBackground = true)
@Composable
private fun YourLaundryPagePreview(){
    ICLICKIPAYTheme {
        YourLaundryPage()
    }
}