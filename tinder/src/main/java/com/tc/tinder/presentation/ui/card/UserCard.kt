package com.tc.tinder.presentation.ui.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.tc.tinder.domain.util.DistanceCalculator
import com.tc.tinder.domain.model.userdetails.User
import com.tc.tinder.domain.model.userdetails.currentUser
import com.tc.tinder.domain.util.formatMilesLabel
import com.tc.tinder.domain.util.userAgeOrNull

import theme.primaryColor

@Composable
fun UserCard(
    user: User,
    initialVisibleHeight: Dp? = null,
    photoInset: Dp = 10.dp,                 // margin so the card frame shows
    photoCornerRadius: Dp = 18.dp,
    containerColor: Color = MaterialTheme.colorScheme.surface
) {
    val age = userAgeOrNull(user.dateOfBirth)

    // distance once per location combo
    val miles = remember(currentUser.location, user.location) {
        DistanceCalculator().miles(currentUser.location, user.location)
    }
    val milesLabel = remember(miles) { formatMilesLabel(miles) }

    // Visible height handed in by SwipeDeck so buttons can overlap a bit
    val targetHeight = initialVisibleHeight ?: 500.dp
    val headerMinHeight = 88.dp
    val photoHeight = (targetHeight - headerMinHeight).coerceAtLeast(140.dp)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp),
        shape = RoundedCornerShape(22.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
        // ✅ use the incoming animated/tinted color
        colors = CardDefaults.cardColors(containerColor = containerColor)
    ) {
        // Constrain all content to a fixed visible area (extra details require scroll)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(targetHeight)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                // ❌ Do NOT paint another background; let the Card color show through
            ) {
                // 1) PHOTO — inset + slightly smaller corner radius so card shows around it
                UserPhotoPager(
                    pictures = user.pictures,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(photoHeight)
                        .padding(all = photoInset)                 // frame around photo
                        .clip(RoundedCornerShape(photoCornerRadius))// inner radius
                )

                // 2) HEADER — always visible without scroll
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = headerMinHeight)
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${user.firstName}, ${age ?: "—"}",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.weight(1f),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "${user.pictures.size}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(Modifier.width(4.dp))
                        Icon(
                            painter = painterResource(id = com.tc.design.R.drawable.image_icon),
                            contentDescription = "Photos",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                // 3) DETAILS — visible only after scrolling
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    if (!user.university.isNullOrBlank()) {
                        Text(
                            text = user.university!!,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(Modifier.height(4.dp))
                    }

                    Text(
                        text = milesLabel,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    user.description?.let {
                        Spacer(Modifier.height(10.dp))
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75f),
                            lineHeight = 20.sp
                        )
                    }

                    Spacer(Modifier.height(24.dp))
                }
            }
        }
    }
}

@Composable
private fun UserPhotoPager(
    pictures: List<String>,
    modifier: Modifier = Modifier,
    pageHeight: Dp = 340.dp
) {
    val safePics = if (pictures.isEmpty()) listOf("") else pictures
    val pagerState = rememberPagerState(pageCount = { safePics.size })

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(pageHeight)
            .clip(RoundedCornerShape(16.dp))
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.matchParentSize()
        ) { page ->
            AsyncImage(
                model = safePics[page],
                contentDescription = "User photo ${page + 1}",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        if (safePics.size > 1) {

            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 12.dp),
            ) {
                PagerDots(
                    total = safePics.size,
                    current = pagerState.currentPage,
                    activeColor = primaryColor,
                    inactiveColor = Color(0xFFE0E3E7)
                )
            }
        }
    }
}


@Composable
private fun PagerDots(
    total: Int,
    current: Int,
    activeColor: Color = primaryColor,
    inactiveColor: Color = Color(0xFFE0E3E7)
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(total) { i ->
            val isActive = i == current
            val size = if (isActive) 8.dp else 6.dp
            val color = if (isActive) activeColor else inactiveColor

            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .size(size)
                    .clip(RoundedCornerShape(50))
                    .background(color)
            )
        }
    }
}
