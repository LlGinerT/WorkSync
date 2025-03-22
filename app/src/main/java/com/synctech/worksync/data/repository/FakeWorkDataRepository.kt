package com.synctech.worksync.data.repository

import com.synctech.worksync.domain.models.Work
import com.synctech.worksync.domain.models.Works
import com.synctech.worksync.domain.repository.WorkRepository

class FakeWorkDataRepository:WorkRepository{
    private val mockWorkData = listOf(
        Work (
        jobName =  "Instalación Cable Fibra Optica",
        clientName = "Andrés Sanz",
        description = "",
        address = "La Latina"),
        Work(
            jobName = "Instalación Router",
            clientName = "Juan Pérez",
            description = "Instalación de red eléctrica en oficina",
            address = "Gran Vía"
        )
    )
        override fun getWork(): List<Work> = mockWorkData
}


