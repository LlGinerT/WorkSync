package com.synctech.worksync.ui.screens.loginPanel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.synctech.worksync.ui.components.AuthInputField

@Composable
private fun LoginBackground(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primaryContainer,
                        MaterialTheme.colorScheme.surfaceContainerLowest
                    )
                )
            )
    ) {
        content()
    }
}

/**
 * Pantalla de inicio de sesión.
 *
 * @param loginViewModel ViewModel encargado de gestionar el estado de la pantalla.
 * @param sessionViewModel ViewModel compartido que guarda el usuario logueado.
 * @param onLoginSuccess Callback que se ejecuta si el login es exitoso.
 */
@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel
) {


    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is LoginUiEvent.LoginSuccess -> {
                    onLoginSuccess()
                }
            }
        }
    }

    LoginBackground {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (state.isLoading) {
                CircularProgressIndicator()
            } else {
                AuthInputField(
                    value = state.email,
                    // esta sintaxis en onValueChange es un function reference, le pasa una función como
                    // parametro a otro componente
                    // seria lo mismo que = {loginViewModel.onPasswordChanged(it)} pero mas corto.
                    onValueChange = viewModel::onEmailChanged,
                    label = "Email",
                    errorMessage = state.errorMessage
                )

                Spacer(modifier = Modifier.height(8.dp))

                AuthInputField(
                    value = state.password,
                    onValueChange = viewModel::onPasswordChanged,
                    label = "Contraseña",
                    errorMessage = state.errorMessage,
                    isPassword = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        viewModel.login()
                    },
                    enabled = state.isLoginEnabled,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Iniciar sesión")
                }
            }
        }
    }
}

