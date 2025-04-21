package com.example.themangabook.presentation.manga

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themangabook.domain.repository.MangaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MangaViewModel @Inject constructor(
    private val mangaRepository: MangaRepository
) : ViewModel() {

    var uiState by mutableStateOf(MangaUiState())
        private set

    init {
        getManga()
    }

    fun getManga() {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)
            try {
                val manga = mangaRepository.getManga(page = 1)
                uiState = uiState.copy(mangaList = manga, isLoading = false)
            } catch (e: Exception) {
                uiState = uiState.copy(errorMessage = e.message, isLoading = false)
            }
        }
    }
}
