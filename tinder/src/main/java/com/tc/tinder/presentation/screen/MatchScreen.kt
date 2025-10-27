package com.tc.tinder.presentation.screen
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.tc.tinder.presentation.ui.swipe.SwipeDeck
import com.tc.tinder.presentation.ui.topbar.MatchTopAppBar
import com.tc.tinder.presentation.viewmodel.MatchViewModel
import com.tc.tinder.presentation.viewmodel.PaywallType



@Composable
fun MatchScreen(
    viewModel: MatchViewModel,
    onHomeClick: () -> Unit = {},
    onMessageClick: () -> Unit = {},
    onUserProfileClick: () -> Unit = {},
    // Navigation targets for the paywall primary action:
    onOpenBoostPaywall: () -> Unit = {},
    onOpenLikePaywall: () -> Unit = {},
    onOpenSuperLikePaywall: () -> Unit = {}
) {
    val ui by viewModel.state.collectAsState()

    // derive current/next cards from list + index
    val current = ui.users.getOrNull(ui.index)
    val next = ui.users.getOrNull(ui.index + 1)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MatchTopAppBar(
                onHomeClick = onHomeClick,
                onMessageClick = onMessageClick,
                onUserProfileClick = onUserProfileClick
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            if (current != null && !ui.isExhausted) {
                SwipeDeck(
                    currentUser = current,
                    nextUser = next,
                    onRewind = viewModel::onRewind,
                    onNope = viewModel::onNope,
                    onBoost = viewModel::onBoost,
                    onLike = viewModel::onLike,
                    onSuperLike = viewModel::onSuperLike,
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                // TODO: show your empty/exhausted state
            }

            // ---- Paywall dialog (shows only when VM sets ui.paywall) ----
            ui.paywall?.let { type ->
                SimplePaywallDialog(
                    type = type,
                    onDismiss = { viewModel.dismissPaywall() },
                    onPrimaryClick = {
                        viewModel.dismissPaywall()
                        when (type) {
                            PaywallType.BOOST      -> onOpenBoostPaywall()
                            PaywallType.LIKE       -> onOpenLikePaywall()
                            PaywallType.SUPER_LIKE -> onOpenSuperLikePaywall()
                        }
                    }
                )
            }
        }
    }
}