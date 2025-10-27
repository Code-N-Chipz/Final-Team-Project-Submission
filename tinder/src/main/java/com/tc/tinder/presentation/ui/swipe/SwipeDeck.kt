package com.tc.tinder.presentation.ui.swipe

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.tc.tinder.R
import com.tc.tinder.domain.model.userdetails.User

import com.tc.tinder.presentation.ui.card.UserCard
import com.tc.tinder.presentation.ui.widget.RoundIcon
import kotlinx.coroutines.launch

// UI-only enum for overlay tint while dragging


// in com.tc.tinder.presentation.ui.swipe.SwipeDeck (or wherever you keep it)

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.draw.clipToBounds
import kotlin.math.abs
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme

private enum class Decision { Neutral, Like, Nope, SuperLike }

/**
 * Shows the current card, a peeking next card, and action buttons.
 * Callbacks are **no-arg** because your ViewModel already knows which user is current.
 */

private enum class VisualDecision { Neutral, Like, Nope, SuperLike }

@Suppress("CyclomaticComplexMethod")
@Composable
fun SwipeDeck(
    currentUser: User,
    nextUser: User?,                 // nullable → enables the “peek”
    modifier: Modifier = Modifier,
    onRewind: () -> Unit = {},
    onNope: () -> Unit = {},
    onBoost: () -> Unit = {},
    onLike: () -> Unit = {},
    onSuperLike: () -> Unit = {}
) {
    val scope = rememberCoroutineScope()
    val density = LocalDensity.current

    // layout constants
    val buttonsHeight = 72.dp
    val overlap = 16.dp

    BoxWithConstraints(
        modifier = modifier.fillMaxSize()
    ) {
        // capture constraints to avoid implicit-receiver issues
        val containerMaxWidth = maxWidth
        val containerMaxHeight = maxHeight

        val widthPx = with(density) { containerMaxWidth.toPx() }
        val swipeThreshold = widthPx * 0.35f

        // visible area so buttons overlap the card a bit
        val initialVisibleHeight = containerMaxHeight - (buttonsHeight - overlap)

        val offsetX = remember { Animatable(0f) }
        val rotation by remember { derivedStateOf { (offsetX.value / widthPx) * 8f } }

        // drag progress (0..1) to drive “peek” scale/translation
        val dragProgress by remember {
            derivedStateOf { (abs(offsetX.value) / swipeThreshold).coerceIn(0f, 1f) }
        }

        var decision by remember { mutableStateOf(Decision.Neutral) }

        // 🔥 Animate the CARD CONTAINER color (no overlay)
        val cardColor by animateColorAsState(
            targetValue = when (decision) {
                Decision.Like      -> Color(0x3322C55E)   // subtle green (≈20% alpha)
                Decision.Nope      -> Color(0x33EF4444)   // subtle red
                Decision.SuperLike -> Color(0x33F59E0B)   // subtle gold/amber
                Decision.Neutral   -> MaterialTheme.colorScheme.surface
            },
            animationSpec = tween(durationMillis = 240),
            label = "cardContainerColor"
        )

        fun resetCenter() {
            decision = Decision.Neutral
            scope.launch {
                offsetX.animateTo(0f, spring(stiffness = Spring.StiffnessMedium))
            }
        }

        fun animateOut(toRight: Boolean, after: () -> Unit) {
            scope.launch {
                offsetX.animateTo(
                    targetValue = if (toRight) widthPx else -widthPx,
                    animationSpec = tween(durationMillis = 420, easing = FastOutSlowInEasing)
                )
                after()
                offsetX.snapTo(0f)
                decision = Decision.Neutral
            }
        }

        // ---------- NEXT CARD (peek, sits behind current) ----------
        if (nextUser != null) {
            val baseScale = 0.95f
            val liftedScale = baseScale + 0.05f * dragProgress
            val liftedTranslateYPx = with(density) { (20.dp * (1f - dragProgress)).toPx() }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp)
                    .graphicsLayer {
                        scaleX = liftedScale
                        scaleY = liftedScale
                        translationY = liftedTranslateYPx
                    }
                    .clipToBounds()
            ) {
                UserCard(
                    user = nextUser,
                    initialVisibleHeight = initialVisibleHeight,
                    containerColor = MaterialTheme.colorScheme.surface // next stays neutral
                )
            }
        }

        // ---------- CURRENT CARD ----------
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp)
                .graphicsLayer {
                    translationX = offsetX.value
                    rotationZ = rotation
                }
                .pointerInput(currentUser.id) {
                    detectHorizontalDragGestures(
                        onHorizontalDrag = { _, dx ->
                            val next = offsetX.value + dx
                            scope.launch { offsetX.snapTo(next) }
                            decision = when {
                                next >  swipeThreshold * 0.5f -> Decision.Like
                                next < -swipeThreshold * 0.5f -> Decision.Nope
                                else                          -> Decision.Neutral
                            }
                        },
                        onDragEnd = {
                            when {
                                offsetX.value >  swipeThreshold -> {
                                    decision = Decision.Like
                                    animateOut(toRight = true, after = onLike)
                                }
                                offsetX.value < -swipeThreshold -> {
                                    decision = Decision.Nope
                                    animateOut(toRight = false, after = onNope)
                                }
                                else -> resetCenter()
                            }
                        }
                    )
                }
                .zIndex(2f)
        ) {
            UserCard(
                user = currentUser,
                initialVisibleHeight = initialVisibleHeight,
                containerColor = cardColor // 👈 CHANGES THE CARD’S BG
            )

            // big decision icon in center (optional)
            CenterDecisionIcon(decision)
        }

        // ---------- BOTTOM BUTTONS (overlap the card) ----------
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp)
                .height(buttonsHeight)
                .fillMaxWidth()
                .zIndex(3f),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RoundIcon(onClick = onRewind, icon = R.drawable.player_fast_back_icon)

            RoundIcon(onClick = {
                decision = Decision.Nope
                animateOut(toRight = false, after = onNope)
            }, icon = R.drawable.cancel_icons)

            RoundIcon(onClick = onBoost, icon = R.drawable.flame_purple_icon)

            RoundIcon(onClick = {
                decision = Decision.Like
                animateOut(toRight = true, after = onLike)
            }, icon = R.drawable.heart_light_green_icon)

            RoundIcon(onClick = {
                decision = Decision.SuperLike
                animateOut(toRight = true, after = onSuperLike)
            }, icon = R.drawable.star_icon)
        }
    }
}

@Composable
private fun CenterDecisionIcon(decision: Decision) {
    val (iconRes, bg, fg) = when (decision) {
        Decision.Like -> Triple(R.drawable.heart_light_green_icon, Color(0x2222C55E), Color(0xFF22C55E))
        Decision.Nope -> Triple(R.drawable.cancel_icons, Color(0x22EF4444), Color(0xFFEF4444))
        Decision.SuperLike -> Triple(R.drawable.star_icon, Color(0x22F59E0B), Color(0xFFF59E0B))
        Decision.Neutral -> Triple(null, Color.Transparent, Color.Transparent)
    }

    AnimatedVisibility(
        visible = iconRes != null,
        enter = fadeIn(tween(120)),
        exit = fadeOut(tween(120)),
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 100.dp) // keep it above buttons a bit
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            if (iconRes != null) {
                // subtle pill behind the icon
                Box(
                    Modifier
                        .size(96.dp)
                        .background(bg, shape = RoundedCornerShape(48.dp))
                )
                Icon(
                    painter = painterResource(iconRes),
                    contentDescription = null,
                    tint = Color.Unspecified, // keep original asset colors
                    modifier = Modifier.size(56.dp)
                )
            }
        }
    }
}







