package com.synctech.worksync.ui.screens.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.synctech.worksync.ui.session.SessionViewModel

/**
 * Pantalla de inicio de sesión.
 *
 * @param loginViewModel ViewModel encargado de gestionar el estado de la pantalla.
 * @param sessionViewModel ViewModel compartido que guarda el usuario logueado.
 * @param onLoginSuccess Callback que se ejecuta si el login es exitoso.
 */
@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel,
    sessionViewModel: SessionViewModel,
    onLoginSuccess: () -> Unit
) {
    val state by loginViewModel.uiState.collectAsState()

    if (state.loginSuccess) {
        onLoginSuccess() // Cuando el estado pase a loginSuccess = true, ejecutara esta acción,
        // en el mainActivity le pasaremos una acción para navegar a la homeScreen.
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            OutlinedTextField(
                value = state.email,
                onValueChange = loginViewModel::onEmailChanged,
                label = { Text("Email") },
                isError = state.emailError != null,
                supportingText = {
                    state.emailError?.let {
                        Text(
                            it,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                colors = outlineColors(isError = state.emailError != null),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = state.password,
                onValueChange = loginViewModel::onPasswordChanged,
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                isError = state.passwordError != null,
                supportingText = {
                    state.passwordError?.let {
                        // ?.let es una forma de decir si no es null haz algo,
                        // nos ahorra el if (state.blabla != null) por complicarlo un poco
                        // y aprender cosas nuevas
                        Text(
                            it,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                colors = outlineColors(isError = state.passwordError != null),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { loginViewModel.loginAndSetSession(sessionViewModel) },
                enabled = state.isLoginEnabled,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Iniciar sesión")
            }
        }
    }
}

/**
 * Función auxiliar para manejar el color del `OutlinedTextField`
 *
 * @param isError Boolean que comprueba si en el estado existe un `emailError` o un `passwordError`
 */
@Composable
fun outlineColors(isError: Boolean) =
    OutlinedTextFieldDefaults.colors(
        focusedBorderColor = if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.outlineVariant,
        unfocusedBorderColor = if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.outline
        //TODO(Comprobar los colores del outline en figma)
    )
