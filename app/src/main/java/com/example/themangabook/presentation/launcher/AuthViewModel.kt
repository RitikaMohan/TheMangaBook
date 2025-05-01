package com.example.themangabook.presentation.launcher

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themangabook.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    var isUserSignedIn by mutableStateOf<Boolean?>(null)
        private set

    init {
        viewModelScope.launch {
            val user = userRepository.getSignedInUser()
            isUserSignedIn = user != null
            Log.d("AuthViewModel", "User signed in: $isUserSignedIn")
        }
    }
}
