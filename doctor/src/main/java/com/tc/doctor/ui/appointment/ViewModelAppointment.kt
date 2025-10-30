package com.tc.doctor.ui.appointment

import com.tc.doctor.domain.model.Specialist
import com.tc.doctor.domain.usecase.GetSpecialistsUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tc.doctor.domain.usecase.GetPersonInfosUseCase
import com.tc.tcmap.domain.PersonInfo

class ViewModelAppointment(
    getSpecialists: GetSpecialistsUseCase,
    getPersonInfos: GetPersonInfosUseCase
): ViewModel() {

    // -------------- SearchDoctorScreen -- AND -- FiltersScreen ----------------------
    // TODO: remember why we are using StateFlow here
    // What other options do we have and what would be better?
    val specialists: StateFlow<List<Specialist>> = getSpecialists()
        // TODO: what is stateIn, when should it be used, other options, pros/cons
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
            initialValue = emptyList()
        )

    // need to filter specialists by filters City, Distance, Specialties, Language


    // -------------------------- DoctorMapScreen -------------------------------
    val personInfos: StateFlow<List<PersonInfo>> = getPersonInfos()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
            initialValue = emptyList()
        )



    // -------------------------------- Calendar ---------------------------------
    // LocalDate and LocalTime -> should we save these?
        // i think we just need to create an appointment

}