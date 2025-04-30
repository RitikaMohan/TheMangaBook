package com.example.themangabook.presentation.signin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themangabook.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var error by mutableStateOf<String?>(null)
    var navigateToHome by mutableStateOf(false)
    var isLoading by mutableStateOf(false)

    fun onLogin() {
        if (email.isBlank() || password.isBlank()) {
            error = "Email and Password cannot be empty"
            return
        }

        viewModelScope.launch {
            isLoading = true
            try {
                // Check if user exists
                val userExists = userRepository.isUserRegistered(email.trim())
                if (!userExists) {
                    error = "User not found. Please register first."
                    return@launch
                }

                // Proceed with login if the user exists
                val user = userRepository.signIn(email.trim(), password)
                navigateToHome = true
            } catch (e: Exception) {
                error = e.message ?: "Login failed"
            } finally {
                isLoading = false
            }
        }
    }
}
