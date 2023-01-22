package com.example.invitation.data.repository

import com.example.invitation.data.model.User
import com.example.invitation.data.repository.dataSource.InviteLocalDataSource
import com.example.invitation.data.repository.dataSource.InviteRemoteDataSource
import com.example.invitation.data.util.Resource
import com.example.invitation.domain.repository.InvitationRepository
import com.google.gson.JsonObject
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class InvitationRepositoryImpl(
    private val inviteRemoteDataSource: InviteRemoteDataSource,
    private val inviteLocalDataSource: InviteLocalDataSource
) : InvitationRepository {
    override suspend fun getInvite(requestBody: JsonObject): Resource<String> {
        return responseToResource(inviteRemoteDataSource.getInvite(requestBody))
    }

    override suspend fun saveInvite(user: User) {
        inviteLocalDataSource.saveUserToDB(user)
    }

    override suspend fun cancelInvite(user: User) {
        inviteLocalDataSource.deleteUserFromDB(user)
    }

    override fun checkUser(email: String): User? {
        return inviteLocalDataSource.checkUserInDB(email)
    }

    private fun responseToResource(response: Response<String>):Resource<String>{
        if (response.isSuccessful){
            response.body()?.let {result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }
}