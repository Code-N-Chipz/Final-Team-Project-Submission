package com.tc.tinder.domain.util

import android.Manifest
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

import android.content.Context

class Camera(private val context: Context) {

    fun hasRequiredPermission(): Boolean {
        return CAMERAX_PERMISSIONS.all {
            ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
        }
    }

    companion object {
        private val CAMERAX_PERMISSIONS = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO
        )
    }
}
