package com.synctech.worksync.ui.screens.jobsPanel
//
//import android.util.Log
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.synctech.worksync.domain.models.EmployeeDomainModel
//import com.synctech.worksync.domain.useCases.jobs.GetJobsUseCase
//import com.synctech.worksync.ui.models.toUi
//import com.synctech.worksync.ui.session.SessionViewModel
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.flow.update
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//
///**
// * ViewModel para la gestión de trabajos.
// *
// * @param getJobsUseCase [GetJobsUseCase] Caso de uso para obtener los trabajos.
// * @param sessionViewModel [SessionViewModel] compartido que gestiona la sesion iniciada
// */
//class JobsViewModel(
//    private val getJobsUseCase: GetJobsUseCase, sessionViewModel: SessionViewModel
//) : ViewModel() {
//
//    private val _uiState = MutableStateFlow(JobsState())
//    val uiState: StateFlow<JobsState> = _uiState
//
//    init {
//        sessionViewModel.currentUser?.let { user ->
//            viewModelScope.launch {
//                fetchWorks(user)
//            }
//        }
//    }
//
//
//    /**
//     * Obtiene los trabajos y actualiza el estado de la UI.
//     *
//     * @param user [EmployeeDomainModel] Empleado del que se obtienen los trabajos.
//     */
//    private suspend fun fetchWorks(user: EmployeeDomainModel) {
//
//        _uiState.update {
//            it.copy(
//                showLoadingIndicator = true, errorMessage = null
//            )
//        }
//
//        // Dispatchers.io creo que deberia ir en el repositorio de bases de datos
//        // aqui ya manejamos con el viewModelScope.launch y con el usecase con suspend
//        //TODO: Comprobar Dispatchers.IO
//        val result = withContext(Dispatchers.IO) {
//            getJobsUseCase(user)
//        }
//        // he cambiado tu try catch por result en el UseCase
//        // misma mecanica, mejor gestion de varios errores que pueden dar
//        // los repositorios cuando metamos FireBase.
//        result.onSuccess { jobs ->
//            _uiState.update {
//                it.copy(
//                    jobsList = jobs.map { job -> job.toUi() },
//                    showLoadingIndicator = false,
//                    errorMessage = null
//                )
//            }
//            Log.i("JobsViewModel", "Trabajos cargados con exito: ${jobs.size}")
//        }.onFailure { error ->
//            _uiState.update {
//                it.copy(
//                    showLoadingIndicator = false,
//                    errorMessage = error.message ?: "Error desconocido"
//                )
//            }
//            Log.e("JobsViewModel", "Error obteniendo trabajos: ${error.message}", error)
//        }
//    }
//}
