package com.example.themangabook.presentation.launcher

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LauncherScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    onNavigateToHome: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    val isUserSignedIn = viewModel.isUserSignedIn

    LaunchedEffect(isUserSignedIn) {
        when (isUserSignedIn) {
            true -> onNavigateToHome()
            false -> onNavigateToLogin()
            null -> {} // Still loading
        }
    }

    // Optional loading UI
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}
