package com.synctech.worksync.ui.screens.profile


import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.synctech.worksync.ui.session.SessionViewModel

@Composable
fun ProfileScreen(sessionViewModel: SessionViewModel) {
    val worker = sessionViewModel.worker.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        worker?.let {
            Text("Nombre: ${it.name}")
            Spacer(modifier = Modifier.height(8.dp))
            Text("Rol: ${if (it.isAdmin) "Administrador" else "Tecnico"}")
            Spacer(modifier = Modifier.height(8.dp))
            Text("Horas trabajadas: ${it.workedHours}")
        } ?: Text("Usuario no cargado")
    }
}
