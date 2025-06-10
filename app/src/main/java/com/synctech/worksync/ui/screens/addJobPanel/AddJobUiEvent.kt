package com.synctech.worksync.ui.screens.addJobPanel

sealed class AddJobUiEvent {
    object JobSaved : AddJobUiEvent()
    object JobCancelled : AddJobUiEvent()
}
