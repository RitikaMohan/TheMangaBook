package com.example.themangabook.presentation.face;

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.hilt.navigation.compose.hiltViewModel
import kotlin.math.roundToInt

@Composable
fun FaceRecognitionScreen(
    viewModel: FaceRecognitionViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState
    val context = LocalContext.current

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val density = LocalDensity.current

    Box(modifier = Modifier.fillMaxSize()) {
        CameraPreview(
            onFaceDetected = { faceDetected ->
                if (faceDetected) {
                    Toast.makeText(context, "Face detected!", Toast.LENGTH_SHORT).show()
                }
            }
        )

        uiState.faceBox?.let { box ->
            // Convert image-based coordinates to screen coordinates
            val scaleX = screenWidth / uiState.imageWidth.toFloat()
            val scaleY = screenHeight / uiState.imageHeight.toFloat()

            val left = box.left * scaleX
            val top = box.top * scaleY
            val right = box.right * scaleX
            val bottom = box.bottom * scaleY

            val boxColor = Color.Green

            Box(
                modifier = Modifier
                    .offset {
                        // Convert Dp to pixels (Float) and then round
                        IntOffset(
                            left.toPx().roundToInt(),
                            top.toPx().roundToInt()
                        )
                    }
                    .size(
                        width = (right - left),
                        height = (bottom - top)
                    )
                    .border(2.dp, boxColor)
            )
        }
    }
}
