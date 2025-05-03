package com.synctech.worksync.ui.navigation

import com.synctech.worksync.ui.models.BottomNavItem
import com.synctech.worksync.ui.uiUtils.IconLibrary

val bottomNavItems = listOf(
    BottomNavItem(
        "main/jobs",
        "Trabajo",
        IconLibrary.JobList.filled,
        IconLibrary.JobList.outlined
    ),
    BottomNavItem(
        "main/inventory",
        "Inventario",
        IconLibrary.Inventory.filled,
        IconLibrary.Inventory.outlined
    ),
    BottomNavItem(
        "main/userPanel",
        "Registro Horario",
        IconLibrary.TimePanel.filled,
        IconLibrary.TimePanel.outlined
    )
)
