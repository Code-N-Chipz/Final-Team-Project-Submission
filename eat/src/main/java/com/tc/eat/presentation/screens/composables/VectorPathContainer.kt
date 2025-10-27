package com.tc.eat.presentation.screens.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.unit.dp

@Composable
fun VectorPathContainer(){
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .offset(y = (-88).dp)
    ) {
        val width = size.width
        val height = size.height

        val path = Path().apply {
            // 1. Start at the top-left corner
            moveTo(0f, 0f)

            // 2. Line to top-right corner
            lineTo(width, 0f)

            // 3. Line to bottom-right corner (end point for the main body)
            lineTo(width, height)

            // 4. Single Quadratic Bezier curve for the bottom arc
            // Control Point: Centered horizontally, pushed down to create the inverted arc
            val controlPointX = width / 2f
            val controlPointY = height + (height * 0.2f) // Pushes the curve 20% below the height

            // Curve from (width, height) to (0f, height)
            quadraticTo(
                x1 = controlPointX,
                y1 = controlPointY,
                x2 = 0f,
                y2 = height
            )

            // 5. Close the path (connects back to 0,0, completing the shape)
            close()
        }
        // --- Draw the Smooth Shadow (Multiple Layers) ---
        val shadowLayers = 5 // Number of layers for the shadow
        val initialShadowAlpha = 0.1f // Starting opacity for the deepest shadow
        val shadowOffsetStepX = 0.5.dp.toPx() // Horizontal offset per layer
        val shadowOffsetStepY = 1.5.dp.toPx()   // Vertical offset per layer

        // Draw multiple shadow layers, starting from the furthest/most transparent
        for (i in shadowLayers downTo 1) {
            val currentAlpha = initialShadowAlpha / shadowLayers * i
            val currentOffsetX = shadowOffsetStepX * i
            val currentOffsetY = shadowOffsetStepY * i

            translate(left = currentOffsetX, top = currentOffsetY) {
                drawPath(
                    path = path,
                    color = Color.Black.copy(alpha = currentAlpha),
                )
            }
        }
        drawPath(
            path = path,
            color = Color(0xFFFFA500), // Orange color
        )

        // Draw a black stroke to match the image's border
        drawPath(
            path = path,
            color = Color.Black,
            style = Stroke(width = 2.dp.toPx())
        )
    }
}