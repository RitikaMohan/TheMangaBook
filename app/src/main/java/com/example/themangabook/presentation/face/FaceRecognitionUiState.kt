package com.example.themangabook.presentation.face

import android.graphics.Rect

data class FaceRecognitionUiState(
    val faceBox: Rect? = null,
    val imageWidth: Int = 0,
    val imageHeight: Int = 0
)