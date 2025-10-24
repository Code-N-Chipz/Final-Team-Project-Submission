package com.tc.laundry.ui.startpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tc.laundry.R
import com.tc.laundry.ui.TopBar
import theme.ICLICKIPAYTheme
import theme.primaryColor

@Composable
fun LaundryStartPage(
    modifier: Modifier = Modifier
){

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
              .fillMaxSize()
            .padding(20.dp)
    ){
        TopBar(
            icon = com.tc.design.R.drawable.arrow_left_orange_icon
        )

        Image(
            painter = painterResource(R.drawable.laundry),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 112.dp)
                .size(width = 262.dp, height = 212.dp)
        )

        Spacer(
            modifier = Modifier.height(43.dp)
        )

        Text(
            text = stringResource(R.string.laundry_start_page),
            fontSize = 32.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(
            modifier = Modifier.height(18.dp)
        )

        Text(
            text = stringResource(R.string.laundry_info_start_page),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.LightGray
        )

        Spacer(
            modifier = Modifier.height(77.dp)
        )

        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = primaryColor
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .size(width = 258.dp, height = 56.dp)
        ) {
            Text(
                text = stringResource(R.string.laundry_button_start_page)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LaundryStartPagePreview(){
    ICLICKIPAYTheme {
        LaundryStartPage()
    }
}