package com.example.invitation.presentation.di

import com.example.invitation.data.db.UserDAO
import com.example.invitation.data.repository.dataSource.InviteLocalDataSource
import com.example.invitation.data.repository.dataSourceImpl.InviteLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Provides
    @Singleton
    fun provideLocalDataSource(userDAO: UserDAO):InviteLocalDataSource{
        return InviteLocalDataSourceImpl(userDAO)
    }

}