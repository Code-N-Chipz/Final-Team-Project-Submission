package com.tc.learn.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tc.tcmap.ui.MapType
import com.tc.tcmap.ui.TcMap
import com.tc.tcmap.domain.MarkerInfo
import com.tc.tcmap.ui.MapType.*

@Composable
fun Map(
    modifier: Modifier = Modifier,
    markers: List<MarkerInfo> = emptyList(),
    onMarkerClick: (MarkerInfo) -> Unit = {},
) {
    TcMap(
        mapType = MapType.SimpleMap,  // basic map type
        markers = markers,
        onMarkerClicked = { marker: MarkerInfo ->
            onMarkerClick(marker)
        }
    )
}
