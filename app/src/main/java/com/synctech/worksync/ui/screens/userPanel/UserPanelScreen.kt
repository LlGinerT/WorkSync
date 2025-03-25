package com.synctech.worksync.ui.screens.userPanel


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.synctech.worksync.data.testData.MockWorkSessionRepository
import com.synctech.worksync.domain.useCases.SaveWorkSessionUseCase
import com.synctech.worksync.ui.session.SessionViewModel
import com.synctech.worksync.ui.theme.WorkSyncTheme

@Composable
fun UserPanelScreen(sessionViewModel: SessionViewModel) {

    val worker = sessionViewModel.uiWorker
    val timeWorked = sessionViewModel.getFormattedWorkedTime()
    val startTimeStamp = sessionViewModel.getFormattedStartedSession()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,

        ) {
        worker?.let {
            Text("Nombre: ${it.name}", color = MaterialTheme.colorScheme.onBackground)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Rol: ${it.category}", color = MaterialTheme.colorScheme.onBackground)
        } ?: Text("Usuario no cargado")
        Spacer(modifier = Modifier.height(8.dp))
        Text("Inicio jornada: $startTimeStamp", color = MaterialTheme.colorScheme.onBackground)
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), Arrangement.Center) {
            Text(
                text = timeWorked,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 45.sp
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), Arrangement.Center) {
            Button(
                onClick = { sessionViewModel.logout() },
                modifier = Modifier
                    .height(48.dp)
                    .width(320.dp)
            ) {
                Text("Cerrar Sesion", style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}


@Preview
@Composable
fun UserPanelPreview() {

    val repository = MockWorkSessionRepository()
    val useCase = SaveWorkSessionUseCase(repository)
    val viewModel = SessionViewModel(useCase)

    WorkSyncTheme {
        UserPanelScreen(viewModel)
    }
}
