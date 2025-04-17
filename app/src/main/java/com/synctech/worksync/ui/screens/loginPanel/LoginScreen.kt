package com.synctech.worksync.ui.screens.loginPanel

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.synctech.worksync.data.testData.MockEmployeesRepository
import com.synctech.worksync.data.testData.MockUserAuthRepository
import com.synctech.worksync.data.testData.MockWorkSessionRepository
import com.synctech.worksync.domain.useCases.AuthUserUseCase
import com.synctech.worksync.domain.useCases.SaveWorkSessionUseCase
import com.synctech.worksync.ui.components.AuthInputField
import com.synctech.worksync.ui.session.SessionViewModel
import com.synctech.worksync.ui.theme.WorkSyncTheme

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
    loginViewModel: LoginViewModel,
    sessionViewModel: SessionViewModel,
    onLoginSuccess: () -> Unit,
    modifier: Modifier = Modifier
) {
    val state by loginViewModel.uiState.collectAsState()

    val debugUserLogin by sessionViewModel.state.collectAsState()//Observamos el sessionViewModel temporalmente
    // con fines de debug en el desarrollo, eliminar mas adelante

    LaunchedEffect(state.loginSuccess) {
        if (state.loginSuccess) {
            loginViewModel.clearState()
            onLoginSuccess() // Cuando el estado pase a loginSuccess = true, ejecutara esta acción,
            // en el mainActivity le pasaremos una acción para navegar a la homeScreen.
            Log.i("LoginScreen", "loginSucces = true")
            Log.d(
                "LoginScreenDebug",
                "userID: ${debugUserLogin.employee?.userId}, " +
                        "name: ${debugUserLogin.employee?.name}, " +
                        "isAdmin: ${debugUserLogin.employee?.isAdmin}"
            )
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
                    onValueChange = loginViewModel::onEmailChanged,
                    label = "Email",
                    errorMessage = state.emailError
                )

                Spacer(modifier = Modifier.height(8.dp))

                AuthInputField(
                    value = state.password,
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
                /*
                Boton con fines de testeo de cerrar sesion hasta que este la navegación
                */
                Row(modifier = Modifier.fillMaxWidth(), Arrangement.Center) {
                    Button(
                        onClick = { sessionViewModel.logout() },
                        modifier = Modifier
                            .height(48.dp)
                            .width(320.dp)
                    ) {
                        Text("Cerrar Sesion", style = MaterialTheme.typography.labelLarge)
                    }
                }
            }
        }
    }
}


@Preview
@Composable
private fun BackgroundPreview() {
    WorkSyncTheme {
        LoginBackground { }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    val mockAuthRepo = MockUserAuthRepository()
    val mockWorkersRepo = MockEmployeesRepository()
    val mockWorkSessionRepository = MockWorkSessionRepository()
    val authUserUseCase = AuthUserUseCase(mockAuthRepo, mockWorkersRepo)
    val saveWorkSessionUseCase = SaveWorkSessionUseCase(mockWorkSessionRepository)

    val loginViewModel = LoginViewModel(authUserUseCase)
    val sessionViewModel = SessionViewModel(saveWorkSessionUseCase)
    WorkSyncTheme {
        LoginScreen(loginViewModel = loginViewModel,
            sessionViewModel = sessionViewModel,
            onLoginSuccess = {})
    }
}

