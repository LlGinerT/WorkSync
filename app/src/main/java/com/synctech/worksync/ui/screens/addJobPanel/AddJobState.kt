package com.synctech.worksync.ui.screens.addJobPanel

data class AddJobState (
    val jobName: String = "",
    val clientName: String = "",
    val address: String = "",
    val description: String = "",
    val assignedTo: String = "",
    val errorMessage: String? = null
)