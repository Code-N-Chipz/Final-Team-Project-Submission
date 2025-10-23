package com.tc.data.repositories

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.tc.domain.models.MapMarker
import com.tc.domain.repository.MapRepository
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume

@Singleton
class MapRepositoryImpl @Inject constructor(
    private val fusedLocationClient: FusedLocationProviderClient,
    private val context: Context
) : MapRepository {


    // Simple hard-coded markers example — replace with real data source (network/local DB)
    override suspend fun getSavedMarkers(): List<MapMarker> = listOf(
        MapMarker("1", "Marker A", "Snippet A", 51.5074, -0.1278),
        MapMarker("2", "Marker B", "Snippet B", 51.5155, -0.0922)
    )


    @SuppressLint("MissingPermission")
    override suspend fun getLastKnownLocation(): Pair<Double, Double>? {
        return suspendCancellableCoroutine { cont ->
            fusedLocationClient.lastLocation
                .addOnSuccessListener { loc ->
                    if (loc != null) cont.resume(Pair(loc.latitude, loc.longitude))
                    else cont.resume(null)
                }
                .addOnFailureListener {
                    cont.resume(null)
                }
        }
    }
}