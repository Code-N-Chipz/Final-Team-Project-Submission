package com.tc.delivery.ui.homepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tc.delivery.R
import com.tc.delivery.ui.comon.HorizontalSpacerGrayLine
import theme.primaryColor

@Composable
fun ThirdBackground(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(14.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row {
                Text(
                    text = "Delivery men",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.width(14.dp))

                Text(
                    text = "120",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.LightGray
                )
            }

            Icon(
                painter = painterResource(com.tc.design.R.drawable.option_slider_orange_icon),
                contentDescription = null,
                tint = primaryColor,
                modifier = Modifier
                    .clickable{

                    }
            )
        }

        Spacer(modifier = Modifier.height(21.dp))

        CardView(
            navController = navController
        )
    }
}

@Composable
private fun CardView(
    modifier: Modifier = Modifier,
    navController: NavController
){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable{

            },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            ) {
            Image(
                painter = painterResource(R.drawable.delivery_man),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Column(
                modifier = Modifier
                    .padding(13.dp)
            ) {

                Text(
                    text = "Jessy Jones",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = "London",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.LightGray,
                    modifier = Modifier
                        .padding(bottom = 6.dp)
                )

                HorizontalSpacerGrayLine()

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.filled_star),
                            contentDescription = null,
                            tint = primaryColor,
                            modifier = Modifier
                                .size(width = 17.dp, height = 15.dp)
                                .padding(end = 8.dp)
                        )

                        Text(
                            text = "4.8",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(com.tc.design.R.drawable.distance_icon),
                            contentDescription = null,
                            modifier = Modifier
                                .size(width = 17.dp, height = 17.dp)
                                .padding(end = 8.dp)
                        )

                        Text(
                            text = "500 m",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Text(
                        text = "$ 15/kg",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}
