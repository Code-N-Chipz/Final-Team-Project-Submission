package com.tc.laundry.ui.placeorderpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tc.laundry.R
import com.tc.laundry.ui.comon.HorizontalSpacerGrayLine
import com.tc.laundry.ui.comon.PrimaryButtonColour
import theme.primaryColor

@Composable
fun PlaceOrderPage(
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .height(260.dp)
                .fillMaxWidth()
                .background(color = primaryColor)
                .padding(vertical = 16.dp, horizontal = 6.dp)
        ) {
            PlaceOrderTopBar(
                title = "Order",
                icon = com.tc.design.R.drawable.arrow_left_white_icon,
                endText = "Cancel"
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.background),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(42.dp)
                        .align(Alignment.Top)
                        .padding(start = 8.dp)
                )

                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .padding(start = 8.dp)
                ) {
                    Text(
                        text = "Laundry",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )

                    Text(
                        text = "Jenny Jones",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Column(
                modifier = Modifier
                    .padding(start = 32.dp)
            ) {

                Text(
                    text = "Date",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )

                Text(
                    text = "20 March, Thu - 14th",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(27.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    Icon(
                        painter = painterResource(com.tc.design.R.drawable.pin_orange_icon),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier
                            .size(23.dp)
                    )

                    Column(
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier
                            .padding(start = 12.dp)
                    ) {
                        Text(
                            text = "28 London \n United kingdom",
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    }
                }
            }
        }

        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(top = 17.dp, start = 13.dp, end = 13.dp)
        ){
            Text(
                text = "Laundry",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(15.dp))

            Info(
                header = "Cleaning"
            )

            Spacer(modifier = Modifier.height(9.dp))

            Info(
                header = "Dry cleaning"
            )

            Spacer(modifier = Modifier.height(9.dp))
            
            Info(
                header = "Ironing"
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier
                    .fillMaxWidth()
            ){
                Column{

                    Text(
                        text = "Subtotal",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Text(
                        text = "Delivery fees",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .padding(top = 17.dp)
                    )
                }

                Column{
                    Text(
                        text = "$110",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = primaryColor
                    )

                    Text(
                        text = "$0.00",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.LightGray,
                        modifier = Modifier
                            .padding(top = 17.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            HorizontalSpacerGrayLine()

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(top = 21.dp)
                    .fillMaxWidth()
            ){
                Text(
                    text = "Total amount",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.LightGray
                )

                Text(
                    text = "$ 110.00",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = primaryColor,
                    modifier = Modifier
                        .padding(top = 7.dp)
                )

                Spacer(modifier = Modifier.height(25.dp))

                PrimaryButtonColour(
                    text = R.string.laundry_place_order_button_place_order_page,
                )
            }
        }
    }
}

@Composable
private fun Info(
    modifier: Modifier = Modifier,
    header: String = ""
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
    ){
        Column{

            Text(
                text = header,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = "Remove",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = primaryColor
            )
        }

        Column{
            Text(
                text = "$15/kg",
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium
            )

            Text(
                text = "x5",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = primaryColor
            )
        }
    }

    Spacer(modifier = Modifier.height(11.dp))

    HorizontalSpacerGrayLine()


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PlaceOrderTopBar(
    modifier: Modifier = Modifier,
    title: String = "",
    icon: Int,
    onClick: () -> Unit = {},
    endText: String = ""
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        },
        navigationIcon = {
            IconButton(
                onClick = onClick
            ) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = modifier
                        .size(42.dp)
                )
            }
        },
        actions = {
            if (endText.isNotEmpty()) {
                Text(
                    text = endText,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
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

@Preview(showBackground = true)
@Composable
private fun PlaceOrderPagePreview() {
    PlaceOrderPage()
}