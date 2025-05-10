package com.synctech.worksync.ui.screens.loginPanel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synctech.worksync.domain.exceptions.AuthError
import com.synctech.worksync.domain.useCases.session.AuthUserUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


/**
 * ViewModel de la pantalla de login.
 *
 * @property authUserUseCase Caso de uso para autenticar y obtener el trabajador.
 */
class LoginViewModel(
    private val authUserUseCase: AuthUserUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginState())
    val uiState: StateFlow<LoginState> = _uiState.asStateFlow()

    private val _eventFlow = MutableSharedFlow<LoginUiEvent>()
    val eventFlow: SharedFlow<LoginUiEvent> = _eventFlow.asSharedFlow()

    /**
     * Actualiza el campo de email en el estado y limpia el error correspondiente.
     *
     * @param email Nuevo valor introducido en el campo de email.
     */
    fun onEmailChanged(email: String) {
        _uiState.update { it.copy(email = email, errorMessage = null) }
        updateLoginButtonState()
    }

    /**
     * Actualiza el campo de contraseña en el estado y limpia el error correspondiente.
     *
     * @param password Nuevo valor introducido en el campo de contraseña.
     */
    fun onPasswordChanged(password: String) {
        _uiState.update { it.copy(password = password, errorMessage = null) }
        updateLoginButtonState()
    }

    /**
     * Habilita o deshabilita el botón de login en función de si los campos están completos.
     */
    private fun updateLoginButtonState() {
        val state = _uiState.value
        val isValid = state.email.isNotBlank() && state.password.isNotBlank()
        _uiState.value = state.copy(isLoginEnabled = isValid)
    }

    /**
     * Intenta autenticar al usuario con el caso de uso [AuthUserUseCase]. Si la autenticación es
     * exitosa, ejecuta una lambda suspendida que se encarga de iniciar la sesión (desacoplada).
     *
     * @param onLoginConfirmed Lógica que se ejecuta si el usuario es válido (ej. sessionViewModel.login(user)).
     */
    fun login() {
        val email = _uiState.value.email
        val password = _uiState.value.password

        _uiState.update {
            it.copy(
                isLoading = true,
                errorMessage = null,
            )
        }

        viewModelScope.launch {
            val result = authUserUseCase(email, password)

            result.fold(onSuccess = { user ->
                _uiState.update { it.copy(isLoading = false) }
                Log.i("LoginViewModel", "Inicio de sesión exitoso para ${user.userId}")
                _eventFlow.emit(LoginUiEvent.LoginSuccess)
                clearState()

            }, onFailure = { error ->
                _uiState.update { state ->
                    when (error) {
                        is AuthError.InvalidCredentials, is AuthError.UserNotFound -> {
                            Log.w("LoginViewModel", "Credenciales inválidas")
                            state.copy(
                                isLoading = false,
                                errorMessage = "Usuario o contraseña incorrectos",
                            )
                        }

                        else -> {
                            Log.e("LoginViewModel", "Error inesperado durante el login", error)
                            state.copy(isLoading = false)
                        }
                    }
                }
            })
        }
    }

    /**
     * Reinicia el estado de la pantalla de login (campos vacíos y errores nulos).
     */
    private fun clearState() {
        _uiState.value = LoginState()
    }

}

