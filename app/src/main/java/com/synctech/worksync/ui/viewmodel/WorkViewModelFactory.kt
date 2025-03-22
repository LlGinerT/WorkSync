package com.synctech.worksync.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.synctech.worksync.domain.models.User
import com.synctech.worksync.domain.useCases.GetWorkUseCase

/**
 * Clase Factory para crear una instancia de [WorkViewModel].
 *
 * @param getWorkUseCase Un caso de uso que es responsable de obtener los datos de trabajo desde la capa de dominio.
 * Este parámetro se inyecta en el [WorkViewModel] para acceder y proporcionar los datos de trabajo.
 */
class WorkViewModelFactory(
    private val getWorkUseCase: GetWorkUseCase,
    private val currentUser: User // Añadimos el usuario
) : ViewModelProvider.Factory {

    /**
     * Crea una instancia de [WorkViewModel].
     *
     * @param modelClass El tipo de clase del ViewModel que necesita ser creado.
     * @return Una instancia de [WorkViewModel].
     * @throws IllegalArgumentException Si [modelClass] no es una subclase de [WorkViewModel].
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WorkViewModel::class.java)) {
            return WorkViewModel(getWorkUseCase, currentUser) as T
        }
        throw IllegalArgumentException("Clase ViewModel desconocida")
    }
}
