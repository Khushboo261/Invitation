package com.example.invitation.presentation.di

import com.example.invitation.data.api.InvitationAPIService
import com.example.invitation.data.repository.dataSource.InviteRemoteDataSource
import com.example.invitation.data.repository.dataSourceImpl.InviteRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataSourceModule {

    @Singleton
    @Provides
    fun provideInviteRemoteDataSource(
        invitationAPIService: InvitationAPIService
    ) : InviteRemoteDataSource{
        return InviteRemoteDataSourceImpl(invitationAPIService)
    }
}