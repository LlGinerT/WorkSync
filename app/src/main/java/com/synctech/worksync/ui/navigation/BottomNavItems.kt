package com.synctech.worksync.ui.navigation

import com.synctech.worksync.ui.models.BottomNavItem
import com.synctech.worksync.ui.uiUtils.IconLibrary

val bottomNavItems = listOf(
    BottomNavItem(
        "JobList",
        "Trabajo",
        IconLibrary.JobList.filled,
        IconLibrary.JobList.outlined
    ),
    BottomNavItem(
        "Inventory",
        "Inventario",
        IconLibrary.Inventory.filled,
        IconLibrary.Inventory.outlined
    ),
    BottomNavItem(
        "UserPanel",
        "Registro Horario",
        IconLibrary.TimePanel.filled,
        IconLibrary.TimePanel.outlined
    )
)
