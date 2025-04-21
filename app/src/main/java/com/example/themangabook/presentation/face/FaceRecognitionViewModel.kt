package com.example.themangabook.presentation.face

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FaceRecognitionViewModel @Inject constructor() : ViewModel() {
    var uiState by mutableStateOf(FaceRecognitionUiState())
        private set

    fun updateDetectionStatus(faceInside: Boolean) {
        uiState = uiState.copy(faceDetected = true, isInsideBox = faceInside)
    }

    fun resetStatus() {
        uiState = FaceRecognitionUiState()
    }
}
