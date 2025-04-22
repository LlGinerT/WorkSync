package com.synctech.worksync.domain.exceptions

//TODO: Documentar
sealed class JobsErrors(message: String) : Exception(message) {

    class NotFound(val jobId: String) : JobsErrors("Job with ID $jobId not found")
    object Unauthorized : JobsErrors("Unauthorized access")
}