package com.tc.learn.ui.screen.search

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
import coil.Coil.imageLoader
import com.tc.learn.data.model.Teacher
import com.tc.learn.ui.component.TeacherCard
import com.tc.learn.ui.navigation.AppNavigator
import com.tc.learn.R

@Composable
fun OverlayerBox(
    modifier: Modifier = Modifier,
    navigator: AppNavigator,
    onTeacherClick: (Teacher) -> Unit,
    onMapClick: (Teacher) -> Unit,
) {

    Box(
        modifier = modifier
            .width(350.dp)
            .height(250.dp)
            .background(Color.White)
            .zIndex(1f) // ensure overlay is above both backgrounds
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
                    painter = painterResource(R.drawable.location_crosshair_icon),
                    contentDescription = null,
                    modifier = Modifier
                        .clickable(
                            onClick = { }
                        )
                )
            }

            HorizontalSpacerGrayLine()

//            TeacherCard(
//                modifier = Modifier,
//                teacher = teacher,
//                imageLoader = imageLoader,
//                onTeacherClick = onTeacherClick
//            ) {
//
//            }

            HorizontalSpacerGrayLine()

            Search(onClickSearchIcon = { })

            Button(
                modifier = Modifier
                    .size(width = 257.dp, height = 33.dp),
                onClick = {}
            ) {
                Text(
//                    text = stringResource(R.string.learn_search_button_home_page_overlayer_box),
                    text = "test",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

        }
    }
}

//@Composable
//private fun TeacherInfo(
//    modifier: Modifier = Modifier,
//    navController: NavController,
//) {
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.SpaceBetween,
//        modifier = modifier
//            .fillMaxWidth()
//            .height(63.dp)
//            .clickable(
//                onClick = {
//                    navController.navigate("your_learn")
//                }
//            )
//    ) {
//        Info(
//            text = stringResource(R.string.learn_choose_dates_home_page_overlayer_box)
//        )
//
//        VerticalSpacerGrayLine()
//
//        Info(
//            text = stringResource(R.string.learn_KG_home_page_overlayer_box)
//        )
//
//        VerticalSpacerGrayLine()
//
//        Info(
//            text = stringResource(R.string.learn_dry_home_page_overlayer_box)
//        )
//
//        VerticalSpacerGrayLine()
//
//        Info(
//            text = stringResource(R.string.learn_ironing_home_page_overlayer_box)
//        )
//    }
//}

@Suppress("UnusedPrivateMember")
@Composable
private fun Info(
    modifier: Modifier = Modifier,
    text: String = "",
) {
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
    onClickSearchIcon: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                onClick = onClickSearchIcon
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .align(Alignment.Center)
        ) {
            Text(
                text = stringResource(R.string.learn_search_home_page_overlayer_box),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray
            )

            Icon(
                painter = painterResource(R.drawable.magnifying_glass_grey_icon),
                contentDescription = null,
                tint = Color.Gray
            )
        }
    }
}

@Suppress("UnusedPrivateMember")
@Composable
private fun VerticalSpacerGrayLine(
    modifier: Modifier = Modifier,
) {
    Spacer(
        modifier = modifier
            .fillMaxHeight()
            .width(1.dp)
            .background(Color.Gray)
    )
}


@Composable
fun HorizontalSpacerGrayLine(
    modifier: Modifier = Modifier,
    thickness: Float = 1f,        // height of the line in dp
    color: Color = Color.LightGray,
    paddingHorizontal: Float = 0f, // optional horizontal padding
) {
    Spacer(
        modifier = modifier
            .fillMaxWidth()
            .height(thickness.dp)
            .padding(horizontal = paddingHorizontal.dp)
            .background(color)
    )
}