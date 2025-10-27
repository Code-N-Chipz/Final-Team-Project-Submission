package com.tc.doctor.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

/*
If you run into version mismatch with your Compose BOM, bump navigationCompose in gradle/libs.versions.toml to a recent version (for example: 2.8.4), then sync.
 */

// add this to app MainActivity -> TODO: coordinate changing that file
@Composable
fun DoctorRoot() {
    // Can add
    DoctorNavHost()
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun DoctorRootPreview() {
    DoctorRoot()
}
