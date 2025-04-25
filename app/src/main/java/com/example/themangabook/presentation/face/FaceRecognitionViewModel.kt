package com.example.themangabook.presentation.face

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import android.graphics.Rect
import com.google.mediapipe.framework.image.BitmapImageBuilder
import com.google.mediapipe.tasks.core.BaseOptions
import com.google.mediapipe.tasks.vision.core.RunningMode
import com.google.mediapipe.tasks.vision.facedetector.FaceDetector


@HiltViewModel
class FaceRecognitionViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val detector: FaceDetector

    init {
        val baseOptions = BaseOptions.builder()
            .setModelAssetPath("blaze_face_short_range.tflite")
            .build()

        val options = FaceDetector.FaceDetectorOptions.builder()
            .setBaseOptions(baseOptions)
            .setMinDetectionConfidence(0.5f)
            .setRunningMode(RunningMode.IMAGE)
            .build()

        detector = FaceDetector.createFromOptions(context, options)
    }

    var uiState by mutableStateOf(FaceRecognitionUiState())
        private set

    suspend fun detectFace(image: Bitmap) {
        val mpImage = BitmapImageBuilder(image).build()
        val result = detector.detect(mpImage)
        val face = result.detections().firstOrNull()

        uiState = if (face != null) {
            val boundingBox = face.boundingBox()
            val androidRect = Rect(
                (boundingBox.left * image.width).toInt(),
                (boundingBox.top * image.height).toInt(),
                (boundingBox.right * image.width).toInt(),
                (boundingBox.bottom * image.height).toInt()
            )

            uiState.copy(
                faceBox = androidRect,
                imageWidth = image.width,
                imageHeight = image.height
            )
        } else {
            uiState.copy(faceBox = null)
        }
    }
}
