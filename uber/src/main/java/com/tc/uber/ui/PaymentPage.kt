package com.tc.uber.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.carousel.HorizontalCenteredHeroCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.tc.uber.R
import com.tc.uber.ui.components.DefButton
import theme.backgroundColor
import theme.poppins
import com.tc.design.R as D
import theme.primaryColor
import theme.textPrimary
import theme.typography
import kotlin.math.absoluteValue


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showSystemUi = true)
fun PaymentPage(onPay : () -> Unit = {}, onHome :() -> Unit = {}) {
    val tv1 = "Your balance"
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text("Choose your payment", style = typography.headlineMedium)
                },
                navigationIcon = {
                    IconButton(onClick = onHome, modifier = Modifier.padding(start = 4.dp)) {
                        Icon(
                            painter = painterResource(D.drawable.home_icon),
                            contentDescription = "home",
                            tint = primaryColor,
                        )
                    }
                })
        }) { innerPadding ->
        ConstraintLayout(
            Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(backgroundColor)
        ) {

            val (tvRef, balRef, cardsRef, trFeeRef, trAmRef, totAmTvRef, totAmRef,  divRef, tv2Ref, finalBalRef,btnRef) = createRefs()

            Text(
                tv1, style = typography.labelLarge,
                textAlign = TextAlign.Center,
                color = Color.Gray,
                modifier = Modifier.constrainAs(tvRef) {
                    top.linkTo(parent.top, margin = 12.dp)
                    start.linkTo(parent.start, margin = 20.dp)
                })

            Text(
                "$ 5523.26",
                style = TextStyle(
                    fontFamily = poppins,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 32.sp,
                    letterSpacing = 0.5.sp,
                    lineHeight = 24.sp,
                ),
                modifier = Modifier.constrainAs(balRef) {
                    top.linkTo(tvRef.bottom, margin = 4.dp)
                    start.linkTo(parent.start, margin = 20.dp)
                    end.linkTo(parent.end, margin = 20.dp)
                    width = Dimension.fillToConstraints
                }
            )

            val pagerState = rememberPagerState(initialPage = 0) { 10 }
            pagerState.currentPage
            HorizontalPager(
                contentPadding = PaddingValues(start = 48.dp, end = 48.dp, bottom = 24.dp,top = 10.dp),
                state = pagerState,
                pageSize = PageSize.Fixed(240.dp),
                pageSpacing = 20.dp,
                modifier = Modifier
                    .constrainAs(cardsRef) {
                        top.linkTo(balRef.bottom, margin = 4.dp)
                        start.linkTo(parent.start, margin = 0.dp)
                        end.linkTo(parent.end, margin = 0.dp)
                        width = Dimension.fillToConstraints
                    }) { page ->

                val pageOffset =
                    (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
                val scale = 1f - (pageOffset.absoluteValue * 0.1f)

                BankCard(scale)
            }

            Text(
                "Transaction fees", style = typography.labelLarge,
                textAlign = TextAlign.Center,
                color = Color.Gray,
                modifier = Modifier.constrainAs(trFeeRef) {
                    top.linkTo(cardsRef.bottom, margin = 8.dp)
                   centerHorizontallyTo(parent)
                })

            Text(
                "$ 0",
                style = TextStyle(
                    fontFamily = poppins,
                    fontWeight = FontWeight.Medium,
                    fontSize = 24.sp,
                    letterSpacing = 0.1.sp,
                    lineHeight = 24.sp,
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.constrainAs(trAmRef) {
                    top.linkTo(trFeeRef.bottom, margin = 8.dp)
                    start.linkTo(parent.start, margin = 20.dp)
                    end.linkTo(parent.end, margin = 20.dp)
                    width = Dimension.fillToConstraints
                }
            )

            Text(
                "Total amount", style = typography.labelLarge,
                textAlign = TextAlign.Center,
                color = Color.Gray,
                modifier = Modifier.constrainAs(totAmTvRef) {
                    top.linkTo(trAmRef.bottom, margin = 12.dp)
                    centerHorizontallyTo(parent)
                })

            Text(
                "$ 9.50",
                style = TextStyle(
                    color = primaryColor,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Medium,
                    fontSize = 24.sp,
                    letterSpacing = 0.1.sp,
                    lineHeight = 24.sp,
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.constrainAs(totAmRef) {
                    top.linkTo(totAmTvRef.bottom, margin = 8.dp)
                    start.linkTo(parent.start, margin = 20.dp)
                    end.linkTo(parent.end, margin = 20.dp)
                    width = Dimension.fillToConstraints
                }
            )

            HorizontalDivider(
                color = Color(0xFFDADADA),
                modifier = Modifier.constrainAs(divRef) {
                    top.linkTo(totAmRef.bottom, margin = 8.dp)
                    start.linkTo(parent.start, margin = 48.dp)
                    end.linkTo(parent.end, margin = 48.dp)
                    width = Dimension.fillToConstraints
                }
            )

            Text(
                "Your balance after payment", style = typography.labelLarge,
                textAlign = TextAlign.Center,
                color = Color.Gray,
                modifier = Modifier.constrainAs(tv2Ref) {
                    top.linkTo(divRef.bottom, margin = 16.dp)
                    centerHorizontallyTo(parent)
                })

            Text(
                "$ 5508.76",
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontFamily = poppins,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 32.sp,
                    letterSpacing = 0.5.sp,
                    lineHeight = 24.sp,
                ),
                modifier = Modifier.constrainAs(finalBalRef) {
                    top.linkTo(tv2Ref.bottom, margin = 20.dp)
                    start.linkTo(parent.start, margin = 20.dp)
                    end.linkTo(parent.end, margin = 20.dp)
                    width = Dimension.fillToConstraints
                }
            )

            DefButton(modifier = Modifier.constrainAs(btnRef) {
                bottom.linkTo(parent.bottom, margin = 32.dp)
                start.linkTo(parent.start, margin = 48.dp)
                end.linkTo(parent.end, margin = 48.dp)
                width = Dimension.fillToConstraints
            }, btnText = "Pay") {

            }


        }
    }

}

@Composable
fun BankCard(scale : Float){
    Card(
        Modifier
            .fillMaxWidth()
            .dropShadow(
                shape = RoundedCornerShape(20.dp),
                shadow = Shadow(
                    radius = 10.dp,
                    spread = 6.dp,
                    color = Color(0xFFfae1cf),
                    offset = DpOffset(x = 8.dp, 8.dp)
                )
            )
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            },
        colors = CardDefaults.cardColors(containerColor = primaryColor)
    ) {

        ConstraintLayout(Modifier.fillMaxWidth()) {
            val (icRef, nameRef, tvRef, numRef) = createRefs()
            Icon(painter = painterResource(D.drawable.logo_white), contentDescription = "Card logo",
                tint = Color.White,
                modifier = Modifier.constrainAs(icRef){
                    top.linkTo(parent.top, margin = 20.dp)
                    start.linkTo(parent.start,  margin = 20.dp)
                })
            Text("John Doe",
                style = TextStyle(
                    fontFamily = poppins,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    letterSpacing = 0.1.sp,
                    lineHeight = 24.sp,
                    color = textPrimary,
                ),
                modifier = Modifier.constrainAs(nameRef){
                    top.linkTo(parent.top, margin = 22.dp)
                    end.linkTo(parent.end,  margin = 20.dp)
                })

            Text("Account number".uppercase(),
                style = TextStyle(
                    fontFamily = poppins,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    letterSpacing = 0.4.sp,
                    lineHeight = 20.sp,
                    color = textPrimary,
                ),
                modifier = Modifier.constrainAs(tvRef){
                    top.linkTo(icRef.bottom, margin = 24.dp)
                    start.linkTo(parent.start,  margin = 20.dp)
                })

            Text("8014 8014 8014 8014",
                style = TextStyle(
                    fontFamily = poppins,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    lineHeight = 26.sp,
                    letterSpacing = 2.sp,
                    color = textPrimary,
                ),
                modifier = Modifier.constrainAs(numRef){
                    top.linkTo(tvRef.bottom, margin = 6.dp)
                    start.linkTo(parent.start,  margin = 20.dp)
                    end.linkTo(parent.end,  margin = 20.dp)
                    bottom.linkTo(parent.bottom, margin = 32.dp)
                    width = Dimension.fillToConstraints
                })


        }
    }
}