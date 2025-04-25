package com.example.themangabook.utils

import android.graphics.Bitmap
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageProxy
import androidx.core.graphics.createBitmap

@OptIn(ExperimentalGetImage::class)
fun ImageProxy.toBitmap(): Bitmap {
    val planeProxy = image?.planes?.get(0)
    val buffer = planeProxy?.buffer
    val bytes = ByteArray(buffer?.capacity() ?: 0)
    buffer?.get(bytes)
    val pixelStride = planeProxy?.pixelStride
    val rowStride = planeProxy?.rowStride
    val width = this.width
    val height = this.height

    val bitmap = createBitmap(width, height)
    bitmap.copyPixelsFromBuffer(java.nio.ByteBuffer.wrap(bytes))
    return bitmap
}