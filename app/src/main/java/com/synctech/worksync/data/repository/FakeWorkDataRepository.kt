import com.synctech.worksync.domain.models.User
import com.synctech.worksync.domain.models.Work
import com.synctech.worksync.domain.repository.WorkRepository

class FakeWorkDataRepository : WorkRepository {

    // Lista simulada de trabajos
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
     * Devuelve la lista de trabajos según el usuario.
     * Si el usuario es administrador, devuelve todos los trabajos.
     * Si no es administrador, devuelve los trabajos asignados a ese usuario.
     */
    override fun getWork(user: User): List<Work> {
        return if (user.isAdmin) {
            mockWorkData
        } else {
            mockWorkData.filter { it.assignedTo == user.userId }
        }
    }

    /**
     * Asigna un trabajo a un usuario.
     * @param user El usuario que intenta asignar el trabajo.
     * @param workId El índice del trabajo que se quiere asignar.
     * @param assignedUser El usuario que será asignado al trabajo.
     * @return True si la asignación fue exitosa, False si no.
     */
    override fun assignWork(user: User, workId: Int, assignedUser: User): Boolean {
        if (!user.isAdmin || workId !in mockWorkData.indices) return false
        mockWorkData[workId].assignedTo = assignedUser.userId
        return true
    }

    override fun removeWork(user: User, work: Work): Boolean {
        TODO("Not yet implemented")
    }

    /**
     * Añadir un nuevo trabajo a la lista de trabajos, solo si el usuario es administrador.
     *
     * @param user El usuario que realiza la solicitud.
     * @param work El trabajo a agregar.
     * @return Si el trabajo fue añadido o no.
     */
    override fun addWork(user: User, work: Work): Boolean {
        return if (user.isAdmin) {
            mockWorkData.add(work)  // Solo un administrador puede añadir trabajos
            true
        } else {
            false  // Los usuarios no administradores no pueden añadir trabajos
        }
    }
}
