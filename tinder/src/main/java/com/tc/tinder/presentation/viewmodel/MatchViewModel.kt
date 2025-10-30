package com.tc.tinder.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tc.tinder.domain.model.userdetails.User
import com.tc.tinder.domain.model.userdetails.currentUser
import com.tc.tinder.domain.usecase.GetCandidatesUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MatchViewModel(
    private val getCandidates: GetCandidatesUseCase
) : ViewModel() {

    private data class Swiped(val user: User, val decision: Decision)
    private enum class Decision { Like, Nope, SuperLike }

    private val history = ArrayDeque<Swiped>()

    private val _state = MutableStateFlow(
        MatchUiState(
            users = emptyList(),
            index = 0,
            likesLeft = currentUser.totalLikes,
            superLikesLeft = currentUser.totalSuperLikes,
            boostsLeft = currentUser.totalBoosts,
            isExhausted = false
        )
    )
    val state: StateFlow<MatchUiState> = _state.asStateFlow()

    private val _events = MutableSharedFlow<MatchEvent>(extraBufferCapacity = 1)
    val events: MutableSharedFlow<MatchEvent> = _events

    init {
        viewModelScope.launch {
            getCandidates().collect { list ->
                _state.update { s ->
                    val clamped = s.index.coerceIn(0, (list.size - 1).coerceAtLeast(0))
                    s.copy(
                        users = list,
                        index = clamped,
                        isExhausted = list.isEmpty() || clamped >= list.size
                    )
                }
                if (_state.value.isExhausted) _events.tryEmit(MatchEvent.Exhausted)
            }
        }
    }


    fun addLikes(count: Int) {
        _state.update { it.copy(likesLeft = it.likesLeft + count) }
    }

    fun addSuperLikes(count: Int) {
        _state.update { it.copy(superLikesLeft = it.superLikesLeft + count) }
    }

    fun addBoosts(count: Int) {
        _state.update { it.copy(boostsLeft = it.boostsLeft + count) }
    }

    fun onLike() {
        val s = _state.value
        val user = s.current ?: return
        if (s.likesLeft <= 0) {
            _state.update { it.copy(paywall = PaywallType.LIKE) }  // show dialog
            return
        }
        history.addLast(Swiped(user, Decision.Like))
        _state.update { it.copy(likesLeft = it.likesLeft - 1) }
        advance()
    }

    fun onNope() {
        val u = _state.value.current ?: return
        history.addLast(Swiped(u, Decision.Nope))
        advance()
    }


    fun onSuperLike() {
        val s = _state.value
        val user = s.current ?: return
        if (s.superLikesLeft <= 0) {
            _state.update { it.copy(paywall = PaywallType.SUPER_LIKE) }
            return
        }
        history.addLast(Swiped(user, Decision.SuperLike))
        _state.update { it.copy(superLikesLeft = it.superLikesLeft - 1) }
        advance()
    }

    fun onBoost() {
        val s = _state.value
        if (s.boostsLeft <= 0) {
            _state.update { it.copy(paywall = PaywallType.BOOST) }
            return
        }
        _state.update { it.copy(boostsLeft = it.boostsLeft - 1) }
        // do boost logic…
    }


    fun dismissPaywall() {
        _state.update { it.copy(paywall = null) }
    }

    fun onRewind() {
        if (history.isEmpty()) {
            _events.tryEmit(MatchEvent.ShowMessage("Nothing to rewind"))
            return
        }
        history.removeLast()
        _state.update { st ->
            val newIndex = (st.index - 1).coerceAtLeast(0)
            st.copy(index = newIndex, isExhausted = false)
        }
        // token refunds if you want:
        // when (last.decision) { ... }
    }

    private fun advance() {
        _state.update { st ->
            val next = st.index + 1
            val exhausted = next >= st.users.size
            st.copy(index = next, isExhausted = exhausted)
        }
        if (_state.value.isExhausted) _events.tryEmit(MatchEvent.Exhausted)
    }
}
