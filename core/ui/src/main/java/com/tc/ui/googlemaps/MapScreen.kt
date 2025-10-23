package com.tc.ui.googlemaps

import MapViewModel
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.tc.design.googlemaps.EmptyMapPlaceholder

@Composable
fun MapScreen(viewModel: MapViewModel = hiltViewModel()) {
    val state by viewModel.uiState.collectAsState()


    if (state.isLoading) {
        EmptyMapPlaceholder()
        return
    }


    val center = LatLng(state.centerLat ?: 51.5074, state.centerLng ?: -0.1278)
    val cameraPositionState: CameraPositionState = rememberCameraPositionState {
        position = com.google.android.gms.maps.model.CameraPosition.fromLatLngZoom(center, 12f)
    }


    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        state.markers.forEach { m ->
            Marker(
                state = MarkerState(position = LatLng(m.latitude, m.longitude)),
                title = m.title,
                snippet = m.snippet
            )
        }
    }
}