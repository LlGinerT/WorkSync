package com.synctech.worksync

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import com.synctech.worksync.ui.screen.WorkScreen
import com.synctech.worksync.ui.theme.WorkSyncTheme
import com.synctech.worksync.ui.viewmodel.WorkViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: WorkViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkSyncTheme {
                WorkScreen(viewModel = viewModel, modifier = Modifier.fillMaxSize())
            }
        }
    }
}
