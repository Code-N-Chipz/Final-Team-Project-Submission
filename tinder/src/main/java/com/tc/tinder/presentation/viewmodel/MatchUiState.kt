package com.tc.tinder.presentation.viewmodel

import com.tc.tinder.domain.model.userdetails.User


enum class PaywallType { LIKE, SUPER_LIKE, BOOST }
data class MatchUiState(
    val users: List<User> = emptyList(),
    val index: Int = 0,

    // tokens for the *current user* (how many actions left)
    val likesLeft: Int = 0,
    val superLikesLeft: Int = 0,
    val boostsLeft: Int = 0,
    val isExhausted: Boolean = false,
    val paywall: PaywallType? = null
) {
    val current: User? get() = users.getOrNull(index)
}