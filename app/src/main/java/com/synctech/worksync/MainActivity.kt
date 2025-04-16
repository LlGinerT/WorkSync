package com.synctech.worksync

import com.synctech.worksync.data.testData.FakeWorkDataRepository
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import com.synctech.worksync.data.testData.MockMaterialDataRepository

import com.synctech.worksync.domain.models.User
import com.synctech.worksync.domain.useCases.GetMaterialUseCase
import com.synctech.worksync.domain.useCases.GetWorkUseCase
import com.synctech.worksync.ui.screens.materialPanel.MaterialScreen
import com.synctech.worksync.ui.screens.materialPanel.MaterialViewModel
import com.synctech.worksync.ui.screens.workPanel.WorkScreen
import com.synctech.worksync.ui.theme.WorkSyncTheme
import com.synctech.worksync.ui.screens.workPanel.WorkViewModel

class MainActivity : ComponentActivity() {
   //private val getWorkUseCase= GetWorkUseCase(FakeWorkDataRepository())
    //private val currentUser = User(userId = "78",username = "admin", isAdmin = false) // Aquí definimos que el usuario es admin
    //private val workViewModel = WorkViewModel(getWorkUseCase,currentUser)

    private val getMaterialUseCase= GetMaterialUseCase(MockMaterialDataRepository())
    private val materialViewModel = MaterialViewModel(getMaterialUseCase)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkSyncTheme {
                //WorkScreen(viewModel = workViewModel, modifier = Modifier.fillMaxSize())
                MaterialScreen(viewModel = materialViewModel, modifier = Modifier.fillMaxSize())
            }
        }
    }
}
