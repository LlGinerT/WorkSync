package com.synctech.worksync.ui.screens.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.synctech.worksync.ui.components.AuthInputField
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
            AuthInputField(
                value = state.email,
                onValueChange = loginViewModel::onEmailChanged,
                label = "Email",
                errorMessage = state.emailError
            )

            Spacer(modifier = Modifier.height(8.dp))

            AuthInputField(
                value = state.password,
                // esta sintaxis en onValueChange es un function reference, le pasa una función como
                // parametro a otro componente
                // seria lo mismo que = {loginViewModel.onPasswordChanged(it)} pero mas corto.
                onValueChange = loginViewModel::onPasswordChanged,
                label = "Contraseña",
                errorMessage = state.passwordError,
                isPassword = true
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
