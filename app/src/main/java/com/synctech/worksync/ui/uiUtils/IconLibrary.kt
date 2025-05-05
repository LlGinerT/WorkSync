package com.synctech.worksync.ui.uiUtils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.HourglassBottom
import androidx.compose.material.icons.filled.Warehouse
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.outlined.HourglassBottom
import androidx.compose.material.icons.outlined.Warehouse
import androidx.compose.material.icons.outlined.Work
import androidx.compose.ui.graphics.vector.ImageVector

sealed class IconLibrary(val filled: ImageVector, val outlined: ImageVector? = null) {
    object Add : IconLibrary(Icons.Filled.Add)
    object Inventory : IconLibrary(Icons.Filled.Warehouse, Icons.Outlined.Warehouse)
    object JobList : IconLibrary(Icons.Filled.Work, Icons.Outlined.Work)
    object TimePanel : IconLibrary(Icons.Filled.HourglassBottom, Icons.Outlined.HourglassBottom)
}