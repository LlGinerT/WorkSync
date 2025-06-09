package com.synctech.worksync.ui.screens.jobsPanel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synctech.worksync.data.cache.CacheActiveSessionRepository
import com.synctech.worksync.domain.repositories.JobsRepository
import com.synctech.worksync.ui.models.toUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel responsable de gestionar la lista de trabajos disponibles.
 *
 * Utiliza el [JobsRepository] y accede al usuario activo a través del [CacheActiveSessionRepository].
 *
 * @param jobsRepository Repositorio central de trabajos (mediador).
 * @param sessionCache Repositorio de sesión en caché.
 */
class JobsViewModel(
    private val jobsRepository: JobsRepository,
    private val sessionCache: CacheActiveSessionRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(JobsState())
    val uiState: StateFlow<JobsState> = _uiState.asStateFlow()

    init {
        fetchJobs()
    }

    /**
     * Carga los trabajos desde el repositorio y actualiza el estado de la UI.
     */
    private fun fetchJobs() {
        val user = sessionCache.getUser() ?: return

        viewModelScope.launch {
            _uiState.update { it.copy(showLoadingIndicator = true, errorMessage = null) }

            runCatching {
                jobsRepository.getJobs(user)
            }.onSuccess { jobs ->
                _uiState.update {
                    it.copy(
                        jobsList = jobs.map { job -> job.toUi() }, showLoadingIndicator = false
                    )
                }
            }.onFailure { error ->
                _uiState.update {
                    it.copy(
                        errorMessage = error.message ?: "Error desconocido",
                        showLoadingIndicator = false
                    )
                }
            }
        }
    }
}

/*
private val auth:FireBaseAuth = Firebase.auth
private val firestore = Firebase.firestore

private val _jobsData = MutableStateFlow<List<JobState>>(emptyList())
val notesData: StateFlow<List<JobState>> = _jobsData

######
var state by mutableStateOf(JobsState()
    perivate set
######

-----EDIT COLOCAR EN EL FORMULARIO---
fun onValue(value:String, text:Stirng){
  when(text){
    "jobName" -> state = state.copy(jobName=value)
    "clientName" -> state = state.copy(clientName = value)
    "description" -> state = state.copy(description = value)
    "address" -> state = state.copy(address = value)
}



fun fectchJobs(){
  val email = auth.currentUser?.email

  firestore.collection("Jobs")
  .whereEqualTo("emailUser", email.toString())
  .addSnapshotListener{querySnapshot, error ->}
       if (error != null){
          return@addSnapshotListener}

          val documents = mutableListOf<JobState>()
          if(querySnapshot !=null){
            for (´document in querySnapshot){
               valMyDocument = document.toObject(JobsState::class.java).copy(idDoc = document.id)
               documents.add(myDocument)
                }
            }
            _jobsData.value

}


fun saveNewJob(jobName:String, clientName:String,description:String,address:String, onSuccess:() ->Unit){

    val email = auth.currentUser?.email
    viewModelScope.launch(Dispatchers.IO){
        try{
            val newJob = hashMapOs(
              "jobName" to jobName,
              "clientName" to clientName,
              "description" to description,
              "address" to address
              )
              firestore.collection("JobName").add(newJob)
                 .addSuccessListener{
                   onSuccess()}
        }catch(e:Exception){
           Log.d("ERROR SAVE", "ERROR AL GUARDAR" ${e.localizedMessage}")


}

########
fun getJobById(documentId: String){
    firestore.collection("Jobs")
        .document(documentId)
        .addSnapshotListener{snapshot, _ ->
            if(snapshot != null){
                val job = snapshot.toObject(JobsState::class.java)
                state = state.copy(
                    jobName = job?.jobName?: "",
                    clientName = job?.clientName?:"",
                    description = job?.description?:"",
                    address = job?.address?:""
                    )}
########



fun signOut(){
  auth.signOut()


actions = {
    IconButton(onClick = {
        navController.navigate("AddJobView")
        }){
           Icon(imageVector = Icons.Default.Add, contentDescription = " ")
           }
        }
*########  EDIT  ##########
    LazyColumn{
    items(datos){
        CreateJob(


#### FUNCION PARA GUARDAR #####

fun updateUob(idDoc:String, onSuccess:() ->Unit){
    viewModelScope.launch(Dispatchers.IO){
        try{
            val editJob = hashMapOs(
              "jobName" to state.jobName,
              "clientName" to state.clientName,
              "description" to state.description,
              "address" to state.address
              )
              firestore.collection("JobName").document(idDoc)
              .update(editJob as Map<String, Any>)
                 .addSuccessListener{
                   onSuccess()}
        }catch(e:Exception){
           Log.d("ERROR EDITAR", "ERROR AL EDITAR" ${e.localizedMessage}")


}



* */
