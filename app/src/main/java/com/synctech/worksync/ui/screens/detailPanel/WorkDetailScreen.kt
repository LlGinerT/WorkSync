package com.synctech.worksync.ui.screens.detailPanel

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.synctech.worksync.R
import com.synctech.worksync.ui.models.WorkUIModel

@Composable
fun WorkDetailScreen(work: WorkUIModel) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Detalles del Trabajo", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)

        Text("Trabajo: ${work.jobName}", style = MaterialTheme.typography.bodyLarge)
        Text("Cliente: ${work.clientName}", style = MaterialTheme.typography.bodyLarge)
        Text("Descripción: ${work.description}", style = MaterialTheme.typography.bodyLarge)
        Text("Dirección: ${work.address}", style = MaterialTheme.typography.bodyLarge)

        Button(
            onClick = {
                val uri = Uri.parse("https://www.google.com/maps/search/?api=1&query=${Uri.encode(work.address)}")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                context.startActivity(intent)
            }
        ) {
            Text("Abrir en Google Maps")
        }

        // Imagen temporal o espacio para futura integración con Google Maps
        Image(
            painter = painterResource(id = R.drawable.map),
            contentDescription = "Mapa del trabajo",
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WorkDetailScreenPreview() {
    val work = WorkUIModel(
        jobName = "Instalación de Cableado",
        clientName = "Juan Pérez",
        description = "Instalación de cableado de fibra óptica.",
        address = "Calle de Alcalá, Madrid",
        assignedTo = null
    )
    WorkDetailScreen(work = work)
}