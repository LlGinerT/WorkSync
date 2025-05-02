package com.synctech.worksync.ui.screens.userPanel


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.synctech.worksync.ui.uiUtils.secondsToTimeString
import com.synctech.worksync.ui.uiUtils.timestampToString
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun UserPanelScreen(onLogoutSuccess: () -> Unit, viewModel: UserPanelViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val user = uiState.employee
    val timeWorked = secondsToTimeString(uiState.secondsWorked)
    val startTimeStamp = timestampToString(uiState.workSession?.startTime ?: 0)

    LaunchedEffect(Unit) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is UserPanelUiEvent.LogoutSuccess -> {
                    onLogoutSuccess()
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        user?.let {
            Text("Nombre: ${it.name}", color = MaterialTheme.colorScheme.onBackground)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Rol: ${it.category}", color = MaterialTheme.colorScheme.onBackground)
        } ?: Text("Usuario no cargado")

        Spacer(modifier = Modifier.height(8.dp))

        Text("Inicio jornada: $startTimeStamp", color = MaterialTheme.colorScheme.onBackground)

        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(
                text = timeWorked,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 45.sp
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button(
                onClick = {
                    CoroutineScope(Dispatchers.Main).launch {
                        val result = viewModel.logout()
                        result.onSuccess {
                            Log.i("UserPanelScreen", "Sesión finalizada correctamente")
                            onLogoutSuccess()
                        }.onFailure {
                            Log.e("UserPanelScreen", "Error al cerrar sesión", it)
                            // TODO: Mostrar snackbar o alerta
                        }
                    }
                }, enabled = !uiState.isLoggingOut, modifier = Modifier
                    .height(48.dp)
                    .width(320.dp)
            ) {
                if (uiState.isLoggingOut) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(24.dp),
                        strokeWidth = 2.dp
                    )
                } else {
                    Text("Cerrar Sesión", style = MaterialTheme.typography.labelLarge)
                }
            }

        }
    }
}


