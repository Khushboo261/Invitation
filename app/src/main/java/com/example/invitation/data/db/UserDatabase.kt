package com.example.invitation.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.invitation.data.model.User

@Database(
    entities = [User::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class UserDatabase : RoomDatabase(){
    abstract fun getUserDAO():UserDAO
}