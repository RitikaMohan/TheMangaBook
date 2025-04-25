package com.example.themangabook.presentation.face

import android.app.Activity
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.util.Log
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.compose.foundation.layout.fillMaxSize
import android.graphics.Rect
import com.google.mediapipe.framework.image.BitmapImageBuilder


@Composable
fun CameraPreview(onFaceDetected: (Boolean) -> Unit) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val previewView = remember { PreviewView(context) }

    AndroidView(factory = { previewView }) { view ->
        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder().build().also {
                it.surfaceProvider = view.surfaceProvider
            }

            val imageAnalyzer = ImageAnalysis.Builder()
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build()
                .also {
                    it.setAnalyzer(ContextCompat.getMainExecutor(context), { imageProxy ->
                        // ✨ Detect face with MediaPipe or ML Kit here
                        val faceInside = detectFaceInsideBox(imageProxy)
                        onFaceDetected(faceInside)
                        imageProxy.close()
                    })
                }

            val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    lifecycleOwner, cameraSelector, preview, imageAnalyzer
                )
            } catch (e: Exception) {
                Log.e("CameraPreview", "Camera bind failed", e)
            }
        }, ContextCompat.getMainExecutor(context))
    }
}

// Replace with actual MediaPipe logic
fun detectFaceInsideBox(imageProxy: ImageProxy): Boolean {
    // You can integrate MediaPipe face detection here
    // For demonstration purposes, we simulate a face detection.
    // Replace this with actual MediaPipe detection code.

    // Example of using MediaPipe's Face Detection model
    val faceDetected = true // You would use MediaPipe to check if a face is detected in the frame.
    return faceDetected
}