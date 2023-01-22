package com.example.invitation.domain.repository

import com.example.invitation.data.model.User
import com.example.invitation.data.util.Resource
import com.google.gson.JsonObject
import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody
import retrofit2.Response

interface InvitationRepository {

    suspend fun getInvite(requestBody: JsonObject) : Resource<String>

    suspend fun saveInvite(user: User)

    suspend fun cancelInvite(user: User)

    fun checkUser(email : String): User?
}