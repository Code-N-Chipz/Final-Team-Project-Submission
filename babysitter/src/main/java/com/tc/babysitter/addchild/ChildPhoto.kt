package com.tc.babysitter.addchild

import android.Manifest
import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.tc.ui.R
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChildPhoto(navController: NavHostController) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val viewModel: ChildViewModel = viewModel()

    var imageCapture: ImageCapture? by remember { mutableStateOf(null) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var photoSelected by remember { mutableStateOf(false) }
    var photoMode by remember { mutableStateOf(false) } // Camera preview toggle

    // Ask camera permission once
    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (!granted) Log.e("CameraPermission", "Camera permission denied")
    }

    LaunchedEffect(Unit) {
        cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Child Photo") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        bottomBar = {
            if (!photoMode) {
                // Show Take Photo button
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = { photoMode = true },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7A00)),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    ) {
                        Text("Take a Photo", color = Color.White)
                    }
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            // Default photo upload view
            if (!photoMode) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(180.dp)
                            .clip(CircleShape)
                            .border(
                                width = 3.dp,
                                color = if (photoSelected) Color(0xFFFF7A00) else Color.Gray,
                                shape = CircleShape
                            )
                            .background(Color(0xFFF5F5F5))
                            .clickable { /* Future gallery option */ },
                        contentAlignment = Alignment.Center
                    ) {
                        if (photoSelected && imageUri != null) {
                            Image(
                                painter = rememberAsyncImagePainter(imageUri),
                                contentDescription = "Child Photo",
                                modifier = Modifier.fillMaxSize()
                            )
                        } else {
                            Image(
                                painter = painterResource(R.drawable.babysitter_homepage_image),
                                contentDescription = "Placeholder",
                                modifier = Modifier.size(80.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = if (photoSelected) "Photo Selected" else "Upload Child Photo",
                        color = Color.Black
                    )
                }
            } else {
                // --- Camera Mode ---
                CameraPreview(
                    onCapture = { uri ->
                        imageUri = uri
                        photoSelected = true
                        photoMode = false
                        viewModel.updatePhoto(uri)
                        navController.navigate("childlist")
                    },
                    onCancel = { photoMode = false }
                )
            }
        }
    }
}

/** Separate composable for Camera Preview **/
@Composable
fun CameraPreview(
    onCapture: (Uri) -> Unit,
    onCancel: () -> Unit
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    var imageCapture: ImageCapture? by remember { mutableStateOf(null) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Camera preview
        AndroidView(
            factory = { ctx ->
                val previewView = PreviewView(ctx)
                val cameraProviderFuture = ProcessCameraProvider.getInstance(ctx)

                cameraProviderFuture.addListener({
                    val cameraProvider = cameraProviderFuture.get()
                    val preview = Preview.Builder().build().also {
                        it.setSurfaceProvider(previewView.surfaceProvider)
                    }

                    val capture = ImageCapture.Builder()
                        .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                        .build()

                    imageCapture = capture

                    try {
                        cameraProvider.unbindAll()
                        cameraProvider.bindToLifecycle(
                            lifecycleOwner,
                            CameraSelector.DEFAULT_BACK_CAMERA,
                            preview,
                            capture
                        )
                    } catch (exc: Exception) {
                        Log.e("CameraXApp", "Use case binding failed", exc)
                    }
                }, ContextCompat.getMainExecutor(ctx))

                previewView
            },
            modifier = Modifier.fillMaxSize()
        )

        // Capture button
        Button(
            onClick = {
                val photoFile = createPhotoFile(context)
                val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
                imageCapture?.takePicture(
                    outputOptions,
                    ContextCompat.getMainExecutor(context),
                    object : ImageCapture.OnImageSavedCallback {
                        override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                            onCapture(Uri.fromFile(photoFile))
                        }

                        override fun onError(exc: ImageCaptureException) {
                            Log.e("CameraXApp", "Capture failed: ${exc.message}", exc)
                        }
                    }
                )
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7A00)),
            shape = CircleShape,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 40.dp)
                .size(80.dp)
        ) {
            Text("📸")
        }

        // Cancel Button
        TextButton(
            onClick = onCancel,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        ) {
            Text("Cancel", color = Color.White)
        }
    }
}

/** Helper: create image file **/
fun createPhotoFile(context: Context): File {
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
    val storageDir = context.externalCacheDir
    return File.createTempFile("IMG_${timeStamp}_", ".jpg", storageDir)
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewChildPhoto() {
//    ChildPhoto(navController = rememberNavController())
//}
