package com.example.invitation.data.repository.dataSourceImpl

import com.example.invitation.data.api.InvitationAPIService
import com.example.invitation.data.model.User
import com.example.invitation.data.repository.dataSource.InviteRemoteDataSource
import com.google.gson.JsonObject
import okhttp3.RequestBody
import retrofit2.Response

class InviteRemoteDataSourceImpl(
    private val invitationAPIService: InvitationAPIService
) : InviteRemoteDataSource {
    override suspend fun getInvite(requestBody: JsonObject): Response<String> {
        return invitationAPIService.getInvite(requestBody)
    }
}