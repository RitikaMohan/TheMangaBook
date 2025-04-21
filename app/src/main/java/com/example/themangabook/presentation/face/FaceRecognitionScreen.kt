package com.example.themangabook.presentation.face;

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun FaceRecognitionScreen(
    viewModel: FaceRecognitionViewModel = hiltViewModel()
) {
    val state = viewModel.uiState

    Box(modifier = Modifier.fillMaxSize()) {
        CameraPreview(onFaceDetected = { insideBox ->
            viewModel.updateDetectionStatus(insideBox)
        })

        val boxColor = if (state.isInsideBox) Color.Green else Color.Red
        Box(
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.Center)
                .border(4.dp, boxColor, RoundedCornerShape(8.dp))
        )
    }
}
