package com.tc.uber.ui

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.tc.tcmap.domain.MarkerInfo
import com.tc.tcmap.domain.PersonInfo
import com.tc.tcmap.ui.MapType
import com.tc.tcmap.ui.TcMap
import com.tc.uber.R
import com.tc.uber.ui.components.StarRating
import com.tc.uber.ui.sheets.ThankYou
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import theme.buttonSecondary
import theme.buttonTertiary
import theme.poppins
import theme.primaryColor
import theme.typography
import com.tc.design.R as D

@Preview(showSystemUi = true)
@Composable
fun UberMapPage(onHome: () -> Unit = {}) {
    var isThankYouSheetVisible by rememberSaveable { mutableStateOf(false) }

    var isRideOptionsVisible by rememberSaveable { mutableStateOf(true) }

    var isRideTimerSheetVisible by rememberSaveable { mutableStateOf(false) }

    var isArrivedSheetVisible by rememberSaveable { mutableStateOf(false) }

    val onCloseSheet = {}
    val focusRequester = remember { FocusRequester().also { it.freeFocus() } }

    var focused by rememberSaveable { mutableStateOf(false) }

    val scope =  rememberCoroutineScope()

    LaunchedEffect(Unit) {
        delay(2000)
        focused = true
    }

    Scaffold(Modifier.fillMaxSize()) { innerPadding ->

        Box(
            Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            TcMap(MapType.SimpleMap,listOf()) {
            }

            RideOptions(Modifier.fillMaxSize(), isRideOptionsVisible) {
                isRideOptionsVisible = false
                isRideTimerSheetVisible = true
            }
            LaunchedEffect(isRideTimerSheetVisible) {
                if(isRideTimerSheetVisible)
                scope.launch(Dispatchers.IO) {
                    delay(10000)
                    if(!isRideOptionsVisible)
                    {
                        isArrivedSheetVisible = true
                        isRideTimerSheetVisible = false
                    }
                }
            }
            RideTimerSheet(isRideTimerSheetVisible, 10) {
                isRideTimerSheetVisible = false
                isRideOptionsVisible = true
            }

            ArrivedSheet(isArrivedSheetVisible) {
                isArrivedSheetVisible = false
                isThankYouSheetVisible = true
            }

            ThankYou(isThankYouSheetVisible){
                isThankYouSheetVisible = false
            }
        }

    }

}

