package com.synctech.worksync.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.synctech.worksync.ui.models.JobUiModel
import com.synctech.worksync.ui.theme.WorkSyncTheme

/**
 * Componente que representa una tarjeta elevada con la información de un trabajo.
 *
 * @param job El objeto `WorkUIModel` que contiene la información de un trabajo,
 *             incluyendo el nombre del trabajo, cliente, descripción y dirección.
 */
@Composable
fun JobCard(job: JobUiModel, onClick: () -> Unit) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp, pressedElevation = 0.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ),
        modifier = Modifier
            .size(220.dp, 180.dp)
            .clickable { onClick() }

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),  // Ajustamos el padding para mejorar el espacio
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = "Trabajo: ${job.jobName}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )

            Text(
                text = "Cliente: ${job.clientName}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )

            Text(
                text = "Descripción: ${job.description}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )

            Text(
                text = "Dirección: ${job.address}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
        }
    }
}

@Preview
@Composable
fun JobCardPreview() {
    WorkSyncTheme {
        JobCard(
            job = JobUiModel(
                jobName = "Ejemplo de job",
                clientName = "Pepito Perez",
                description = "Instalación fibra optica",
                address = "C/labradores 40",
                assignedTo = null
            )
        ) {}
    }

}

