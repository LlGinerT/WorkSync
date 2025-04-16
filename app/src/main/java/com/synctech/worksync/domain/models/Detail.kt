package com.synctech.worksync.domain.models

data class Detail(
    // Te falta añadir un WorkID
    val workId: String,
    val jobName: String,
    val clientName: String,
    val description: String,
    val address: String,
    var assignedTo: String? = null
)