@Composable
fun ArrivedSheet(isVisible: Boolean, onClose: () -> Unit) {
    if (isVisible) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {

            Card(
                shape = RoundedCornerShape(
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp,
                    topStart = 24.dp,
                    topEnd = 24.dp
                ),
                elevation = CardDefaults.cardElevation(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            ) {
                ConstraintLayout(
                    Modifier
                        .fillMaxWidth()
                        .clip(
                            RoundedCornerShape(
                                bottomStart = 0.dp,
                                bottomEnd = 0.dp,
                                topStart = 24.dp,
                                topEnd = 24.dp
                            )
                        )
                        .background(Color.White)
                ) {
                    ConstraintLayout(Modifier.fillMaxWidth()) {

                        val (dhRef, tvRef, rateRef,
                            starsRef,
                            d1Ref, d2Ref, pickIcRef,
                            vRef,
                            dropIcRef, pRef, dRef,
                            clearRef) = createRefs()

                        HorizontalDivider(
                            thickness = 2.dp,
                            modifier = Modifier
                                .width(48.dp)
                                .padding(top = 16.dp)
                                .constrainAs(dhRef) {
                                    bottom.linkTo(tvRef.top, margin = 16.dp)
                                    centerHorizontallyTo(parent)
                                },
                            color = Color.Gray
                        )

                        IconButton(
                            onClick = onClose,
                            modifier = Modifier.constrainAs(clearRef) {
                                top.linkTo(tvRef.top, margin = 2.dp)
                                bottom.linkTo(tvRef.bottom, margin = 2.dp)
                                end.linkTo(parent.end, margin = 24.dp)
                                height = Dimension.fillToConstraints
                            }) {

                            Icon(
                                painter = painterResource(D.drawable.cancel_grey_icon),
                                tint = primaryColor,
                                contentDescription = "close",
                            )
                        }

                        Text(
                            "You arrived !", style = typography.headlineMedium,
                            modifier = Modifier.constrainAs(tvRef) {
                                bottom.linkTo(d1Ref.top, margin = 36.dp)
                                centerHorizontallyTo(parent)
                            })

                        HorizontalDivider(modifier = Modifier.constrainAs(d1Ref) {
                            bottom.linkTo(pRef.top, margin = 24.dp)
                            end.linkTo(parent.end, margin = 0.dp)
                            start.linkTo(parent.start, margin = 0.dp)
                            width = Dimension.fillToConstraints
                        }, thickness = 0.6.dp, color = Color.LightGray)

                        Column(modifier = Modifier.constrainAs(pRef) {
                            bottom.linkTo(dRef.top, margin = 48.dp)
                            end.linkTo(parent.end, margin = 20.dp)
                            start.linkTo(parent.start, margin = 48.dp)
                            width = Dimension.fillToConstraints
                        }) {
                            Text("Pickup", style = typography.labelLarge)
                            Text(
                                "Pickup Location",
                                style = typography.labelLarge,
                                color = Color.Gray
                            )

                        }

                        Icon(
                            painter = painterResource(D.drawable.pin_blue_icon),
                            tint = buttonTertiary,
                            contentDescription = "pick icon",
                            modifier = Modifier
                                .constrainAs(pickIcRef) {
                                    top.linkTo(pRef.top)
                                    bottom.linkTo(pRef.bottom)
                                    start.linkTo(parent.start, margin = 4.dp)
                                    end.linkTo(pRef.start, margin = 4.dp)
                                    width = Dimension.fillToConstraints
                                    height = Dimension.fillToConstraints
                                }
                                .padding(4.dp)
                        )


                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .width(4.dp)
                                .constrainAs(vRef) {
                                    top.linkTo(pickIcRef.bottom, margin = 2.dp)
                                    bottom.linkTo(dropIcRef.top, margin = 2.dp)
                                    start.linkTo(pickIcRef.start, margin = 2.dp)
                                    end.linkTo(pickIcRef.end, margin = 2.dp)
                                    height = Dimension.fillToConstraints
                                }
                        )
                        {
                            Canvas(
                                modifier = Modifier
                                    .width(1.4.dp)
                                    .fillMaxHeight()
                            ) {
                                val dashHeight = 4.dp.toPx()
                                val gap = 4.dp.toPx()
                                var y = 0f
                                var i = 0
                                while (y < size.height) {
                                    drawLine(
                                        color = if (i < 4) buttonTertiary else buttonSecondary,
                                        start = Offset(0f, y),
                                        end = Offset(0f, y + dashHeight),
                                        strokeWidth = size.width
                                    )
                                    y += dashHeight + gap
                                    i++
                                }
                            }
                        }

                        Icon(
                            painter = painterResource(D.drawable.pin_green_icon),
                            tint = buttonSecondary,
                            contentDescription = "Drop icon",
                            modifier = Modifier
                                .constrainAs(dropIcRef) {
                                    top.linkTo(dRef.top)
                                    bottom.linkTo(dRef.bottom)
                                    start.linkTo(parent.start, margin = 4.dp)
                                    end.linkTo(dRef.start, margin = 4.dp)
                                    width = Dimension.fillToConstraints
                                    height = Dimension.fillToConstraints
                                }
                                .padding(4.dp)
                        )

                        Column(modifier = Modifier.constrainAs(dRef) {
                            bottom.linkTo(d2Ref.top, margin = 24.dp)
                            end.linkTo(parent.end, margin = 20.dp)
                            start.linkTo(parent.start, margin = 48.dp)
                            width = Dimension.fillToConstraints
                        }) {

                            Text("Drop", style = typography.labelLarge)
                            Text("Drop Location", style = typography.labelLarge, color = Color.Gray)
                        }


                        HorizontalDivider(modifier = Modifier.constrainAs(d2Ref) {
                            bottom.linkTo(rateRef.top, margin = 36.dp)
                            end.linkTo(parent.end, margin = 0.dp)
                            start.linkTo(parent.start, margin = 0.dp)
                            width = Dimension.fillToConstraints
                        }, thickness = 0.6.dp, color = Color.LightGray)

                        Text(
                            "Rate your trip with Gabriel", style = typography.labelLarge,
                            modifier = Modifier.constrainAs(rateRef) {
                                bottom.linkTo(starsRef.top, margin = 24.dp)
                                centerHorizontallyTo(parent)
                            })

                        StarRating(
                            modifier = Modifier.constrainAs(starsRef) {
                                bottom.linkTo(parent.bottom, margin = 24.dp)
                                centerHorizontallyTo(parent)
                            }, rating = 4
                        ) { newRating ->

                        }

                    }
                }
            }
        }
    }
}

