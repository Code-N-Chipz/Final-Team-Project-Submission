package com.tc.tinder.presentation.screen

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.tc.tinder.R
import com.tc.tinder.domain.model.tokens.boostOptions
import com.tc.tinder.domain.model.tokens.likeOptions
import com.tc.tinder.domain.model.tokens.superLikeOptions
import com.tc.tinder.presentation.ui.products.TokenPayment
import com.tc.tinder.presentation.viewmodel.MatchViewModel

import theme.ICLICKIPAYTheme
// -------------------- BOOST --------------------
@Composable
fun BoostPaymentScreen(
    navController: NavController,
    matchViewModel: MatchViewModel
) {
    val context = LocalContext.current
    var selectedId by remember { mutableStateOf<String?>(null) }

    TokenPayment(
        paymentPainter = painterResource(R.drawable.boostpaymentbackground),
        contentDescription = "Boost paywall background",
        paymentTitle = "Boost your profile",
        paymentMessage = "Be the top profile in your area for\n30 minutes to get more matches",
        options = boostOptions,
        selectedId = selectedId,
        onOptionSelected = { selectedId = it.id },
        onNext = {
            val selected = boostOptions.find { it.id == selectedId }
            selected?.let {
                matchViewModel.addBoosts(it.quantity.toInt())
                Toast.makeText(
                    context,
                    "You bought ${it.quantity} Boost${if (it.quantity.toInt() > 1) "s" else ""} for ${it.price}",
                    Toast.LENGTH_SHORT
                ).show()
            }
            navController.popBackStack("match", inclusive = false)
        },
        onNoThanks = { navController.popBackStack("match", inclusive = false) }
    )
}


// -------------------- LIKE --------------------
@Composable
fun LikePaymentScreen(
    navController: NavController,
    matchViewModel: MatchViewModel
) {
    val context = LocalContext.current
    var selectedId by remember { mutableStateOf<String?>(null) }

    TokenPayment(
        paymentPainter = painterResource(R.drawable.liketpaymentbackground),
        contentDescription = "Likes paywall background",
        paymentTitle = "Like a lot!",
        paymentMessage = "If it's mutual,\nyou can talk together",
        options = likeOptions,
        selectedId = selectedId,
        onOptionSelected = { selectedId = it.id },
        onNext = {
            val selected = likeOptions.find { it.id == selectedId }
            selected?.let {
                matchViewModel.addLikes(it.quantity.toInt())
                Toast.makeText(
                    context,
                    "You bought ${it.quantity} Like${if (it.quantity.toInt() > 1) "s" else ""} for ${it.price}",
                    Toast.LENGTH_SHORT
                ).show()
            }
            navController.popBackStack("match", inclusive = false)
        },
        onNoThanks = { navController.popBackStack("match", inclusive = false) }
    )
}

// -------------------- SUPERLIKE --------------------
@Composable
fun SuperLikePaymentScreen(
    navController: NavController,
    matchViewModel: MatchViewModel
) {
    val context = LocalContext.current
    var selectedId by remember { mutableStateOf<String?>(null) }

    TokenPayment(
        paymentPainter = painterResource(R.drawable.superlikepaymentbackground),
        contentDescription = "Super Like paywall background",
        paymentTitle = "Superlike me!",
        paymentMessage = "Indicate visually\nthat you are interested",
        options = superLikeOptions,
        selectedId = selectedId,
        onOptionSelected = { selectedId = it.id },
        onNext = {
            val selected = superLikeOptions.find { it.id == selectedId }
            selected?.let {
                matchViewModel.addSuperLikes(it.quantity.toInt())
                Toast.makeText(
                    context,
                    "You bought ${it.quantity} Super Like${if (it.quantity.toInt() > 1) "s" else ""} for ${it.price}",
                    Toast.LENGTH_SHORT
                ).show()
            }
            navController.popBackStack("match", inclusive = false)
        },
        onNoThanks = { navController.popBackStack("match", inclusive = false) }
    )
}

@Preview()
@Composable
fun PaymentPreview() {

    ICLICKIPAYTheme {





    }
}




