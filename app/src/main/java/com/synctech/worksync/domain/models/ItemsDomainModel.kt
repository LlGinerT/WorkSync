package com.synctech.worksync.domain.models


/**
 * Representa un material individual en el dominio de la aplicación.
 *
 * @param materialId Identificador único del material.
 * @param name Nombre del material.
 * @param precio Precio del material.
 * @param cantidad Cantidad disponible del material (puede ser 0 si no hay unidades).
 */
data class ItemsDomainModel(
    val materialId: Int,
    val name: String,
    val precio: Double,
    val cantidad: Int
)