@Composable
fun RideTimerSheet(isVisible: Boolean,
                   time : Long,
                   onClose: () -> Unit) {
    if (isVisible) {
        var timeLeftInMillis = time
        val timerFlow = flow {
            while (timeLeftInMillis > 0){
                delay(1000)
                Log.d("TIME_X",timeLeftInMillis.toString())
                timeLeftInMillis--
                emit(timeLeftInMillis)
            }
        }

        val timeLeft by timerFlow.collectAsStateWithLifecycle(time)

        BackHandler(true){
            onClose()
        }
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {

            Card(
                shape = RoundedCornerShape(
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp,
                    topStart = 24.dp,
                    topEnd = 24.dp
                ),
                elevation = CardDefaults.cardElevation(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            ) {
                ConstraintLayout(
                    Modifier
                        .fillMaxWidth()
                        .clip(
                            RoundedCornerShape(
                                bottomStart = 0.dp,
                                bottomEnd = 0.dp,
                                topStart = 24.dp,
                                topEnd = 24.dp
                            )
                        )
                        .background(Color.White)
                ) {
                    ConstraintLayout(Modifier.fillMaxWidth()) {

                        val (dhRef, tvRef, lineRef, cRef) = createRefs()

                        HorizontalDivider(
                            thickness = 2.dp,
                            modifier = Modifier
                                .width(48.dp)
                                .padding(top = 16.dp)
                                .constrainAs(dhRef) {
                                    bottom.linkTo(tvRef.top, margin = 16.dp)
                                    centerHorizontallyTo(parent)
                                },
                            color = Color.Gray
                        )

                        Text(
                            "You arrive in 0:$timeLeft minutes",
                            style = TextStyle(
                                fontFamily = poppins,
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp,
                                letterSpacing = 0.2.sp,
                                lineHeight = 20.sp
                            ),
                            modifier = Modifier.constrainAs(tvRef) {
                                bottom.linkTo(lineRef.top, margin = 20.dp)
                                start.linkTo(parent.start, margin = 16.dp)
                            })

                        HorizontalDivider(
                            thickness = 0.6.dp,
                            color = Color.Gray,
                            modifier = Modifier
                                .constrainAs(lineRef) {
                                    bottom.linkTo(cRef.top, margin = 0.dp)
                                    start.linkTo(parent.start, margin = 0.dp)
                                    end.linkTo(parent.end, margin = 0.dp)
                                    width = Dimension.fillToConstraints
                                }
                        )

                        RideOptionCard(
                            modifier = Modifier.constrainAs(cRef) {
                                bottom.linkTo(parent.bottom, margin = 60.dp)
                                start.linkTo(parent.start, margin = 0.dp)
                                end.linkTo(parent.end, margin = 0.dp)
                                width = Dimension.fillToConstraints
                            }
                        )

                    }
                }
            }
        }
    }

}

@Composable
fun RideOptionCard(modifier: Modifier = Modifier, onRideOptionClick: () -> Unit = {}) {
    Card(
        onClick = onRideOptionClick,
        modifier.fillMaxWidth(),
        shape = RectangleShape,
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        ConstraintLayout(Modifier.fillMaxWidth()) {
            val (imgRef, nameRef, vehicleRef, starRef, disRef, colorRef, tvRef, costRef, divRef, lineRef) = createRefs()

            AsyncImage(
                contentScale = ContentScale.Crop,
                model = "https://wallpapers-clan.com/wp-content/uploads/2024/07/naruto-uzumaki-epic-background-desktop-wallpaper-preview.jpg",
                contentDescription = "Driver Img",
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .constrainAs(imgRef) {
                        top.linkTo(parent.top, margin = 20.dp)
                        start.linkTo(parent.start, margin = 20.dp)
                    }
            )

            Text(
                "Gabriel D.",
                style = TextStyle(
                    fontFamily = poppins,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    lineHeight = 24.sp
                ),
                modifier = Modifier
                    .constrainAs(nameRef) {
                        top.linkTo(parent.top, margin = 20.dp)
                        start.linkTo(imgRef.end, margin = 20.dp)
                    }
            )

            Text(
                "Honda Civic",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier
                    .constrainAs(vehicleRef) {
                        top.linkTo(nameRef.bottom, margin = 8.dp)
                        start.linkTo(imgRef.end, margin = 20.dp)
                    }
            )

            Text(
                "Red",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier
                    .constrainAs(colorRef) {
                        top.linkTo(vehicleRef.bottom, margin = 8.dp)
                        start.linkTo(imgRef.end, margin = 20.dp)
                    }
            )

            VerticalDivider(
                thickness = 0.1.dp,
                color = Color.White,
                modifier = Modifier
                    .height(1.dp)
                    .constrainAs(divRef) {
                        top.linkTo(nameRef.top)
                        centerHorizontallyTo(parent)
                    }
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
                    .constrainAs(starRef) {
                        top.linkTo(nameRef.top)
                        bottom.linkTo(nameRef.bottom)
                        start.linkTo(divRef.end, margin = 8.dp)
                        height = Dimension.fillToConstraints
                    }

            ) {
                Icon(
                    painter = painterResource(D.drawable.full_star_icon),
                    contentDescription = "Driver Stars",
                    tint = primaryColor,
                    modifier = Modifier.padding(vertical = 4.dp)
                )

                Text(
                    "4.8",
                    style = typography.labelLarge
                )

            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
                    .constrainAs(disRef) {
                        top.linkTo(vehicleRef.top)
                        bottom.linkTo(vehicleRef.bottom)
                        start.linkTo(divRef.end, margin = 8.dp)
                        height = Dimension.fillToConstraints
                    }
            ) {
                Icon(
                    painter = painterResource(D.drawable.pin_blue_icon),
                    contentDescription = "Driver Stars",
                    tint = Color.Gray
                )

                Text(
                    "4 min",
                    style = typography.labelLarge
                )

            }

            Text(
                "Estimate",
                style = TextStyle(
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    fontFamily = poppins,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    letterSpacing = 0.2.sp,
                    lineHeight = 20.sp,
                ),
                modifier = Modifier
                    .constrainAs(tvRef) {
                        top.linkTo(nameRef.top)
                        end.linkTo(parent.end, margin = 16.dp)
                    }
            )

            Text(
                "$8-10",
                style = TextStyle(
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontFamily = poppins,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    letterSpacing = 0.2.sp,
                    lineHeight = 20.sp,
                ),
                modifier = Modifier
                    .constrainAs(costRef) {
                        top.linkTo(tvRef.bottom, margin = 4.dp)
                        end.linkTo(tvRef.end)
                    }
            )

            HorizontalDivider(
                thickness = 0.6.dp,
                color = Color.Gray,
                modifier = Modifier
                    .constrainAs(lineRef) {
                        top.linkTo(colorRef.bottom, margin = 12.dp)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start, margin = 0.dp)
                        end.linkTo(parent.end, margin = 0.dp)
                        width = Dimension.fillToConstraints
                    }
            )

        }
    }
}

