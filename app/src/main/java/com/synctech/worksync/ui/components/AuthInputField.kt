package com.synctech.worksync.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview

/**
 * Campo de texto reutilizable para autenticación (email, contraseña, etc.).
 *
 * Muestra un `OutlinedTextField` que puede actuar como campo de email o de contraseña.
 * Soporta visualización de mensajes de error y estilo de error.
 *
 * @param value Texto actual basado en el estado del LoginViewModel (ej. `state.email`).
 * @param onValueChange Función que se llama cuando el texto cambia (ej. `loginViewModel::onEmailChanged`).
 * @param label Etiqueta flotante del campo ("Email", "Contraseña").
 * @param errorMessage Mensaje de error a mostrar debajo del campo. Si es `null`, no se muestra y no se aplica estilo de error (ej. `state.emailError`).
 * @param isPassword Por defecto `false`, si es `true`, el campo oculta el texto como contraseña.
 */

@Composable
fun AuthInputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    errorMessage: String?,
    isPassword: Boolean = false

) {
    val errorColor = MaterialTheme.colorScheme.error
    val focusedColor = MaterialTheme.colorScheme.primary
    val unfocusedColor = MaterialTheme.colorScheme.outline

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        isError = errorMessage != null,
        supportingText = {
            errorMessage?.let {
                // ?.let es una forma de decir si no es null haz algo,
                // nos ahorra el if (errorMessage != null) por complicarlo un poco
                // y aprender cosas nuevas
                Text(
                    it,
                    color = errorColor
                )
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = if (errorMessage != null) errorColor else focusedColor,
            unfocusedBorderColor = if (errorMessage != null) errorColor else unfocusedColor
            //TODO(Comprobar si funciona el focused, no se puede en el @preview)
        ),
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        modifier = Modifier.fillMaxWidth() //TODO (Comprobar el tamaño necesario en la pantalla)
    )
}

@Preview(showBackground = true)
@Composable
fun EmailInputPreview_NoError() {
    AuthInputField(
        value = "usuario@email.com",
        onValueChange = {},
        label = "Email",
        errorMessage = null,
        isPassword = false
    )
}

@Preview(showBackground = true)
@Composable
fun EmailInputPreview_WithError() {
    AuthInputField(
        value = "usuario@email",
        onValueChange = {},
        label = "Email",
        errorMessage = "Correo inválido",
        isPassword = false
    )
}

@Preview(showBackground = true)
@Composable
fun PasswordInputPreview_noError() {
    AuthInputField(
        value = "1234",
        onValueChange = {},
        label = "Contraseña",
        errorMessage = null,
        isPassword = true
    )
}

@Preview(showBackground = true)
@Composable
fun PasswordInputPreview_WithError() {
    AuthInputField(
        value = "1234",
        onValueChange = {},
        label = "Contraseña",
        errorMessage = "Contraseña incorrecta",
        isPassword = true
    )
}

