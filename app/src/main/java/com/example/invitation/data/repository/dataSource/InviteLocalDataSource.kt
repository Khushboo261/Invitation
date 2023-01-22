package com.example.invitation.data.repository.dataSource

import com.example.invitation.data.model.User
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface InviteLocalDataSource {

    suspend fun saveUserToDB(user:User)

    suspend fun deleteUserFromDB(user: User)

    fun checkUserInDB(email:String): User?

}