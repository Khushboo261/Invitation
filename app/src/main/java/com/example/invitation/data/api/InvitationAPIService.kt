package com.example.invitation.data.api

import com.example.invitation.data.model.User
import com.google.gson.JsonObject
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface InvitationAPIService {

    @POST("/fakeAuth")
    suspend fun getInvite(
        @Body requestBody: JsonObject
    ):Response<String>
}