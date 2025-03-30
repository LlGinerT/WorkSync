package com.synctech.worksync.data.testData

import com.synctech.worksync.domain.models.Material
import com.synctech.worksync.domain.models.User
import com.synctech.worksync.domain.repositories.MaterialRepository

/**
 * Implementación de prueba del repositorio de materiales.
 * Proporciona datos ficticios para simular la interacción con un repositorio real.
 */
class MockMaterialDataRepository: MaterialRepository {
    private val materials = mutableListOf(
        Material(
            materialId = 1,
            name = "Cable De Red",
            precio = 100.50,
            cantidad = 35
        ),
        Material(
            materialId = 2,
            name = "Adaptador RJ45",
            precio = 170.50,
            cantidad = 60
        ),
        Material(
            materialId = 3,
            name = "Módem",
            precio = 65.50,
            cantidad = 120
        ),
        Material(
            materialId = 4,
            name = "Switch de Red",
            precio = 80.50,
            cantidad = 46
        ),
    )

    /**
     * Obtiene la lista de materiales disponibles en el repositorio simulado.
     *
     * @return Lista de materiales.
     */
    override fun getMaterials(): List<Material> {
        return materials
    }

    /**
     * Agrega un material al repositorio simulado.
     *
     * @param user Usuario que realiza la acción de agregar el material.
     * @param material El material a agregar.
     * @return `true` si el material se agregó correctamente, siempre retorna `true` en esta implementación.
     */
    override fun addMaterials(user: User, material: Material): Boolean {
        return true
    }

    /**
     * Elimina un material del repositorio simulado.
     *
     * @param user Usuario que realiza la acción de eliminar el material.
     * @param material El material a eliminar.
     * @return `true` si el material se eliminó correctamente, siempre retorna `true` en esta implementación.
     */
    override fun removeMaterials(user: User, material: Material): Boolean {
        return true
    }
}
