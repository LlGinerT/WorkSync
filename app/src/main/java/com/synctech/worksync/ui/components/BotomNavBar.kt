package com.synctech.worksync.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.synctech.worksync.ui.navigation.bottomNavItems
import com.synctech.worksync.ui.theme.WorkSyncTheme

@Composable
fun BottomNavBar(navController: NavController) {

    NavigationBar {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
        bottomNavItems.forEachIndexed { index, item ->
            val selected = currentRoute == item.route

            NavigationBarItem(selected = selected, onClick = {
                if (!selected) {
                    navController.navigate(item.route) {
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }, icon = {
                val icon = if (!selected && item.outlinedIcon != null) {
                    item.outlinedIcon
                } else {
                    item.filledIcon
                }
                Icon(imageVector = icon, contentDescription = item.label)
            }, label = {
                Text(item.label)
            })
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
fun BottomNavBarPreview() {
    val navController = rememberNavController()
    WorkSyncTheme {
        Scaffold(
            bottomBar = {
                BottomNavBar(navController = navController)
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                Text("Contenido de ejemplo", modifier = Modifier.padding(16.dp))
            }
        }
    }
}
