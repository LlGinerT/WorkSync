package com.synctech.worksync.domain.models

/**
 * Representa una colección de materiales en el dominio de la aplicación.
 *
 * @param title Título de la colección de materiales.
 * @param materials Lista de materiales que pertenecen a esta colección.
 */
data class Materials(
    val title: String,
    val materials: List<Material>
)

/**
 * Representa un material individual en el dominio de la aplicación.
 *
 * @param materialId Identificador único del material.
 * @param name Nombre del material.
 * @param precio Precio del material.
 * @param cantidad Cantidad disponible del material (puede ser 0 si no hay unidades).
 */
data class Material(
    val materialId: Int,
    val name: String,
    val precio: Double,
    val cantidad: Int
)
