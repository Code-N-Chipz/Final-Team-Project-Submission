package com.tc.doctor.ui.appointment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tc.doctor.domain.usecase.GetPersonInfosUseCase
import com.tc.doctor.domain.usecase.GetSpecialistsUseCase

class ViewModelFactoryAppointment(
    private val getSpecialists: GetSpecialistsUseCase,
    private val getPersonInfos: GetPersonInfosUseCase
): ViewModelProvider.Factory {
    // TODO: detekt causing problems -> not sure on different approach than suppress
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelAppointment::class.java)) {
            // TODO: why can't this ever succeed?
            return ViewModelAppointment(getSpecialists, getPersonInfos) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}


/*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tc.tinder.domain.usecase.GetCandidatesUseCase

class MatchViewModelFactory(
    private val getCandidates: GetCandidatesUseCase
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MatchViewModel::class.java)) {
            return MatchViewModel(getCandidates) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
 */