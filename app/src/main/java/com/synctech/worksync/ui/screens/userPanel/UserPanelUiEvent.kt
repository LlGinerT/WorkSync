package com.synctech.worksync.ui.screens.userPanel

sealed class UserPanelUiEvent {
    object LogoutSuccess : UserPanelUiEvent()
}