@Composable
fun RideOptions(
    modifier: Modifier = Modifier,
    isVisible: Boolean,
    onRideOptionClick: () -> Unit = {}
) {
    if (isVisible) {

        Column(
            modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {

            Card(
                shape = RoundedCornerShape(
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp,
                    topStart = 24.dp,
                    topEnd = 24.dp
                ),
                elevation = CardDefaults.cardElevation(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            ) {
                ConstraintLayout(
                    Modifier
                        .fillMaxWidth()
                        .clip(
                            RoundedCornerShape(
                                bottomStart = 0.dp,
                                bottomEnd = 0.dp,
                                topStart = 24.dp,
                                topEnd = 24.dp
                            )
                        )
                        .background(Color.White)
                ) {
                    ConstraintLayout(Modifier.fillMaxWidth()) {

                        val (rowLeftRef, rowEndRef, navRef, lineRef, cardsRef, divRef) = createRefs()

                        HorizontalDivider(
                            thickness = 2.dp,
                            modifier = Modifier
                                .width(48.dp)
                                .padding(top = 16.dp)
                                .constrainAs(lineRef) {
                                    bottom.linkTo(navRef.top, margin = 4.dp)
                                    centerHorizontallyTo(parent)
                                },
                            color = Color.Gray
                        )

                        val itemDrawables = arrayOf(
                            D.drawable.car_icon,
                            D.drawable.minivan_icon,
                            D.drawable.motorcycle_icon
                        )

                        var selectedIndex by rememberSaveable { mutableStateOf(0) }
                        var numOfSeats = remember {
                            derivedStateOf {
                                when (selectedIndex) {
                                    0 -> 2
                                    1 -> 4
                                    2 -> 1
                                    else -> 0
                                }
                            }
                        }

                        Row(
                            modifier = Modifier.constrainAs(navRef) {
                                bottom.linkTo(rowLeftRef.top, margin = 8.dp)
                                start.linkTo(parent.start, 0.dp)
                                end.linkTo(parent.end, margin = 0.dp)
                                width = Dimension.fillToConstraints
                            }

                        ) {

                            itemDrawables.forEachIndexed { index, drawable ->
                                NavigationBarItem(
                                    selected = selectedIndex == index,
                                    colors = NavigationBarItemDefaults.colors(
                                        indicatorColor = Color.Transparent,
                                        selectedIconColor = primaryColor,
                                        unselectedIconColor = Color.Gray
                                    ),
                                    icon = {
                                        Icon(
                                            painter = painterResource(drawable),
                                            contentDescription = "V2"
                                        )
                                    }, onClick = {
                                        selectedIndex = index
                                    })
                            }
                        }


                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            modifier = Modifier
                                .constrainAs(rowEndRef) {
                                    bottom.linkTo(cardsRef.top, margin = 12.dp)
                                    end.linkTo(parent.end, margin = 16.dp)
                                }

                        ) {

                            Text(
                                "Price down",
                                style = TextStyle(
                                    color = Color.Gray,
                                    fontFamily = poppins,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 14.sp,
                                    lineHeight = 20.sp,
                                    letterSpacing = 0.2.sp
                                ),
                            )
                            Spacer(Modifier.width(8.dp))

                            Icon(
                                painter = painterResource(D.drawable.down_arrow_grey_icon),
                                contentDescription = "Driver Stars",
                                tint = Color.Gray,
                                modifier = Modifier.padding(vertical = 4.dp)
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            modifier = Modifier
                                .constrainAs(rowLeftRef) {
                                    bottom.linkTo(cardsRef.top, margin = 12.dp)
                                    start.linkTo(parent.start, margin = 16.dp)
                                }

                        ) {
                            Icon(
                                painter = painterResource(D.drawable.person_icon),
                                contentDescription = "Seats",
                                tint = Color.Black,
                                modifier = Modifier.padding(vertical = 4.dp)
                            )

                            Text(
                                "${numOfSeats.value} Seats",
                                style = TextStyle(
                                    fontFamily = poppins,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                    lineHeight = 20.sp,
                                    letterSpacing = 0.2.sp
                                ),
                            )

                            Spacer(Modifier.width(8.dp))

                            Icon(
                                painter = painterResource(R.drawable.arrow_fwd),
                                contentDescription = "Driver Stars",
                                tint = Color.Gray,
                                modifier = Modifier.padding(vertical = 4.dp)
                            )
                        }

                        LazyColumn(

                            modifier = Modifier
                                .heightIn(max = 480.dp)
                                .constrainAs(cardsRef) {
                                    bottom.linkTo(parent.bottom)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                    width = Dimension.fillToConstraints
                                }
                        ) {

                            item {
                                HorizontalDivider(
                                    thickness = 0.6.dp,
                                    color = Color.Gray,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                )
                            }

                            items(8) {
                                RideOptionCard(onRideOptionClick = onRideOptionClick)
                            }

                            item { Spacer(Modifier.height(2.dp)) }
                        }
                    }
                }

            }
        }

    }
}
