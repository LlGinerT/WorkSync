package com.synctech.worksync.data.repository

import com.synctech.worksync.domain.models.Work
import com.synctech.worksync.domain.models.Works
import com.synctech.worksync.domain.repository.WorkRepository

/**
 * Implementación falsa del repositorio de trabajos, utilizada para simular la obtención de datos en lugar de hacerlo desde una base de datos o una API real.
 */
class FakeWorkDataRepository : WorkRepository {

    // Lista simulada de trabajos.
    // Cada objeto de tipo `Work` contiene información sobre un trabajo, como el nombre del trabajo, el cliente, la descripción y la dirección.
    private val mockWorkData = listOf(
        Work(
            jobName = "Instalación Cable Fibra Optica",
            clientName = "Andrés Sanz",
            description = "",
            address = "La Latina"
        ),
        Work(
            jobName = "Instalación Router",
            clientName = "Juan Pérez",
            description = "Instalación de Router en el Salón",
            address = "Gran Vía"
        ),
        Work(
            jobName = "Configuración de Red Wifi",
            clientName = "Elisa Main",
            description = "Asignación de un nombre único para la red (SSID)",
            address = "Sol"
        ),
        Work(
            jobName = "Optimización de la Señal Red Wifi",
            clientName = "Daniel Rodriquez",
            description = "Mejorar la cobertura y el rendimiento de una red Wi-Fi. Mover el router a una ubicación más central",
            address = "Chamberí"
        ),
        Work(
            jobName = "Configuración de Red Local",
            clientName = "Sara Ban",
            description = "Configuración red local que conecta varios dispositivos mediante cables Ethernet",
            address = "Malasaña"
        )
    )

    /**
     * Devuelve la lista simulada de trabajos.
     *
     * @return Una lista de objetos `Work` que contienen información simulada sobre varios trabajos.
     */
    override fun getWork(): List<Work> = mockWorkData
}
