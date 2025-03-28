package com.synctech.worksync.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.synctech.worksync.ui.models.WorkUIModel

/**
 * Componente que representa una tarjeta elevada con la información de un trabajo.
 *
 * @param work El objeto `WorkUIModel` que contiene la información de un trabajo,
 *             incluyendo el nombre del trabajo, cliente, descripción y dirección.
 */
@Composable
fun WorkCard(work: WorkUIModel, onClick: () -> Unit, cardColor:Color ) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.elevatedCardColors(
                containerColor = cardColor
            ),
        modifier = Modifier
            .size(220.dp,180.dp)
            .clickable { onClick() }

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),  // Ajustamos el padding para mejorar el espacio
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = "Trabajo: ${work.jobName}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )

            Text(
                text = "Cliente: ${work.clientName}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )

            Text(
                text = "Descripción: ${work.description}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )

            Text(
                text = "Dirección: ${work.address}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
        }
    }
}



