package com.tc.tinder.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tc.tinder.domain.model.userdetails.User
import com.tc.tinder.domain.usecase.GetCandidatesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class SwipeUiState(
    val users: List<User> = emptyList(),
    val index: Int = 0,
    val current: User? = null,
    val canRewind: Boolean = false,
    val exhausted: Boolean = false
)

class SwipeDeckViewModel(
    private val getAllUsers: GetCandidatesUseCase
) : ViewModel() {

    private val history = ArrayDeque<User>()

    private val _state = MutableStateFlow(SwipeUiState())
    val state: StateFlow<SwipeUiState> = _state.asStateFlow()

    init {
        // Subscribe to repo flow and reset deck when list changes
        viewModelScope.launch {
            getAllUsers().collect { list ->
                history.clear()
                _state.value = SwipeUiState(
                    users = list,
                    index = 0,
                    current = list.getOrNull(0),
                    canRewind = false,
                    exhausted = list.isEmpty()
                )
            }
        }
    }

    /** Proceed to the next card (called after like/nope/superlike finishes animating). */
    fun advance() {
        val s = _state.value
        val cur = s.current ?: return
        history.addLast(cur)

        val nextIdx = s.index + 1
        val next = s.users.getOrNull(nextIdx)

        _state.value = s.copy(
            index = nextIdx,
            current = next,
            canRewind = history.isNotEmpty(),
            exhausted = next == null
        )
    }

    /** Bring back the previous card. */
    fun rewind() {
        val s = _state.value
        if (history.isEmpty() || s.index <= 0) return

        val last = history.removeLast()
        val newIdx = (s.index - 1).coerceAtLeast(0)

        _state.value = s.copy(
            index = newIdx,
            current = last,
            canRewind = history.isNotEmpty(),
            exhausted = false
        )
    }

    // Optional: expose convenience flags for UI buttons
    fun canRewind(): Boolean = _state.value.canRewind
    fun isExhausted(): Boolean = _state.value.exhausted
}