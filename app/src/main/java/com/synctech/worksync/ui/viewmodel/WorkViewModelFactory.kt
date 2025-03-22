package com.synctech.worksync.ui.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.synctech.worksync.domain.useCases.GetWorkUseCase

class WorkViewModelFactory(
    private val getWorkUseCase: GetWorkUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WorkViewModel::class.java)) {
            return WorkViewModel(getWorkUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
