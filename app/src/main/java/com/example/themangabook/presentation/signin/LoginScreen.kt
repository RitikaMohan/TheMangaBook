package com.example.themangabook.presentation.signin

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.themangabook.R

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginSuccess: () -> Unit = {},
    onSignUpClick: () -> Unit = {},
    onForgotPasswordClick: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val error = viewModel.error
    val isLoading = viewModel.isLoading
    val navigateToHome = viewModel.navigateToHome

    // ✅ Observe navigation state and navigate only once
    LaunchedEffect(navigateToHome) {
        if (navigateToHome) {
            onLoginSuccess()
            viewModel.navigateToHome = false // Reset flag to avoid navigating again
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF1E1E1E), RoundedCornerShape(20.dp))
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Zenithra", style = MaterialTheme.typography.headlineSmall, color = Color.White)
            Text("Welcome back", style = MaterialTheme.typography.headlineMedium, color = Color.White)
            Text("Please enter your details to sign in", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)

            Spacer(modifier = Modifier.height(20.dp))

            // Social sign-in buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButton(onClick = { /* TODO: Google sign-in */ }) {
                    Icon(painter = painterResource(id = R.drawable.ic_google), contentDescription = null, tint = Color.White)
                }
                IconButton(onClick = { /* TODO: Apple sign-in */ }) {
                    Icon(painter = painterResource(id = R.drawable.ic_apple), contentDescription = null, tint = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text("OR", color = Color.Gray)

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Your Email Address", color = Color.Gray) },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    disabledTextColor = Color.Gray,
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.Gray,
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password", color = Color.Gray) },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { /* TODO: Toggle visibility */ }) {
                        Icon(painter = painterResource(id = R.drawable.visibility), contentDescription = null, tint = Color.White, modifier = Modifier.size(20.dp))
                    }
                },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    disabledTextColor = Color.Gray,
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.Gray,
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            error?.let {
                Text(text = it, color = Color.Red, style = MaterialTheme.typography.bodySmall)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Forgot password?",
                color = Color.Gray,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .align(Alignment.End)
                    .clickable { onForgotPasswordClick() }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    viewModel.email = email
                    viewModel.password = password
                    viewModel.onLogin()
                },
                enabled = email.isNotBlank() && password.isNotBlank(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
            ) {
                Text("Sign In", color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Don’t have an account?", color = Color.Gray)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Sign Up",
                    color = Color(0xFFD0BCFF),
                    modifier = Modifier.clickable { onSignUpClick() }
                )
            }
        }
    }
}
