package com.example.invitation.data.repository.dataSource

import com.example.invitation.data.model.User
import com.google.gson.JsonObject
import okhttp3.RequestBody
import retrofit2.Response

interface InviteRemoteDataSource {
    suspend fun getInvite(requestBody: JsonObject):Response<String>
}