package com.synctech.worksync.domain.exceptions

class JobNotFoundException(jobId: String) : Exception("Trabajo con ID $jobId no encontrado.")

class UnauthorizedAccessException : Exception("No tienes permisos para acceder a este trabajo.")
