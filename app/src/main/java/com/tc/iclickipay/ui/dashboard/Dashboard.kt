package com.tc.iclickipay.ui.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tc.eat.presentation.navigation.EatNavigation
import com.tc.iclickipay.R


@Composable
fun Dashboard(
    modifier: Modifier = Modifier,
    navController: NavController
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(2.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(iconItems) { item ->
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .aspectRatio(1f)
                    .clickable {
                        if (item.id.equals("screen_eat"))
                            navController.navigate(EatNavigation)
                        else
                            navController.navigate(item.id)
                    }
            ) {
                Image(
                    painter = painterResource(item.drawableResId),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .size(120.dp),
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}


data class IconItem(
    val id: String,
    val drawableResId: Int
)

val iconItems = listOf(
    IconItem("screen_uber", R.drawable.transport),
    IconItem("screen_bank", R.drawable.bank),
    IconItem("screen_tinder", R.drawable.tinder),
    IconItem("screen_chat", R.drawable.chat),
    IconItem("screen_eat", R.drawable.restaurant),
    IconItem("screen_hotel", R.drawable.hotel),
    IconItem("screen_doctor", R.drawable.doctor),
    IconItem("screen_pet", R.drawable.pet),
    IconItem("screen_mechanic", R.drawable.mechanic),
    IconItem("screen_pc_repair", R.drawable.pc_repair),
    IconItem("screen_learn", R.drawable.learn),
    IconItem("screen_handy_man", R.drawable.handyman),
    IconItem("screen_laundry", R.drawable.laundry),
    IconItem("screen_delivery", R.drawable.delivery),
    IconItem("screen_babysitter", R.drawable.babysitter),
    IconItem("screen_house_clean", R.drawable.house_clean)
)