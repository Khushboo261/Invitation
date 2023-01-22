package com.example.invitation.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.invitation.domain.usecase.CancelInviteUseCase
import com.example.invitation.domain.usecase.CheckUserExistUseCase
import com.example.invitation.domain.usecase.GetInviteUseCase
import com.example.invitation.domain.usecase.SaveInviteUseCase

class InvitationViewModelFactory(
    private val app : Application,
    private val getInviteUseCase: GetInviteUseCase,
    private val saveInviteUseCase: SaveInviteUseCase,
    private val cancelInviteUseCase: CancelInviteUseCase,
    private val checkUserExistUseCase: CheckUserExistUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return InvitationViewModel(
            app,
            getInviteUseCase,
            saveInviteUseCase,
            cancelInviteUseCase,
            checkUserExistUseCase
        ) as T
    }

}