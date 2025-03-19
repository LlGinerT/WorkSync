package com.synctech.worksync.ui.componentes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import com.synctech.worksync.ui.theme.WorkSyncTheme
import kotlinx.coroutines.launch

/**
 * Función composable para la pantalla de inicio de sesión.
 *
 * Esta pantalla presenta una interfaz de usuario donde los usuarios pueden ingresar su
 * correo electrónico y contraseña para iniciar sesión. Incluye una imagen en la parte superior,
 * campos de entrada para el correo electrónico y la contraseña, un enlace para "Olvidé mi contraseña",
 * y un botón para iniciar sesión. El botón solo se habilita cuando ambos campos están completos.
 *
 * @param email Estado que mantiene el correo electrónico ingresado por el usuario.
 * @param password Estado que mantiene la contraseña ingresada por el usuario.
 * @param isLoading Estado que indica si el proceso de inicio de sesión está en curso.
 * @param loginEnabled Estado que determina si el botón de inicio de sesión está habilitado
 *                     en función de los campos llenos.
 */
@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var isLoading by remember { mutableStateOf(false) }
    var loginEnabled by remember { mutableStateOf(false) }

    // Detecta si los campos están llenos para habilitar el botón de inicio de sesión
    LaunchedEffect(email, password) {
        loginEnabled = email.text.isNotEmpty() && password.text.isNotEmpty()
    }

    WorkSyncTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (isLoading) {
                Box(Modifier.fillMaxSize()) {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
            } else {
                HeaderImage(Modifier.fillMaxWidth().height(250.dp)) // Establece la altura de la imagen
                Spacer(modifier = Modifier.height(16.dp))
                EmailField(email) { email = it }
                Spacer(modifier = Modifier.height(8.dp))
                PasswordField(password) { password = it }
                Spacer(modifier = Modifier.height(8.dp))
                ForgotPassword(Modifier.align(Alignment.End))
                Spacer(modifier = Modifier.height(16.dp))
                LoginButton(
                    enabled = loginEnabled,
                    onLoginClick = {
                        isLoading = true
                        // Simula un retraso en el inicio de sesión
                        kotlinx.coroutines.GlobalScope.launch {
                            kotlinx.coroutines.delay(2000)
                            isLoading = false
                        }
                    }
                )
            }
        }
    }
}

/**
 * Función composable para mostrar la imagen del encabezado.
 *
 * @param modifier El modificador que se aplica a la imagen (por ejemplo, tamaño, relleno).
 */
@Composable
fun HeaderImage(modifier: Modifier) {
    // Placeholder para la imagen del encabezado
    val image: Painter = painterResource(id = com.synctech.worksync.R.drawable.loginimage) // Reemplaza con tu recurso de imagen
    Image(painter = image, contentDescription = "Imagen del Encabezado", modifier = modifier)
}

/**
 * Función composable para el campo de entrada de correo electrónico.
 *
 * @param value El valor actual del campo de entrada de correo electrónico.
 * @param onValueChange Un callback para actualizar el valor del campo de correo electrónico cuando cambia.
 */
@Composable
fun EmailField(value: TextFieldValue, onValueChange: (TextFieldValue) -> Unit) {
    val colorScheme = MaterialTheme.colorScheme
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        textStyle = TextStyle(color = colorScheme.onSurface),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = { /* Mueve el enfoque al siguiente campo */ }
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .border(1.dp, colorScheme.outline, MaterialTheme.shapes.small),
                verticalAlignment = Alignment.CenterVertically
            ) {
                innerTextField()
            }
        }
    )
}

/**
 * Función composable para el campo de entrada de contraseña.
 *
 * @param value El valor actual del campo de entrada de contraseña.
 * @param onValueChange Un callback para actualizar el valor del campo de contraseña cuando cambia.
 */
@Composable
fun PasswordField(value: TextFieldValue, onValueChange: (TextFieldValue) -> Unit) {
    val colorScheme = MaterialTheme.colorScheme
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        textStyle = TextStyle(color = colorScheme.onSurface),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .border(1.dp, colorScheme.outline, MaterialTheme.shapes.small),
                verticalAlignment = Alignment.CenterVertically
            ) {
                innerTextField()
            }
        }
    )
}

/**
 * Función composable para mostrar el enlace de "Olvidaste la contraseña?".
 *
 * @param modifier El modificador que se aplica al texto "Olvidaste la contraseña?" (por ejemplo, clickeable).
 */
@Composable
fun ForgotPassword(modifier: Modifier) {
    val colorScheme = MaterialTheme.colorScheme
    Text(
        text = "Olvidaste la Contraseña?",
        modifier = modifier.clickable { /* Manejar acción de recuperar contraseña */ },
        color = colorScheme.primary,
        fontWeight = FontWeight.Bold
    )
}

/**
 * Función composable para el botón de inicio de sesión.
 *
 * @param enabled Un valor booleano que indica si el botón de inicio de sesión está habilitado o no.
 * @param onLoginClick Un callback para ejecutar la acción de inicio de sesión cuando se hace clic en el botón.
 */
@Composable
fun LoginButton(enabled: Boolean, onLoginClick: () -> Unit) {
    val colorScheme = MaterialTheme.colorScheme
    Button(
        onClick = onLoginClick,
        modifier = Modifier.fillMaxWidth().height(48.dp),
        colors = buttonColors(
            containerColor = colorScheme.primary,
            contentColor = colorScheme.onPrimary
        ),
        enabled = enabled
    ) {
        Text("Iniciar Sesión")
    }
}

/**
 * Función de vista previa para la función composable LoginScreen.
 */
@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreen()
}
