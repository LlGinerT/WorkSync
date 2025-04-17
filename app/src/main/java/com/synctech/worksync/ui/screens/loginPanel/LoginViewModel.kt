package com.synctech.worksync.ui.screens.loginPanel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synctech.worksync.domain.useCases.AuthUserUseCase
import com.synctech.worksync.ui.session.SessionViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    /**
     * Actualiza el valor del campo email y limpia el error si lo hubiera.
     *
     * @param email Nuevo valor introducido en el campo email.
     */
    fun onEmailChanged(email: String) {
        _uiState.value = _uiState.value.copy(email = email, emailError = null)
        updateLoginButtonState()
    }

    /**
     * Actualiza el valor del campo contraseña y limpia el error si lo hubiera.
     *
     * @param password Nuevo valor introducido en el campo contraseña.
     */
    fun onPasswordChanged(password: String) {
        _uiState.value = _uiState.value.copy(password = password, passwordError = null)
        updateLoginButtonState()
    }

    /**
     * Habilita o deshabilita el botón de login según si los campos están completos.
     */
    private fun updateLoginButtonState() {
        val state = _uiState.value
        val isValid = state.email.isNotBlank() && state.password.isNotBlank()
        _uiState.value = state.copy(isLoginEnabled = isValid)
    }

    /**
     * Intenta autenticar al usuario y almacenarlo en la sesión.
     *
     * @param sessionViewModel ViewModel compartido donde se almacena el trabajador autenticado.
     */
    fun loginAndSetSession(sessionViewModel: SessionViewModel) {
        val email = _uiState.value.email
        val password = _uiState.value.password

        _uiState.value = _uiState.value.copy(isLoading = true)

        viewModelScope.launch {
            val emailExists = authUserUseCase.checkIfEmailExists(email)
            // Comprobamos si existe el usuario
            if (!emailExists) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    emailError = "El usuario no existe",
                    passwordError = null
                )
                return@launch // Sale de la corutina si no existe el email en la BD, y "reiniciamos"
                              // con el estado actualizado con el error.
            }

            // Si existe, intentamos autenticar a traves del caso de uso
            val worker = authUserUseCase(email, password)
            if (worker != null) {
                sessionViewModel.setWorker(worker)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    loginSuccess = true,
                    emailError = null,
                    passwordError = null
                )
            } else { //Si no logra autenticar, solo puede significar que la contraseña es incorrecta
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    emailError = null,
                    passwordError = "Contraseña incorrecta"
                )
            }
        }
    }

    /**
     * Metodo que reinicia el estado de la ui.
     */
    fun clearState(){
        _uiState.value = LoginState()
    }
}
