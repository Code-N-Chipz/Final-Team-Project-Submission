package com.tc.tinder.presentation.viewmodel.screen



import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tc.tinder.data.fake.TestUsers
import com.tc.tinder.data.repository.UserFakeRepositoryImpl
import com.tc.tinder.domain.usecase.GetCandidatesUseCase
import com.tc.tinder.presentation.screen.MatchScreen
import com.tc.tinder.presentation.viewmodel.MatchViewModel
import com.tc.tinder.presentation.viewmodel.MatchViewModelFactory

@Composable
fun MatchScreenHost(
    onHomeClick: () -> Unit = {},
    onMessageClick: () -> Unit = {},
    onUserProfileClick: () -> Unit = {},
    onOpenBoostPaywall: () -> Unit = {},
    onOpenLikePaywall: () -> Unit = {},
    onOpenSuperLikePaywall: () -> Unit = {}
) {
    val repo = remember { UserFakeRepositoryImpl(TestUsers.list) }
    val useCase = remember { GetCandidatesUseCase(repo) }
    val vm: MatchViewModel = viewModel(factory = MatchViewModelFactory(useCase))

    MatchScreen(
        viewModel = vm,
        onHomeClick = onHomeClick,
        onMessageClick = onMessageClick,
        onUserProfileClick = onUserProfileClick,
        onOpenBoostPaywall = onOpenBoostPaywall,
        onOpenLikePaywall = onOpenLikePaywall,
        onOpenSuperLikePaywall = onOpenSuperLikePaywall
    )
}
