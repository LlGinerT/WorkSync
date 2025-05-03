package com.synctech.worksync.ui.screens.loginPanel

/**
 * Estado de la pantalla de inicio de sesión (`LoginScreen`), gestionado por el `LoginViewModel`.
 *
 * Contiene toda la información necesaria para representar el estado de los campos del formulario,
 * los posibles errores de validación, y el control de la interfaz (habilitación de botones, carga, éxito, etc.).
 *
 * @property email Valor actual del campo de email introducido por el usuario.
 * @property password Valor actual del campo de contraseña introducido por el usuario.
 * @property errorMessage Mensaje de error asociado al email si no es válido, o `null` si es correcto.
 * @property isLoginEnabled Indica si el botón de inicio de sesión debe estar habilitado, en función de la validez del formulario.
 * @property isLoading Indica si se debe mostrar una pantalla de carga mientras se procesa el inicio de sesión.
 */
data class LoginState(
    val email: String = "",
    val password: String = "",
    val errorMessage: String? = null,
    val isLoginEnabled: Boolean = false,
    val isLoading: Boolean = false,
)