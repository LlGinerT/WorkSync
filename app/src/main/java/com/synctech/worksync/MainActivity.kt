package com.synctech.worksync

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.synctech.worksync.ui.navigation.AppNavHost
import com.synctech.worksync.ui.theme.WorkSyncTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkSyncTheme {
                AppNavHost()
            }
        }
    }
}

