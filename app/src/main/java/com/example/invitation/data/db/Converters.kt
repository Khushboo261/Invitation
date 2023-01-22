package com.example.invitation.data.db

import androidx.room.TypeConverter
import com.example.invitation.data.model.User

class Converters {
    @TypeConverter
    fun fromUser(user: User): String? {
        return user.email
    }

    @TypeConverter
    fun toUser(email: String):User{
        return User(0,null, email)
    }
}