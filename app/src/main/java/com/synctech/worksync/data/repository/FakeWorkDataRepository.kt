package com.synctech.worksync.data.repository

import com.synctech.worksync.domain.models.User
import com.synctech.worksync.domain.models.Work
import com.synctech.worksync.domain.models.Works
import com.synctech.worksync.domain.repository.WorkRepository

/*/**
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
*/




/**
 * Implementación falsa del repositorio de trabajos, utilizada para simular la obtención de datos en lugar de hacerlo desde una base de datos o una API real.
 */
class FakeWorkDataRepository : WorkRepository {

    // Lista simulada de trabajos.
    private val mockWorkData = mutableListOf(
        Work(
            jobName = "Instalación Cable Fibra Optica",
            clientName = "Andrés Sanz",
            description = "",
            address = "La Latina",
            assignedTo = null
        ),
        Work(
            jobName = "Instalación Router",
            clientName = "Juan Pérez",
            description = "Instalación de Router en el Salón",
            address = "Gran Vía",
            assignedTo = null
        ),
        Work(
            jobName = "Configuración de Red Wifi",
            clientName = "Elisa Main",
            description = "Asignación de un nombre único para la red (SSID)",
            address = "Sol",
            assignedTo = null
        ),
        Work(
            jobName = "Optimización de la Señal Red Wifi",
            clientName = "Daniel Rodriquez",
            description = "Mejorar la cobertura y el rendimiento de una red Wi-Fi. Mover el router a una ubicación más central",
            address = "Chamberí",
            assignedTo = null
        ),
        Work(
            jobName = "Configuración de Red Local",
            clientName = "Sara Ban",
            description = "Configuración red local que conecta varios dispositivos mediante cables Ethernet",
            address = "Malasaña",
            assignedTo = null
        )
    )


    /**
     * Devuelve la lista simulada de trabajos. Si el usuario es administrador, se devuelven todos los trabajos.
     * Si no es administrador, se puede devolver una lista limitada o vacía.
     *
     * @param user El usuario que está solicitando los trabajos.
     * @return Una lista de objetos `Work`.
     */
    override fun getWork(user: User): List<Work> {
        // Si el usuario es administrador, devuelve todos los trabajos
        if (user.isAdmin) {
            return mockWorkData
        }

        // Si el usuario no es administrador, solo devuelve los trabajos asignados a él
        return mockWorkData.filter { it.assignedTo?.username == user.username }
    }

    /**
     * Añadir un nuevo trabajo a la lista de trabajos, solo si el usuario es administrador.
     *
     * @param user El usuario que realiza la solicitud.
     * @param work El trabajo a agregar.
     * @return Si el trabajo fue añadido o no.
     */
    fun addWork(user: User, work: Work): Boolean {
        return if (user.isAdmin) {
            mockWorkData.add(work)  // Solo un administrador puede añadir trabajos
            true
        } else {
            false  // Los usuarios no administradores no pueden añadir trabajos
        }
    }
}



    /*
    /**
     * Añadir un nuevo trabajo a la lista de trabajos, solo si el usuario es administrador.
     *
     * @param user El usuario que realiza la solicitud.
     * @param work El trabajo a agregar.
     * @return Si el trabajo fue añadido o no.
     */
    fun addWork(user: User, work: Work): Boolean {
        return if (user.isAdmin) {
            mockWorkData.add(work)  // Solo un administrador puede añadir trabajos.
            true
        } else {
            false  // Los usuarios no administradores no pueden añadir trabajos.
        }
    }
}*/
