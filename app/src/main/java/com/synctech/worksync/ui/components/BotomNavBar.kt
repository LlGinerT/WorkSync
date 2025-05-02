package com.synctech.worksync.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.synctech.worksync.ui.navigation.BottomNavItem
import com.synctech.worksync.ui.uiUtils.IconLibrary

@Composable
fun BottomNavBar(navController: NavController) {

    val jobListIcon = IconLibrary.JobList
    val inventoryIcon = IconLibrary.Inventory
    val userPanelIcon = IconLibrary.TimePanel

    val items = listOf(
        BottomNavItem(
            "JobList",
            "Trabajo",
            jobListIcon.filled,
            jobListIcon.outlined
        ),
        BottomNavItem(
            "Inventory",
            "Inventario",
            inventoryIcon.filled,
            inventoryIcon.outlined
        ),
        BottomNavItem(
            "UserPanel",
            "Registro Horario",
            userPanelIcon.filled,
            userPanelIcon.outlined
        ),
    )

    NavigationBar {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
        items.forEachIndexed { index, item ->
            val selected = currentRoute == item.route

            NavigationBarItem(
                selected = selected,
                onClick = {
                    if (!selected) {
                        navController.navigate(item.route) {
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = {
                    val icon = if (!selected && item.outlinedIcon != null) {
                        item.outlinedIcon
                    } else {
                        item.filledIcon
                    }
                    Icon(imageVector = icon, contentDescription = item.label)
                },
                label = {
                    Text(item.label)
                }
            )
        }
    }
}