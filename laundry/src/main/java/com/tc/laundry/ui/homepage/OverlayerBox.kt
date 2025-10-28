package com.tc.laundry.ui.homepage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.tc.laundry.R
import com.tc.laundry.ui.comon.HorizontalSpacerGrayLine

import theme.primaryColor

@Composable
fun OverlayerBox(
    modifier: Modifier = Modifier,
    navController: NavController
){

    Box(
        modifier = modifier
            .width(350.dp)
            .height(250.dp)
            .background(Color.White)
            .zIndex(1f) // ensure overlay is above both backgrounds
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Johannesburg, 1 Road Ubuntu",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Icon(
                    painter = painterResource(com.tc.design.R.drawable.location_crosshair_icon),
                    contentDescription = null,
                    tint = primaryColor,
                    modifier = Modifier
                        .clickable(
                            onClick = {
                                navController.navigate("map")
                            }
                        )
                )
            }

            HorizontalSpacerGrayLine()

            LaundryInfo(
                navController = navController
            )

            HorizontalSpacerGrayLine()

            Search(onClickSearchIcon = {  })

            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = primaryColor
                ),
                modifier = Modifier
                    .size(width = 257.dp, height = 33.dp),
                onClick = {}
            ) {
                Text(
                    text = stringResource(R.string.laundry_search_button_home_page_overlayer_box),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

        }
    }
}

@Composable
private fun LaundryInfo(
    modifier: Modifier = Modifier,
    navController: NavController
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .height(63.dp)
            .clickable(
                onClick = {
                    navController.navigate("calendar")
                }
            )
    ) {
        Info(
            text = stringResource(R.string.laundry_choose_dates_home_page_overlayer_box)
        )

        VerticalSpacerGrayLine()

        Info(
            text = stringResource(R.string.laundry_KG_home_page_overlayer_box)
        )

        VerticalSpacerGrayLine()

        Info(
            text = stringResource(R.string.laundry_dry_home_page_overlayer_box)
        )

        VerticalSpacerGrayLine()

        Info(
            text = stringResource(R.string.laundry_ironing_home_page_overlayer_box)
        )
    }
}

@Composable
private fun Info(
    modifier: Modifier = Modifier,
    text: String = ""
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = Color.LightGray
        )

        Text(
            text = "02-10",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun Search(
    modifier: Modifier = Modifier,
    onClickSearchIcon: () -> Unit = {}
){
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                onClick = onClickSearchIcon
            )
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .align(Alignment.Center)
        ) {
            Text(
                text = stringResource(R.string.laundry_search_home_page_overlayer_box),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray
            )

            Icon(
                painter = painterResource(com.tc.design.R.drawable.magnifying_glass_grey_icon),
                contentDescription = null,
                tint = Color.Gray
            )
        }
    }
}

@Composable
private fun VerticalSpacerGrayLine(
    modifier: Modifier = Modifier
){
    Spacer(
        modifier = modifier
            .fillMaxHeight()
            .width(1.dp)
            .background(Color.Gray)
    )
}
