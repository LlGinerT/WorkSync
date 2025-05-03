package com.synctech.worksync.ui.components


import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.synctech.worksync.ui.theme.WorkSyncTheme

@Composable
fun MainScaffold(
    navController: NavHostController, content: @Composable (Modifier) -> Unit
) {
    Scaffold(bottomBar = {
        BottomNavBar(navController = navController)
    }) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            content(Modifier.fillMaxSize())
        }
    }
}

@Preview(showBackground = true, name = "MainScaffold - Light Mode")
@Preview(
    showBackground = true,
    name = "MainScaffold - Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun MainScaffoldPreview() {
    WorkSyncTheme {
        val navController = rememberNavController()

        MainScaffold(navController = navController) { modifier ->
            Box(modifier = modifier) {
                Text("Vista principal", modifier = Modifier.padding(16.dp))
            }
        }
    }
}



