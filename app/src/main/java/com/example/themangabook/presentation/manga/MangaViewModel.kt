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
import android.util.Log

@HiltViewModel
class MangaViewModel @Inject constructor(
    private val repository: MangaRepository
) : ViewModel() {

    var uiState by mutableStateOf(MangaUiState())
        private set

    init {
        loadManga()
    }

    private fun loadManga(page: Int = 1) {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)
            try {
                val mangaList = repository.getManga(page)
                Log.d("MangaViewModel", "Loaded ${mangaList.size} manga")
                uiState = uiState.copy(mangaList = mangaList, isLoading = false)
            } catch (e: Exception) {
                uiState = uiState.copy(errorMessage = e.message, isLoading = false)
            }
        }
    }

    fun refresh() {
        viewModelScope.launch {
            try {
                val refreshed = repository.refreshManga()
                uiState = uiState.copy(mangaList = refreshed)
            } catch (e: Exception) {
                uiState = uiState.copy(errorMessage = e.message)
            }
        }
    }
}
