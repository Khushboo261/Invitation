package com.example.invitation.presentation.di

import com.example.invitation.domain.repository.InvitationRepository
import com.example.invitation.domain.usecase.CancelInviteUseCase
import com.example.invitation.domain.usecase.CheckUserExistUseCase
import com.example.invitation.domain.usecase.GetInviteUseCase
import com.example.invitation.domain.usecase.SaveInviteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetInviteUseCase(
        invitationRepository: InvitationRepository
    ) : GetInviteUseCase{
        return GetInviteUseCase(invitationRepository)
    }

    @Singleton
    @Provides
    fun provideSaveInviteUseCase(
        invitationRepository: InvitationRepository
    ) : SaveInviteUseCase{
        return SaveInviteUseCase(invitationRepository)
    }

    @Singleton
    @Provides
    fun provideCancelInvite(
        invitationRepository: InvitationRepository
    ) : CancelInviteUseCase{
        return CancelInviteUseCase(invitationRepository)
    }

    @Singleton
    @Provides
    fun provideCheckUser(
        invitationRepository: InvitationRepository
    ) : CheckUserExistUseCase{
        return CheckUserExistUseCase(invitationRepository)
    }
}