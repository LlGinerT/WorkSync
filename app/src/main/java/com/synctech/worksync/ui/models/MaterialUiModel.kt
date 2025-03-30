package com.synctech.worksync.ui.models

import com.synctech.worksync.domain.models.Material
import com.synctech.worksync.domain.models.Materials

/**
 * Modelo de datos para representar una colección de materiales en la UI.
 *
 * @param title Título de la colección de materiales.
 * @param materials Lista de materiales representados en la UI.
 */
data class MaterialsUiModel(
    val title: String,
    val materials: List<MaterialUiModel>
)

/**
 * Modelo de datos para representar un material individual en la UI.
 *
 * @param materialId Identificador único del material.
 * @param name Nombre del material.
 * @param precio Precio del material.
 * @param cantidad Cantidad disponible del material (puede ser 0 si no hay unidades).
 */
data class MaterialUiModel(
    val materialId: Int,
    val name: String,
    val precio: Double,
    val cantidad: Int
)

/**
 * Convierte un objeto de tipo [Materials] del dominio a su representación en la UI.
 *
 * @receiver [Materials] Modelo de dominio de una colección de materiales.
 * @return [MaterialsUiModel] Modelo de UI con los datos transformados.
 */
fun Materials.toUI() = MaterialsUiModel(
    title = this.title,
    materials = this.materials.map { it.toUi() }
)

/**
 * Convierte un objeto de tipo [Material] del dominio a su representación en la UI.
 *
 * @receiver [Material] Modelo de dominio de un material individual.
 * @return [MaterialUiModel] Modelo de UI con los datos transformados.
 */
fun Material.toUi() = MaterialUiModel(
    materialId = materialId,
    name = name,
    precio = precio,
    cantidad = cantidad
)
