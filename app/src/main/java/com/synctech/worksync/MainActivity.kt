package com.synctech.worksync

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.synctech.worksync.data.repository.FakeWorkDataRepository
import com.synctech.worksync.domain.useCases.GetWorkUseCase
import com.synctech.worksync.ui.screen.WorkScreen
import com.synctech.worksync.ui.theme.WorkSyncTheme
import com.synctech.worksync.ui.viewmodel.WorkViewModel
import com.synctech.worksync.ui.viewmodel.WorkViewModelFactory

class MainActivity : ComponentActivity() {
    private val workViewModel: WorkViewModel by viewModels {
        val getWorkUseCase = GetWorkUseCase(FakeWorkDataRepository())
        WorkViewModelFactory(getWorkUseCase)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkSyncTheme {
                WorkScreen(viewModel = workViewModel, modifier = Modifier.fillMaxSize())
            }
        }
    }
}
