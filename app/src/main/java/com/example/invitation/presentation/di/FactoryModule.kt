package com.example.invitation.presentation.di

import android.app.Application
import com.example.invitation.domain.usecase.CancelInviteUseCase
import com.example.invitation.domain.usecase.CheckUserExistUseCase
import com.example.invitation.domain.usecase.GetInviteUseCase
import com.example.invitation.domain.usecase.SaveInviteUseCase
import com.example.invitation.presentation.viewmodel.InvitationViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun provideInvitationViewModelFactory(
        application: Application,
        getInviteUseCase: GetInviteUseCase,
        saveInviteUseCase: SaveInviteUseCase,
        cancelInviteUseCase: CancelInviteUseCase,
        checkUserExistUseCase: CheckUserExistUseCase
    ) : InvitationViewModelFactory{
        return InvitationViewModelFactory(
            application,
            getInviteUseCase,
            saveInviteUseCase,
            cancelInviteUseCase,
            checkUserExistUseCase
        )
    }

}