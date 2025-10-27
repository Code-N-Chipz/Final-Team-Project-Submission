package com.tc.tinder.presentation.viewmodel

sealed class MatchEvent {
    data class ShowMessage(val text: String) : MatchEvent()
    object Exhausted : MatchEvent()
}