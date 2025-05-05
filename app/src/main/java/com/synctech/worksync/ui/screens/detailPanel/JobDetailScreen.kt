
package com.synctech.worksync.ui.screens.detailPanel

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.synctech.worksync.R
import com.synctech.worksync.ui.models.JobUiModel


/**
 * Fondo de pantalla para el detalle del trabajo.
 *
 * @param content Contenido principal a renderizar.
 */
@Composable
private fun JobDetailBackground(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        content()
    }
}

/**
 * Pantalla que muestra los detalles de un trabajo específico.
 *
 * @param jobDetailViewModel ViewModel que gestiona la lógica de esta pantalla.
 * @param jobId ID del trabajo a mostrar, recibido desde la navegación.
 */
@Composable
fun JobDetailScreen(
    jobDetailViewModel: JobDetailViewModel, jobId: String
) {
    val context = LocalContext.current
    val uiState by jobDetailViewModel.uiState.collectAsState()

    LaunchedEffect(jobId) {
        jobDetailViewModel.loadWorkDetail(jobId)
    }

    JobDetailBackground {
        when {
            uiState.showLoadingIndicator -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            uiState.errorMessage != null -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = uiState.errorMessage ?: "Error desconocido",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            uiState.job != null -> {
                val job = uiState.job!!
                JobDetailContent(job = job, context = context)
            }
        }
    }
}


/**
 * Fila con un título y su valor, usada para mostrar los campos del trabajo.
 *
 * @param title Título del campo (por ejemplo: "Cliente").
 * @param value Valor asociado al título.
 */
@Composable
private fun DetailRow(title: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(
            text = title, style = MaterialTheme.typography.labelSmall.copy(
                color = Color.Gray, fontSize = 12.sp
            )
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

/**
 * Contenido principal del detalle del trabajo.
 *
 * @param job Objeto con los datos del trabajo.
 * @param context Contexto actual de la aplicación, necesario para lanzar el intent de Maps.
 */
@Composable
private fun JobDetailContent(
    job: JobUiModel, context: Context
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Detalles del Trabajo",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = cardElevation(defaultElevation = 6.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                DetailRow(title = "Trabajo", value = job.jobName)
                DetailRow(title = "Cliente", value = job.clientName)
                DetailRow(title = "Descripción", value = job.description)
                DetailRow(title = "Dirección", value = job.address)
            }


            ElevatedButton(
                onClick = {
                    val uri =
                        Uri.parse("https://www.google.com/maps/search/?api=1&query=${Uri.encode(job.address)}")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    context.startActivity(intent)
                }, modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Icon(Icons.Filled.LocationOn, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Google Maps")
            }

            Image(
                painter = painterResource(id = R.drawable.map),
                contentDescription = "Mapa del trabajo",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(top = 8.dp)
                    .clip(RoundedCornerShape(12.dp))
            )

        }

        ElevatedButton(
            onClick = {
                val uri = Uri.parse(
                    "https://www.google.com/maps/search/?api=1&query=${Uri.encode(job.address)}"
                )
                val intent = Intent(Intent.ACTION_VIEW, uri)
                context.startActivity(intent)
            }, modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Icon(Icons.Filled.LocationOn, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Ver en Google Maps")
        }

        Image(
            painter = painterResource(id = R.drawable.map),
            contentDescription = "Mapa del trabajo",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(top = 8.dp)
                .clip(RoundedCornerShape(12.dp))
        )
    }
}




