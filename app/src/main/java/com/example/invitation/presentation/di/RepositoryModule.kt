package com.example.invitation.presentation.di

import com.example.invitation.data.repository.InvitationRepositoryImpl
import com.example.invitation.data.repository.dataSource.InviteLocalDataSource
import com.example.invitation.data.repository.dataSource.InviteRemoteDataSource
import com.example.invitation.domain.repository.InvitationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideInvitationRepository(
        inviteRemoteDataSource: InviteRemoteDataSource,
        inviteLocalDataSource: InviteLocalDataSource
    ) : InvitationRepository{
        return InvitationRepositoryImpl(inviteRemoteDataSource,
        inviteLocalDataSource)
    }
}