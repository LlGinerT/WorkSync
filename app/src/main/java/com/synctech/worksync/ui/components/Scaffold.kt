package com.synctech.worksync.ui.components

import com.synctech.worksync.ui.theme.WorkSyncTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.synctech.worksync.R

/**
 * ScaffoldExample es un contenedor principal que incluye una barra superior (TopAppBar),
 * una barra inferior (BottomAppBar) y el contenido principal.
 *
 * @param content Composable que representa el contenido principal de la pantalla.
 * El parámetro content se utiliza para incluir el contenido que se mostrará debajo
 * de la barra superior y encima de la barra inferior. Este contenido recibe el
 * paddingValues proporcionado por Scaffold.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldExample(content: @Composable (PaddingValues) -> Unit) {
    Scaffold(
        topBar = { TopBar() },  // Llama a la función TopBar para la barra superior
        bottomBar = { BottomBar() }  // Llama a la función BottomBar para la barra inferior
    ) { paddingValues ->  // Padding para el contenido
        content(paddingValues)  // Muestra el contenido con el padding adecuado
    }
}

/**
 * TopBar representa la barra superior de la pantalla que contiene el título "WorkSync"
 * y tres iconos para "Trabajo", "Inventario" y "Fichaje".
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        title = {},  // Sin título específico, el título se maneja más abajo con un texto
        navigationIcon = {  // Iconos y texto en la barra superior
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp), // Espacio superior
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Título centrado sobre los íconos
                Text(text = "WorkSync",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    style = TextStyle(
                        fontSize = 20.sp,  // Tamaño de fuente
                        fontWeight = FontWeight.Bold  // Negrita para el texto
                    )
                )

                Spacer(modifier = Modifier.height(8.dp)) // Espacio entre el título y los íconos

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),  // Padding horizontal para los íconos
                    horizontalArrangement = Arrangement.SpaceBetween  // Los íconos ocupan todo el ancho disponible
                ) {
                    // Iconos para diferentes funcionalidades
                    TopBarIcon(iconRes = R.drawable.work, description = "Trabajo")
                    TopBarIcon(iconRes = R.drawable.inventory, description = "Inventario")
                    TopBarIcon(iconRes = R.drawable.access_time, description = "Fichaje")
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary  // Color de fondo de la barra superior
        )
    )
}

/**
 * TopBarIcon crea un icono interactivo que realiza una acción cuando se hace clic en él.
 *
 * @param iconRes Recurso del ícono a mostrar en el botón.
 * Este parámetro define el recurso del ícono que se mostrará en la interfaz de usuario.
 * @param description Descripción accesible del ícono.
 * Este parámetro se utiliza para proporcionar una descripción de accesibilidad para el ícono,
 * lo que mejora la experiencia de usuarios con discapacidades visuales.
 */
@Composable
fun TopBarIcon(iconRes: Int, description: String) {
    IconButton(onClick = { /* Acción para cada icono */ }) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = description,
            tint = MaterialTheme.colorScheme.onPrimaryContainer  // Usamos el mismo color que en el BottomBar
        )
    }
}

/**
 * BottomBar representa la barra inferior que contiene tres iconos interactivos:
 * Inicio, Calendario y Notificaciones.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBar() {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primary,  // Color de fondo de la barra inferior
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer  // Color de los íconos
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),  // Padding de los íconos en la barra inferior
            horizontalArrangement = Arrangement.SpaceBetween  // Distribuye los íconos en el espacio disponible
        ) {
            // Botón de Inicio
            IconButton(onClick = { /* Acción para ir al inicio */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.home),  // Asegúrate de tener este ícono en res/drawable
                    contentDescription = "Inicio",  // Descripción accesible
                    tint = MaterialTheme.colorScheme.onPrimaryContainer  // Color del ícono
                )
            }

            // Botón de Calendario
            IconButton(onClick = { /* Acción para abrir el calendario */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.calendar),  // Asegúrate de tener este ícono en res/drawable
                    contentDescription = "Calendario",  // Descripción accesible
                    tint = MaterialTheme.colorScheme.onPrimaryContainer  // Color del ícono
                )
            }

            // Botón de Notificaciones
            IconButton(onClick = { /* Acción para abrir las notificaciones */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.notifications),  // Asegúrate de tener este ícono en res/drawable
                    contentDescription = "Notificaciones",  // Descripción accesible
                    tint = MaterialTheme.colorScheme.onPrimaryContainer  // Color del ícono
                )
            }
        }
    }
}

/**
 * Función de vista previa que muestra el diseño del ScaffoldExample en el entorno de desarrollo.
 */
@Preview(showBackground = true)
@Composable
fun ScaffoldExamplePreview() {
    ScaffoldExample { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues))  // Muestra un espacio vacío con padding
    }
}
