package com.example.themangabook.presentation.signin

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
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
import com.example.themangabook.R

@Composable
fun LoginScreen(
    onSignInClick: (String, String) -> Unit = { _, _ -> },
    onSignUpClick: () -> Unit = {},
    onForgotPasswordClick: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212)) // Dark background
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF1E1E1E), RoundedCornerShape(20.dp))
                .padding(24.dp)
        ) {
            Text(
                text = "Zenithra",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White
            )
            Text(
                text = "Welcome back",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                modifier = Modifier.padding(vertical = 4.dp)
            )
            Text(
                text = "Please enter your details to sign in",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Your Email Address", color = Color.Gray) },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    disabledTextColor = Color.Gray,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.Gray,
                    disabledIndicatorColor = Color.Gray,
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
                    IconButton(onClick = { /* toggle visibility */ }) {
                        Icon(painter = painterResource(id = R.drawable.visibility), contentDescription = null, tint = Color.White)
                    }
                },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    disabledTextColor = Color.Gray,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.Gray,
                    disabledIndicatorColor = Color.Gray,
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            errorMessage.takeIf { it.isNotEmpty() }?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (email.isBlank() || password.isBlank()) {
                        errorMessage = "Email and Password cannot be empty"
                    } else {
                        errorMessage = ""
                        onSignInClick(email, password)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Sign In", color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
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