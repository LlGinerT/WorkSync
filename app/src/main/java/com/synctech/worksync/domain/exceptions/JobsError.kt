@file:Suppress("JavaIoSerializableObjectMustHaveReadResolve")

package com.synctech.worksync.domain.exceptions

//TODO: Documentar
sealed class JobsError(message: String) : Exception(message) {

    class NotFound(private val jobId: String) : JobsError("Job with ID $jobId not found")
    data object Unauthorized : JobsError("Unauthorized access")
}