import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tc.domain.models.MapMarker
import com.tc.domain.usecase.GetLastKnownLocationUseCase
import com.tc.domain.usecase.GetSavedMarkersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MapUiState(
    val markers: List<MapMarker> = emptyList(),
    val centerLat: Double? = null,
    val centerLng: Double? = null,
    val isLoading: Boolean = true
)


@HiltViewModel
class MapViewModel @Inject constructor(
    private val getSavedMarkersUseCase: GetSavedMarkersUseCase,
    private val getLastKnownLocationUseCase: GetLastKnownLocationUseCase
) : ViewModel() {


    private val _uiState = MutableStateFlow(MapUiState())
    val uiState: StateFlow<MapUiState> = _uiState.asStateFlow()


    init {
        load()
    }


    private fun load() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            val markers = getSavedMarkersUseCase()
            val loc = getLastKnownLocationUseCase()
            _uiState.value = MapUiState(
                markers = markers,
                centerLat = loc?.first ?: markers.firstOrNull()?.latitude,
                centerLng = loc?.second ?: markers.firstOrNull()?.longitude,
                isLoading = false
            )
        }
    }
}