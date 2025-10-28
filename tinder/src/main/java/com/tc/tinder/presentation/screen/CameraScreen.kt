package com.tc.tinder.presentation.screen
import android.Manifest
import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.Camera
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.tc.tinder.R
import com.tc.tinder.presentation.ui.topbar.CameraTopAppBar
import com.tc.tinder.domain.util.Camera as CameraPermissionHelper
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun CameraScreen(
    onBackClick: () -> Unit = {},
    onGalleryClick: () -> Unit = {},
    onHelpClick: () -> Unit = {},
    onPhotoCaptured: (Uri) -> Unit = {}
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val isPreview = LocalInspectionMode.current

    // 🧩 PREVIEW MODE SIMULATION
    if (isPreview) {
        val fakeUri = Uri.parse("android.resource://com.tc.tinder/" + R.drawable.add_profile_picture_image)
        LaunchedEffect(Unit) {
            // Simulate photo taken in preview mode
            onPhotoCaptured(fakeUri)
        }
        Box(
            Modifier
                .fillMaxSize()
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("📸 Preview Mode — Pretending photo captured.")
        }
        return
    }

    // 🎥 Runtime Camera Flow
    val permissionHelper = remember(context) { CameraPermissionHelper(context) }
    var hasPermissions by remember { mutableStateOf(permissionHelper.hasRequiredPermission()) }
    val permissionsLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { result -> hasPermissions = result.values.all { it } }

    val previewView = remember { PreviewView(context) }
    val imageCapture = remember { ImageCapture.Builder().build() }
    var boundCamera by remember { mutableStateOf<Camera?>(null) }

    var useFrontCamera by remember { mutableStateOf(true) }
    var flashOn by remember { mutableStateOf(false) }

    val cameraSelector =
        if (useFrontCamera) CameraSelector.DEFAULT_FRONT_CAMERA else CameraSelector.DEFAULT_BACK_CAMERA
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }

    // Bind camera lifecycle
    LaunchedEffect(hasPermissions, cameraSelector) {
        if (!hasPermissions) return@LaunchedEffect
        cameraProviderFuture.addListener({
            runCatching {
                val provider = cameraProviderFuture.get()
                val preview = Preview.Builder().build().also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }
                provider.unbindAll()
                boundCamera = provider.bindToLifecycle(
                    lifecycleOwner, cameraSelector, preview, imageCapture
                )
                boundCamera?.cameraControl?.enableTorch(flashOn && !useFrontCamera)
            }.onFailure { e -> Log.e("CameraScreen", "Bind failed", e) }
        }, ContextCompat.getMainExecutor(context))
    }

    // Sync flash
    LaunchedEffect(flashOn) {
        imageCapture.flashMode =
            if (flashOn) ImageCapture.FLASH_MODE_ON else ImageCapture.FLASH_MODE_OFF
        boundCamera?.cameraControl?.enableTorch(flashOn && !useFrontCamera)
    }

    Scaffold(
        topBar = { CameraTopAppBar(onBackClick, onGalleryClick, onHelpClick) },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        if (!hasPermissions) {
            Box(Modifier.fillMaxSize().padding(innerPadding), contentAlignment = Alignment.Center) {
                Button(onClick = {
                    permissionsLauncher.launch(
                        arrayOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO)
                    )
                }) { Text("Grant Camera Permissions") }
            }
            return@Scaffold
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Camera preview
            Box(
                Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                AndroidView(factory = { previewView }, modifier = Modifier.matchParentSize())

                Row(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Switch camera
                    IconButton(
                        onClick = { useFrontCamera = !useFrontCamera },
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.75f)
                        )
                    ) {
                        Icon(
                            painterResource(com.tc.design.R.drawable.arrow_white_right_two),
                            contentDescription = "Switch camera"
                        )
                    }
                    // Flash toggle
                    IconButton(
                        onClick = { flashOn = !flashOn },
                        enabled = !useFrontCamera,
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.75f)
                        )
                    ) {
                        Icon(
                            if (flashOn) painterResource(R.drawable.flash)
                            else painterResource(com.tc.design.R.drawable.lighting_white_icon),
                            contentDescription = if (flashOn) "Flash on" else "Flash off"
                        )
                    }
                }
            }

            // Bottom panel
            Surface(
                tonalElevation = 4.dp,
                shadowElevation = 8.dp,
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(0.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 16.dp)
                ) {
                    Button(
                        onClick = {
                            capturePhoto(
                                appContext = context.applicationContext,
                                imageCapture = imageCapture,
                                onSaved = onPhotoCaptured,
                                onError = { e -> Log.e("CameraScreen", "Capture failed", e) }
                            )
                        },
                        modifier = Modifier
                            .align(Alignment.Center)
                            .height(52.dp)
                            .fillMaxWidth(0.6f),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(painterResource(com.tc.design.R.drawable.camera_icon), contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("Take a photo")
                    }
                }
            }
        }
    }
}

private fun capturePhoto(
    appContext: Context,
    imageCapture: ImageCapture,
    onSaved: (Uri) -> Unit,
    onError: (Throwable) -> Unit
) {
    val name = "IMG_" + SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US)
        .format(System.currentTimeMillis())
    val file = File(appContext.cacheDir, "$name.jpg")
    val output = ImageCapture.OutputFileOptions.Builder(file).build()
    val executor = ContextCompat.getMainExecutor(appContext)

    imageCapture.takePicture(
        output,
        executor,
        object : ImageCapture.OnImageSavedCallback {
            override fun onError(exc: ImageCaptureException) = onError(exc)
            override fun onImageSaved(res: ImageCapture.OutputFileResults) {
                onSaved(res.savedUri ?: file.toUri())
            }
        }
    )
}



@androidx.compose.ui.tooling.preview.Preview
@Composable
private fun CameraScreenPreview() {
    // Preview won't show a real camera feed, but this compiles/renders
    CameraScreen()
}