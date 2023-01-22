package com.example.invitation.presentation.di

import android.app.Application
import androidx.room.Room
import com.example.invitation.data.db.UserDAO
import com.example.invitation.data.db.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideUserDatabase(app:Application):UserDatabase{
        return Room.databaseBuilder(app, UserDatabase::class.java, "user_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideUserDao(userDatabase: UserDatabase):UserDAO{
        return userDatabase.getUserDAO()
    }

}