package com.example.themangabook.presentation.face


import android.content.Context
import android.graphics.Bitmap
import android.os.SystemClock
import com.google.mediapipe.framework.image.BitmapImageBuilder
import com.google.mediapipe.tasks.core.BaseOptions
import com.google.mediapipe.tasks.vision.core.RunningMode
import com.google.mediapipe.tasks.vision.facedetector.FaceDetector
import com.google.mediapipe.tasks.vision.facedetector.FaceDetectorResult

class FaceDetectorHelper(context: Context) {

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

    fun detectFace(bitmap: Bitmap): ResultBundle? {
        val startTime = SystemClock.uptimeMillis()
        val mpImage = BitmapImageBuilder(bitmap).build()
        val result = detector.detect(mpImage)

        val inferenceTime = SystemClock.uptimeMillis() - startTime
        return result?.let {
            ResultBundle(it, inferenceTime, bitmap.width, bitmap.height)
        }
    }

    data class ResultBundle(
        val detectionResult: FaceDetectorResult,
        val inferenceTime: Long,
        val imageWidth: Int,
        val imageHeight: Int
    )
}
