package com.example.themangabook.presentation.signin

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themangabook.domain.repository.UserRepository
import com.google.firebase.firestore.auth.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    var navigateToHome by mutableStateOf(false)
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var error by mutableStateOf<String?>(null)
    var isLoading by mutableStateOf(false)

    fun onRegister(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            error = "Email and Password cannot be empty"
            return
        }
        viewModelScope.launch {
            isLoading = true
            try {
                val newUser = com.example.themangabook.domain.model.User(email, password, true)
                userRepository.registerUser(newUser)
                navigateToHome = true
            } catch (e: Exception) {
                error = e.message ?: "Registration failed"
            } finally {
                isLoading = false
            }
        }
    }
}
