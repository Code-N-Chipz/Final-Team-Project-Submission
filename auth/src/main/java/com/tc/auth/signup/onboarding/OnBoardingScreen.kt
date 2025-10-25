package com.tc.auth.signup.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tc.auth.R
import kotlinx.coroutines.launch

data class OnboardPage(
    val id: Int,
    val title: String,
    val description: String,
    val imagePainter: Painter,
    val imageDescription: String,
    val illustration: @Composable () -> Unit
)

@Composable
fun OnBoardingPageScreen(){
    OnboardingPager(
        pages = samplePages(),
        initialPage = 0,
        onSkip = {} ,
        onGetStarted = {},
        onSignIn = {}
    )
}

@Composable
fun OnboardingPager(
    pages: List<OnboardPage>,
    modifier: Modifier = Modifier,
    initialPage: Int = 0,
    onSkip: () -> Unit = {},
    onGetStarted: () -> Unit = {},
    onSignIn: () -> Unit = {},
    onPageChanged: (Int) -> Unit = {}
) {
    val pagerState = rememberPagerState (
        initialPage = initialPage,
        pageCount = { pages.size }
        )
    val scope = rememberCoroutineScope ()
    LaunchedEffect (pagerState.currentPage) { onPageChanged(pagerState.currentPage) }

    Column (modifier = modifier.fillMaxSize()) {
        // Top navigation row (Skip / Back)
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (pagerState.currentPage == initialPage) {
                Text(
                    text = "Back",
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .clickable {
                            scope.launch {
                                val prev = (pagerState.currentPage - 1).coerceAtLeast(0)
                                pagerState.animateScrollToPage(prev)
                            }
                        }
                        .padding(8.dp)
                )
            } else {
                Text(
                    text = "Skip",
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .clickable { onSkip() }
                        .padding(8.dp)
                )
            }
        }

        // Pager (content)
        Box(modifier = Modifier.weight(1f)) {
            HorizontalPager (
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                val p = pages[page]
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 24.dp, vertical = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .height(260.dp)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        p.illustration()
                    }

                    Box(
                        modifier = Modifier
                            .height(260.dp)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = p.imagePainter,
                            contentDescription = p.imageDescription,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = p.title,
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = p.description,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        lineHeight = 20.sp
                    )
                }
            }
        }

        // Indicators + actions
        Column(modifier = Modifier.fillMaxWidth()) {
            // Indicators row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                for (i in pages.indices) {
                    val active = i == pagerState.currentPage
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 6.dp)
                            .size(if (active) 18.dp else 8.dp)
                            .background(
                                color = if (active) MaterialTheme.colorScheme.primary else Color.Gray.copy(alpha = 0.4f),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .clickable { scope.launch { pagerState.animateScrollToPage(i) } }
                    )
                }
            }

            // Last page: show Get started + Sign in. Otherwise show "Next" small button.
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                if (pagerState.currentPage == pages.lastIndex) {
                    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                        Button(
                            onClick = onGetStarted,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7A00))
                        ) {
                            Text(text = "Get started", color = Color.White)
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "Sign in",
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .clickable { onSignIn() }
                                .padding(8.dp)
                        )
                    }
                }
                /**else {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = " ",
                            modifier = Modifier.width(72.dp)
                        )

                        Button(
                            onClick = {
                                val next = (pagerState.currentPage + 1).coerceAtMost(pages.lastIndex)
                                scope.launch { pagerState.animateScrollToPage(next) }
                            },
                            modifier = Modifier
                                .height(44.dp)
                                .width(140.dp),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(text = "Next")
                        }
                    }
                }**/
            }
        }
    }
}

// ---------- Sample pages and preview ----------

@Composable
private fun samplePages(): List<OnboardPage> {
//    val isPreview = LocalInspectionMode.current
    return listOf(
        OnboardPage(
            id = 1,
            title = "Bank & transfer",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque ultricies justo.",
            imagePainter = painterResource(R.drawable.bank_transfer),
            imageDescription = "Bank and Transfer",
            illustration = {
                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .background(Color(0xFFEDF2FF), RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Card", color = Color(0xFF1E3A8A))
                }
            }
        ),
        OnboardPage(
            id = 2,
            title = "Messages",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque ultricies justo.",
            imagePainter = painterResource(R.drawable.message),
            imageDescription = "Message",
            illustration = {
                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .background(Color(0xFFE8FFF5), RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Chat", color = Color(0xFF00796B))
                }
            }
        ),
        OnboardPage(
            id = 3,
            title = "Move",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque ultricies justo.",
            imagePainter = painterResource(R.drawable.move),
            imageDescription = "Move",
            illustration = {
                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .background(Color(0xFFFFF4E6), RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Car", color = Color(0xFF8A4B00))
                }
            }
        ),
        OnboardPage(
            id = 4,
            title = "Services",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque ultricies justo.",
            imagePainter = painterResource(R.drawable.services),
            imageDescription = "Services",
            illustration = {
                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .background(Color(0xFFEFF7FF), RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Services", color = Color(0xFF0B63D6))
                }
            }
        )
    )
}

// Helper: attempt to load a painter, return null if resource not present (safe for preview)
@Composable
private fun tryLoadPainterOrNull(resId: Int): Painter? {
//    return try {
       return painterResource(id = resId)
//    } catch (e: Exception) {
//        null
//    }
}

@Preview(showBackground = true, name = "Onboarding Light")
@Composable
fun OnboardingPreviewLight() {
    Surface {
        OnboardingPager(
            pages = samplePages(),
            onSkip = {},
            onGetStarted = {},
            onSignIn = {}
        )
    }
}

//@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES, name = "Onboarding Dark")
//@Composable
//fun OnboardingPreviewDark() {
//    Surface {
//        OnboardingPager(
//            pages = samplePages(),
//            onSkip = {},
//            onGetStarted = {},
//            onSignIn = {}
//        )
//    }
//}